package br.edu.infnet.batch;

import br.edu.infnet.entrega.client.EntregaClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<EntregaBatch> entregaReader() {

        return new FlatFileItemReaderBuilder<EntregaBatch>()
                .name("entregaReader")
                .resource(new ClassPathResource("batch/Entregas.csv"))
                .encoding("UTF-8")
                .linesToSkip(1)
                .delimited().delimiter(";")
                .names("id", "nomeCliente", "endereco", "frete", "data", "hora", "valorEntrega", "ativa")
                .fieldSetMapper(fs -> new EntregaBatch(
                        fs.readLong("id"),
                        fs.readString("nomeCliente"),
                        fs.readString("endereco"),
                        fs.readBigDecimal("frete"),
                        LocalDateTime.parse(fs.readString("data")),
                        LocalTime.parse(fs.readString("hora")),
                        fs.readBigDecimal("valorEntrega"),
                        fs.readBoolean("ativa")))
                .build();

    }

    @Bean
    public ItemWriter<EntregaRequest> entregaApiWriter(EntregaClient entregaClient) {
        return chunk -> {
            for (EntregaRequest entrega : chunk.getItems()) {
                System.err.println("[entregaApiWriter] Entrega enviada para a API = " + entrega);
                entregaClient.incluir(entrega);
            }
        };
    }

    @Bean
    public FlatFileItemWriter<EntregaBatch> entregaWriter() {

        return new FlatFileItemWriterBuilder<EntregaBatch>()
                .name("entregaWriter")
                .resource(new FileSystemResource("target/entregas-processados.csv"))
                .encoding("UTF-8")
                .shouldDeleteIfExists(true)
                .headerCallback(writer -> writer.write("id;nomeCliente;endereco;frete;data;hora;valorEntrega;ativa"))
                .delimited().delimiter(";")
                .names("id", "nomeCliente", "endereco", "frete", "data", "hora", "valorEntrega", "ativa")
                .build();
    }

    @Bean
    public Step importarEntregasStep(JobRepository jobRepository,
                                     PlatformTransactionManager transactionManager,
                                     FlatFileItemReader<EntregaBatch> entregaReader,
                                     EntregaProcessor entregaProcessor,
                                     ItemWriter<EntregaRequest> entregaApiWriter) {
        return new StepBuilder(
                "importarEntregasStep", jobRepository)
                .<EntregaBatch, EntregaRequest>chunk(3, transactionManager)
                .reader(entregaReader)
                .processor(entregaProcessor)
                .writer(entregaApiWriter)
                .build();
    }

/*    @Bean
    public Step importarEntregasStep(JobRepository jobRepository,
                                     PlatformTransactionManager transactionManager,
                                     FlatFileItemReader<EntregaBatch> entregaReader,
                                     EntregaProcessor entregaProcessor,
                                     FlatFileItemWriter<EntregaBatch> entregaWriter) {
        return new StepBuilder(
                "importarEntregasStep", jobRepository)
                .<EntregaBatch, EntregaBatch>chunk(3, transactionManager)
                .reader(entregaReader)
                .processor(entregaProcessor)
                .writer(entregaWriter)
                .build();
    }*/

    @Bean
    public Step resumoProcessamentoStep(JobRepository jobRepository,
                                        PlatformTransactionManager transactionManager) {
        return new StepBuilder("resumoProcessamentoStep", jobRepository)
                .tasklet((contribution, chuckContext) -> {
                    System.out.println("Step 2: processamento de entregas concluido");
                    System.out.println("Step 2: as entregas valida serão enviados para a API");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Job importarEntregasJob(JobRepository jobRepository,
                                   Step importarEntregasStep,
                                   Step resumoProcessamentoStep) {

        return new JobBuilder("importarEntregasJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(importarEntregasStep)
                .next(resumoProcessamentoStep)
                .build();
    }
}

package br.edu.infnet.model.domain.service.BaseGenerics;

import br.edu.infnet.model.domain.util.Identificavel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T extends Identificavel>{

    private final Map<Long, T> dados = new LinkedHashMap<Long,T>();

    public void incluir(T objeto){
        dados.put(objeto.getId(),objeto);
    }

    public void alterar(T objeto){
        dados.put(objeto.getId(),objeto);
    }

    public List<T> obterLista(){
        return new ArrayList<T>(dados.values());
    }// pode ser Collection também;

    public void excluir(Long id){
        dados.remove(id);
    }

    public T getId(Long id){
        return dados.get(id);
    }
}

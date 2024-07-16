package br.ufma.ppgee.eds.sistemacontroleestoque.adapter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;

public interface AdapterInterface<T> {
    public String [] getColumns();
    public T load(  Map<String,String>  values) throws ValidationFieldException;
    Map<String,String> get(T produto);
    public List<Map> getList() throws Exception;
}

package br.ufma.ppgee.eds.sistemacontroleestoque.adapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.Validation;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;

public class ProdutoAdapter implements AdapterInterface<Produto>{
    private ProdutoDAO dao;
    public ProdutoAdapter(){
         dao =new ProdutoDAO(SingletonConnectionDB.getConnection());
    }
    public String [] getColumns(){
        return new String[] {"id","nome","codigoDeBarras","preco"};
    }

    public Produto load(  Map<String,String>  values) throws ValidationFieldException{
        Validation.minMaxSizeString(values.get("nome"), "nome",5,100);
        Validation.minMaxSizeString(values.get("codigoDeBarras"), "codigoDeBarras",5,50);
        Validation.minmaxFloat(values.get("preco"), "preco", 0.001f,100000000f);


        Produto produto = new Produto();
        if(!(values.get("id")==null||values.get("id").isEmpty())){
            produto.setId(Integer.parseInt(values.get("id")));
        }
       
        produto.setNome(values.get("nome"));
        produto.setCodigoDeBarras(values.get("codigoDeBarras"));
        produto.setPreco(Double.parseDouble(values.get("preco")));
        return produto;

    }

    public Map<String,String> get(Produto produto){
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("id",produto.getId().toString());
        map.put("nome",produto.getNome());
        map.put("codigoDeBarras",produto.getCodigoDeBarras());
        map.put("preco",produto.getPreco().toString());
        return map;
    }
    
    public List<Map> getList() throws SQLException{
        List<Map> list=new ArrayList<Map>();
        
        dao.getAll().forEach(value -> {
            list.add(get(value));
        });

        return list;
    }
}

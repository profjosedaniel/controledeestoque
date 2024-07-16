package br.ufma.ppgee.eds.sistemacontroleestoque.adapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.Validation;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;


public class FabricanteAdapter implements AdapterInterface<Fabricante>{
    private FabricanteDAO dao;
    public FabricanteAdapter(){
         dao =new FabricanteDAO(SingletonConnectionDB.getConnection());
    }
    public String [] getColumns(){
        return new String[] {  "cnpj","nome","endereco","contato"};
    }

    public Fabricante load(  Map<String,String>  values) throws ValidationFieldException{
        Validation.minMaxSizeString(values.get("nome"), "nome",8,100);
        Validation.minMaxSizeString(values.get("endereco"), "endereco",8,100);
        Validation.telefone(values.get("contato"), "contato");
        



        Fabricante fabricante = new Fabricante();
        if(!(values.get("cnpj")==null||values.get("cnpj").isEmpty())){
            fabricante.setCnpj(values.get("cnpj"));
        }
        fabricante.setNome(values.get("nome"));
        fabricante.setEndereco(values.get("endereco"));
        fabricante.setContato(values.get("contato"));


       
        return fabricante;

    }

    public Map<String,String> get(Fabricante fabricante){
        HashMap<String,String> map=new HashMap<String,String>();
            
        map.put("cnpj",fabricante.getCnpj());
        map.put("nome",fabricante.getNome());
        map.put("endereco",fabricante.getEndereco());
        map.put("contato",fabricante.getContato());


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

package br.ufma.ppgee.eds.sistemacontroleestoque.adapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.Validation;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;

public class RepresentanteAdapter implements AdapterInterface<Representante>{
    private RepresentanteDAO dao;
    public RepresentanteAdapter(){
         dao =new RepresentanteDAO(SingletonConnectionDB.getConnection());
    }
    public String [] getColumns(){
        return new String[] {"cpf","nome","telefone","email"};
    }

    public Representante load(  Map<String,String>  values) throws ValidationFieldException{
        Validation.minMaxSizeString(values.get("nome"), "nome",8,100);
        Validation.cpf(values.get("cpf"), "cpf");
        Validation.telefone(values.get("telefone"), "telefone");
        Validation.email(values.get("email"), "email");
      
        Representante representante = new Representante();
        if(!(values.get("cpf")==null||values.get("cpf").isEmpty())){
            representante.setCpf(values.get("cpf"));
        }
        
        representante.setNome(values.get("nome"));
        representante.setTelefone(values.get("telefone"));
        representante.setEmail(values.get("email"));

      
        return representante;

    }

    public Map<String,String> get(Representante representante){
        HashMap<String,String> map=new HashMap<String,String>();
        
        map.put("cpf",representante.getCpf());
        map.put("nome",representante.getNome());
        map.put("telefone",representante.getTelefone());
        map.put("email",representante.getEmail());

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

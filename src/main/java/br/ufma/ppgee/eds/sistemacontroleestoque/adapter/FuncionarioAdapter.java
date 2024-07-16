package br.ufma.ppgee.eds.sistemacontroleestoque.adapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.Validation;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;


public class FuncionarioAdapter implements AdapterInterface<Funcionario>{
    private FuncionarioDAO dao;
    public FuncionarioAdapter(){
         dao =new FuncionarioDAO(SingletonConnectionDB.getConnection());
    }
    public String [] getColumns(){
        return new String[] {"cpf","nome","telefone","email"};
    }

    public Funcionario load(  Map<String,String>  values) throws ValidationFieldException{
        Validation.cpf(values.get("cpf"), "cpf");
        Validation.minMaxSizeString(values.get("nome"), "nome",8,100);
 
        Validation.email(values.get("email"), "email");
        Validation.telefone(values.get("telefone"), "telefone");
        if(values.get("password")!=null&&values.get("password").length()>0)
            Validation.password(values.get("password"), "password");
        

        Funcionario funcionario = new Funcionario();
        if(!(values.get("cpf")==null||values.get("cpf").isEmpty())){
            funcionario.setCpf(values.get("cpf"));
        }
        
        funcionario.setNome(values.get("nome"));
        funcionario.setTelefone(values.get("telefone"));
        funcionario.setEmail(values.get("email"));
        if(values.get("password")!=null&&values.get("password").length()>0)
            funcionario.setPassword(values.get("password"));
        try{
            if(values.get("papel")!=null)
                funcionario.setPapel( Funcionario.Papel.valueOf(values.get("papel").toUpperCase()));
      
        }catch(Exception e){
            throw new ValidationFieldException("papel","papel invalido",values.get("papel"));
        }
      
        return funcionario;

    }

    public Map<String,String> get(Funcionario funcionario){
        HashMap<String,String> map=new HashMap<String,String>();
        
        map.put("cpf",funcionario.getCpf());
        map.put("nome",funcionario.getNome());
        map.put("telefone",funcionario.getTelefone());
        map.put("email",funcionario.getEmail());
        map.put("password",funcionario.getPassword());
        map.put("papel",funcionario.getPapel().toString());

        return map;
    }
    
    public List<Map> getList() throws Exception{
        List<Map> list=new ArrayList<Map>();
        
        dao.getAll().forEach(produto -> {
            list.add(get(produto));
        });

        return list;
    }
}

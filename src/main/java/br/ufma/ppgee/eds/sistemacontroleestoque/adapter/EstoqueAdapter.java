package br.ufma.ppgee.eds.sistemacontroleestoque.adapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.Validation;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;


public class EstoqueAdapter implements AdapterInterface<Estoque>{
    private EstoqueDAO dao;
    public EstoqueAdapter(){
         dao =new EstoqueDAO(SingletonConnectionDB.getConnection());
    }
    public String [] getColumns(){
        return new String[] {   "id","nome","localizacao" };
    }

    public Estoque load(  Map<String,String>  values) throws ValidationFieldException{
        Validation.minMaxSizeString(values.get("nome"), "nome",8,100);
        Validation.minMaxSizeString(values.get("localizacao"), "localizacao",8,100);
        


        Estoque estoque = new Estoque();
        if(!(values.get("id")==null||values.get("id").isEmpty())){
            estoque.setId( Integer.parseInt( values.get("id")));
        }
        estoque.setNome(values.get("nome"));
        estoque.setLocalizacao(values.get("localizacao"));

       
        return estoque;

    }

    public Map<String,String> get(Estoque estoque){
        HashMap<String,String> map=new HashMap<String,String>();
   
        map.put("id",String.valueOf(estoque.getId()));
        map.put("nome",estoque.getNome());
        map.put("localizacao",estoque.getLocalizacao());

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

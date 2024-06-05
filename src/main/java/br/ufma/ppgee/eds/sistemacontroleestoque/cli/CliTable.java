package br.ufma.ppgee.eds.sistemacontroleestoque.cli;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;

public class CliTable {
    public static void main(String[] args) {
       
        try {
            ResultSet res = new FabricanteDAO(SingletonConnectionDB.getConnection() ).relatorioProdutoFabricante();
           // String cols[]={"cpf","funcionario","email","cnpj","fabricante"};
           
            new CliTable().visualize(res );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private int tam=15;

    public void visualize(ResultSet result) throws SQLException {
        visualize(result,getCollumns(result));
    }

    public void visualize(ResultSet result,String columns[]) {
        System.out.println(repeatString("_", columns.length*(tam+1)+1));
        for(String column:columns){
            System.out.print("¦");
            System.out.print(cell(column.toUpperCase(),tam));
            
        }
        System.out.println("¦");
        System.out.println("¦"+repeatString("=", columns.length*(tam+1)+1-2)+"¦");
        try {
            while(result.next()){
                for(String column:columns){
                    System.out.print("¦");
                    String value=result.getString(column);
                    System.out.print(cell(value,tam));
                }
                System.out.println("¦");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao visualizar");
            e.printStackTrace();
        }
     
        System.out.println("¦"+repeatString("_", columns.length*(tam+1)+1-2)+"¦");
    }
 

    private String cell(Object value, int tam) {
        if(value!=null)
            cell(value.toString(),tam);

        return cell( "",tam); 
    }

    private String cell(String value, int tam) {
        if(value==null)
            value="";

        if(value.length()>tam){
            return value.substring(0,tam-3)+"...";
        }else if(value.length()<tam){
           // return value+spaces(tam-value.length());
            return centerString(value,tam);
        }else{
            return value;
        }
    }

    private String  spaces(int n){ 
        return repeatString(" ",n);
    }

    private String repeatString(String s, int n) {
        return s.repeat(n);
    }

    private String[] getCollumns( ResultSet res) throws SQLException{
        ResultSetMetaData rsmd = res.getMetaData();
        int columnCount = rsmd.getColumnCount();

        String colluns[]=new String[columnCount];
        for (int i = 1; i <= columnCount; i++ ) {
            String name = rsmd.getColumnName(i);
            System.out.println(name);
            colluns[i-1]=name;
        }
        return colluns;
    }

    private String centerString(String s, int size) {
        int padSize = size - s.length();
        int padStart = s.length() + padSize / 2;
        s = String.format("%" + padStart + "s", s);
        s = String.format("%-" + size  + "s", s);
        return s;
    }
}

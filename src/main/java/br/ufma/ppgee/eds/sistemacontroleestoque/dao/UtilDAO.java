package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UtilDAO {
    public String[] getCollumns( ResultSet res) throws SQLException{
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

    public  List<Map> get( ResultSet res) throws SQLException{
        ArrayList list=new ArrayList<>();
        String[] columns = getCollumns(res);
        while(res.next()){
            HashMap<String,String> row=new HashMap<String,String>();
            for(String column:columns){
                row.put(column,res.getString(column));
            }
            list.add(row);

        }
        return list;
    }
}

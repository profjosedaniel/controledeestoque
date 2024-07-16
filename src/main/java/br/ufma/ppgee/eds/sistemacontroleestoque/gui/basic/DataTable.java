package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org.postgresql.util.PSQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ErroDataBase;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.CustomColumn;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;


public class DataTable extends  AbstractTableModel{
    private Set<Integer> changedRows = new HashSet<>();
    private Map<Integer,Color> rowCollors = new HashMap<Integer,Color>();

    private List<String> columnNames  ;
    private Class columnClass [];
    private List<Map> data;
    private List<ActionRow> saveRow;
    private String[] disableColumns;
    private List<String> hildenColumns;


    public void setData(List<Map> data){
        this.data=data;
    }
    public DataTable( List<String> columnNames, List<Map> data, List<ActionRow> saveRow,ArrayList<CustomColumn> customColumns, String[] disableColumns, List<String> hildenColumns) {
        this.columnNames = columnNames;
        this.data = data;
        this.saveRow=saveRow;
        this.disableColumns=disableColumns;

        this.hildenColumns=hildenColumns;
        System.out.println(this.hildenColumns);
        if( hildenColumns!=null && hildenColumns.size()>0){
            this.hildenColumns.stream().forEach(column->{
                columnNames.remove(column);
                System.out.println("Removendo coluna "+column);
            });
        }
       

        if(customColumns!=null && customColumns.size()>0){
            customColumns.forEach(col -> {columnNames.add(col.column()); } );
        }

        if(saveRow!=null && saveRow.size()>0){
            saveRow.forEach(action -> {columnNames.add(action.column()); } );
        }
      
    }
    
    public void addRow() {
        Map<String,String> row=new HashMap<String,String>();
        for(String column:columnNames){
            row.put(column,"");
        }
        data.add(row);
        fireTableDataChanged();
   
    }

    @Override
    public int getRowCount() {
         return data.size();
    }
    int getIndexColumn(String col){
        return columnNames.indexOf(col);
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String name=columnNames.get(columnIndex);
        if (saveRow!=null) {
            for(ActionRow action:saveRow){
                if(action.column().equals(name)){
                    boolean checkValue=data.contains(rowIndex);
                    Map<String,String> row= ( Map<String,String>)data.get(rowIndex);
                    if(checkValue  ){
                        return action.get(row.get(name),row);
                    }else{
                        return action.get(null,row);
                    }
                    
                }
            }
        }
        

        
        Map<String,String> row= ( Map<String,String>)data.get(rowIndex);
        return row.get(name);
        
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
        
    }
    public boolean isCellEditable(int row, int column) {
       // return column!=0;
       if(disableColumns!=null){
           for(String col:disableColumns){
               if(columnNames.get(column).equals(col)){
                   return false;
               }
           }

       }
        return true;
     }

     public void save(int row,ActionRow action) {
        System.out.println("Salvando linha " + row);
        Map p=data.get(row);
        System.out.println(p.get("id"));
        try{
            action.action(p);
            rowCollors.put(row,Color.GREEN);
        }catch (ValidationFieldException e){
            rowCollors.put(row,Color.RED);
            String errorMessage=e.getText();
            JOptionPane.showMessageDialog( null, "Erro !"+errorMessage);
            
        }catch (PSQLException e){
            rowCollors.put(row,Color.RED);
            String errorMessage=new ErroDataBase().getMessageErro(e);
            JOptionPane.showMessageDialog( null, "Erro ao processar "+action.column()+" na linha "+row+" \nErro: "+errorMessage);
            
        }catch (Exception e){
            rowCollors.put(row,Color.RED);
            JOptionPane.showMessageDialog( null, "Erro ao processar "+action.column()+" na linha "+row+" \nErro:"+e.getMessage());
            e.printStackTrace();
        }
       
      }

     @Override
     public void setValueAt(Object value, int row, int col) {
            String name=columnNames.get(col);
            if(saveRow!=null){
                for(ActionRow action:saveRow){
                    if(action.column().equals(name)){
                        save(row,action);
                        return ;
                    }
                }
            }
           

                Map<String,String> rowMap= ( Map<String,String>)data.get(row);
                rowMap.put(columnNames.get(col),value.toString());
                rowCollors.put(row,Color.YELLOW);
                fireTableCellUpdated(row, col);
            
   
     }
     
     
 
     public boolean isRowChanged(int row) {
        return changedRows.contains(row);
    }

    public Color getColor(int row) {
        if(rowCollors.containsKey(row)){
            return rowCollors.get(row);
        }else{
            return Color.WHITE;
        }
        
        
    }

}

package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class DataTable extends  AbstractTableModel{
    private Set<Integer> changedRows = new HashSet<>();
    private Map<Integer,Color> rowCollors = new HashMap<Integer,Color>();

    private List<String> columnNames  ;
    private Class columnClass [];
    private List<Map> data;
    private List<ActionRow> saveRow;

    public DataTable( List<String> columnNames, List<Map> data, List<ActionRow> saveRow) {
        this.columnNames = columnNames;
        this.data = data;
        this.saveRow=saveRow;
        saveRow.forEach(action -> {columnNames.add(action.column()); } );
        
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
        for(ActionRow action:saveRow){
            if(action.column().equals(name)){
                return action.get(null);
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
        return column!=0;
     }

     public void save(int row,ActionRow action) {
        System.out.println("Salvando linha " + row);
        Map p=data.get(row);
        System.out.println(p.get("id"));
        try{
            action.action(p);
             
            rowCollors.put(row,Color.GREEN);
        }catch (Exception e){
            rowCollors.put(row,Color.RED);
            JOptionPane.showMessageDialog( null, "Erro ao salvar linha "+row);
            e.printStackTrace();
        }
       
      }

     @Override
     public void setValueAt(Object value, int row, int col) {
            String name=columnNames.get(col);
            for(ActionRow action:saveRow){
                if(action.column().equals(name)){
                    save(row,action);
                    return ;
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

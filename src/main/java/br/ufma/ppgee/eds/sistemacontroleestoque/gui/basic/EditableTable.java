package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class EditableTable extends JPanel{
    private List<Map> data;
    private String columns[];
    private List<ActionRow> actions;

    public EditableTable(String columns[], List<Map> data, List<ActionRow> actions){
        super();
 
        this.columns=columns;
        this.data=data;
        this.actions=actions;
       
        show();
    }
    public void show(){
        try{
            ArrayList values=new ArrayList<String>();
            for(String column:columns){
                values.add(column);
            }
            DataTable model = new DataTable(values,data, actions);
            JTable table = new JTable(model);
            table.setDefaultRenderer(Object.class, new CustomTableCellRenderer(model));
            actions.forEach(actions->{
              
                table.getColumnModel().getColumn(model.getIndexColumn(actions.column())).setCellRenderer(actions.render());
                table.getColumnModel().getColumn(model.getIndexColumn(actions.column())).setCellEditor(new IconEditor(new JCheckBox(), table, model));
            });
        
            
            JButton btn=new JButton("Add Row");
            btn.addActionListener(e->{
                model.addRow();
            });

            int defaultRowHeight = table.getRowHeight();
            table.setRowHeight(defaultRowHeight * 2); // Dobra a altura das linhas
    
            setLayout(new BorderLayout());
            this.add(btn,BorderLayout.NORTH);
            this.add(new JScrollPane(table));

        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

}
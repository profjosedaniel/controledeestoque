package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.CustomColumn;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.CustomTableCellRenderer;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.IconEditor;

public class EditableTable extends JPanel{
    protected List<Map> data;
    protected String columns[];
    private List<ActionRow> actions;
    private ArrayList<CustomColumn> customColumns;
    private String[] disableColumns;
    protected JPanel panel;
    private List<String> hildeColumns;
    public EditableTable(String columns[], List<Map> data, List<ActionRow> actions, ArrayList<CustomColumn> customColumns){
        this(columns, data, actions, customColumns, null);
        this.panel=new JPanel();
        this.add(panel);
    }

    public void setHildeColumns(String[] hildeColumns) {
        this.hildeColumns = List.of(hildeColumns);
    }

    public EditableTable(String columns[], List<Map> data, List<ActionRow> actions, ArrayList<CustomColumn> customColumns,String disableColumns[]){
        super();
 
        this.columns=columns;
        this.data=data;
        this.actions=actions;
        this.customColumns=customColumns;
        this.disableColumns=disableColumns;
        
    }
    public void show(){
        show(null);
    }

    protected DataTable model;
    public void show(JPanel panel2){
        try{
            ArrayList values=new ArrayList<String>();
            //colunas de texto
            for(String column:columns){
                values.add(column);
            }
           
            model = new DataTable(values,data, actions,customColumns,disableColumns,hildeColumns);
            JTable table = new JTable(model);
            table.setDefaultRenderer(Object.class, new CustomTableCellRenderer(model));

            if(customColumns!=null){
                customColumns.forEach(customColumn->{
                    int index=model.getIndexColumn(customColumn.column());
                    table.getColumnModel().getColumn(index).setCellEditor(new DefaultCellEditor( customColumn.getComponent()));
                });

            }

            
            if(actions!=null){
                actions.forEach(actions->{
                    table.getColumnModel().getColumn(model.getIndexColumn(actions.column())).setCellRenderer(actions.render());
                    table.getColumnModel().getColumn(model.getIndexColumn(actions.column())).setCellEditor(new IconEditor(new JCheckBox(), table, model));
                });    
            }
            int defaultRowHeight = table.getRowHeight();
            table.setRowHeight(defaultRowHeight * 2); // Dobra a altura das linhas
    
            setLayout(new BorderLayout());

            if(panel2!=null){
                this.add(panel2,BorderLayout.SOUTH);
            }

            if(actions!=null){
                JButton btn=new JButton("Add Row");
                btn.addActionListener(e->{
                    model.addRow();
                });
                this.add(btn,BorderLayout.NORTH);
            }
            
            this.add(new JScrollPane(table));

        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

}
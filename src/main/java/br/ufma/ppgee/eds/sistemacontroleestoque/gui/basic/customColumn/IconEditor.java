package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;

import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.DataTable;

public class IconEditor extends DefaultCellEditor {
    protected JButton button;
    private boolean isPushed;
    private JTable table;
    private DataTable model;

    public IconEditor(JCheckBox checkBox, JTable table, DataTable model) {
        super(checkBox);
        this.table = table;
        this.model = model;
        button = new JButton();
        button.setOpaque(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                model.fireTableDataChanged();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value != null && value instanceof Icon) {
            button.setIcon((Icon) value);
            isPushed = true;
        }else if(value != null && value instanceof String){
            button.setText((String)value);
            button.setVisible(true);
            isPushed = true;
        } 
      
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Lógica para salvar a linha alterada
            int row = table.getSelectedRow();
            System.out.println("Saving row " + row);

        }
        isPushed = false;
        return button.getIcon();
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
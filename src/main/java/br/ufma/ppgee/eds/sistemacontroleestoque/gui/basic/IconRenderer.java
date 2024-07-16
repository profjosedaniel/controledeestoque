package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class IconRenderer extends JButton implements TableCellRenderer {
    public IconRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null && value instanceof Icon) {
            setIcon((Icon) value);
        }
        return this;
    }
}
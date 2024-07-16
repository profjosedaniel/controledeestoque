package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;

import java.util.Map;

import javax.swing.table.TableCellRenderer;

public interface ActionRow{
    
    void action(Map<String,String> row)  throws Exception;
    String column() ;
    TableCellRenderer render();
    Object get(Object value);
}

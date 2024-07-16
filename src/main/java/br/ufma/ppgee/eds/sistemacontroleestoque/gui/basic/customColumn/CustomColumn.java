package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn;

import java.awt.Component;
import java.awt.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;

public class CustomColumn {

    private Object[] values;
    private String name;
    private Object[] tooltips;

    public CustomColumn(Object values[],String name){
        
        this(values, name, null);
    }
    public CustomColumn(Object values[],String name,Object tooltips[]){
        this.values=values;
        this.name=name;
        this.tooltips=tooltips;
    }
    
  
    public JComboBox getComponent(){
        JComboBox comb=new JComboBox<>(values);
       
        if(this.tooltips!=null){
            System.out.println(name+":"+tooltips);
            comb.setToolTipText("daniel");
    

            DefaultListCellRenderer renderer=  new DefaultListCellRenderer() {
     
                @Override
                public Component getListCellRendererComponent(JList list, Object value,
                                    int index, boolean isSelected, boolean cellHasFocus) {
            
                    JComponent comp = (JComponent) super.getListCellRendererComponent(list,
                            value, index, isSelected, cellHasFocus);
                        if(-1 < index && null != value && null != tooltips){
                            System.out.println(""+tooltips[index]);
                            list.setToolTipText(""+tooltips[index]);
                        
                        }
                     
                    return comp;
                }
            
              
            };
             comb.setRenderer(renderer);
            // for (Object value : values) {
            //     comb.addItem(value);
            // }
        }
      
        return comb;
    }

    public String column(){
        return this.name;
    }

 
}

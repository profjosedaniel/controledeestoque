package br.ufma.ppgee.eds.sistemacontroleestoque.gui;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

public class Desing {
     public void desing(){
        UIManager.put("Button.background", java.awt.Color.CYAN);
        UIManager.put("Button.foreground", java.awt.Color.BLACK);
  
        UIManager.put("Button.border", BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        UIManager.put("Button.opaque", true);
        UIManager.put("Button.contentAreaFilled", false);

        UIManager.put("TextField.background", java.awt.Color.WHITE);
        UIManager.put("TextField.foreground", java.awt.Color.BLACK);
        UIManager.put("TextField.font", new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        UIManager.put("TextField.opaque", true);
        UIManager.put("TextField.contentAreaFilled", false);

        UIManager.put("Label.background", java.awt.Color.WHITE);
        UIManager.put("Label.foreground", java.awt.Color.BLACK);
        UIManager.put("Label.font", new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        UIManager.put("Label.border", BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        UIManager.put("Label.opaque", true);
        UIManager.put("Label.contentAreaFilled", false);

        UIManager.put("Panel.background", java.awt.Color.WHITE);
        UIManager.put("Panel.foreground", java.awt.Color.BLACK);
        UIManager.put("Panel.font", new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        UIManager.put("Panel.border", BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        UIManager.put("Panel.opaque", true);
        UIManager.put("Panel.contentAreaFilled", false);

        UIManager.put("Table.background", java.awt.Color.WHITE);
        UIManager.put("Table.foreground", java.awt.Color.BLACK);
        UIManager.put("Table.font", new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        UIManager.put("Table.border", BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        UIManager.put("Table.opaque", true);
        UIManager.put("Table.contentAreaFilled", false);
        
        UIManager.put("Menu.background", java.awt.Color.WHITE);
        UIManager.put("Menu.foreground", java.awt.Color.BLACK);
        UIManager.put("Menu.font", new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        UIManager.put("Menu.border", BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        UIManager.put("Menu.opaque", true);
        UIManager.put("Menu.contentAreaFilled", false);

        //JCheckBox
        UIManager.put("CheckBox.background", java.awt.Color.WHITE);
        UIManager.put("CheckBox.foreground", java.awt.Color.BLACK);
        UIManager.put("CheckBox.font", new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        UIManager.put("CheckBox.border", BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        UIManager.put("CheckBox.opaque", true);
        UIManager.put("CheckBox.contentAreaFilled", false);

        
    }
}

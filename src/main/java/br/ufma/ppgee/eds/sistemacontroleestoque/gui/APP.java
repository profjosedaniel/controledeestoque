package br.ufma.ppgee.eds.sistemacontroleestoque.gui;
import javax.swing.SwingUtilities;

import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.MainFrame;
 
public class APP {
     public static void main(String[] args) {
      
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
     
            }
        });
     }

     
}

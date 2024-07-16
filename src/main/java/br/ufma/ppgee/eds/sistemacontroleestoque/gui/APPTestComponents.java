package br.ufma.ppgee.eds.sistemacontroleestoque.gui;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.checkerframework.checker.units.qual.t;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ListEntities;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

public class APPTestComponents {
     public static void main(String[] args) {
      
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               testList();
            }
        });
     }

     public static void testList(){
         ProdutoDAO dao = new ProdutoDAO(SingletonConnectionDB.getConnection());
        
         ArrayList all = new ArrayList<>();
         ArrayList selected = new ArrayList<>();
          
         try {
            all=(ArrayList) dao.getAll();
            selected=new ArrayList<>(all);
            selected.remove(1);
            selected.remove(2);
          
         } catch (Exception e) {
             e.printStackTrace();
         }
        

        JFrame frame=new JFrame();
        frame.getContentPane().add(new ListEntities(all,selected));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

     }
}

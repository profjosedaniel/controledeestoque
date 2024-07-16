package br.ufma.ppgee.eds.sistemacontroleestoque.gui;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.checkerframework.checker.units.qual.t;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;

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
      
     }
}

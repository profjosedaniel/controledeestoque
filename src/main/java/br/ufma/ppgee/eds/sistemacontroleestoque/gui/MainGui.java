package br.ufma.ppgee.eds.sistemacontroleestoque.gui;
import javax.swing.SwingUtilities;

import br.ufma.ppgee.eds.sistemacontroleestoque.gui.MainFrame;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.login.LoginGUI;
 
public class MainGui {
     public static void main(String[] args) {
      new Desing().desing();
        Splash splash = new Splash(2000);
        splash.showSplashAndExit();      
     }
}

package br.ufma.ppgee.eds.sistemacontroleestoque.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import br.ufma.ppgee.eds.sistemacontroleestoque.gui.login.LoginGUI;

public class Splash  extends JWindow {
        private int duration;
    
    public Splash(int d) {
        duration = d;
    }
    
// Este é um método simples para mostrar uma tela de apresentção

// no centro da tela durante a quantidade de tempo passada no construtor

    public void showSplash() {        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        
        // Configura a posição e o tamanho da janela
        int width = 450;
        int height =450;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        // Constrói o splash screen
        String url="C:\\Users\\Daniel\\OneDrive\\doutorado 2024\\disciplinas\\engenharia de software\\projeto\\sistemacontroleestoque\\assert\\img\\splash.png";
        JLabel label = new JLabel(new ImageIcon(url));
        JLabel copyrt = new JLabel
                ("José Daniel Pereira Ribeiro Filho", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color oraRed = new Color(156, 20, 20,  255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));        
        // Torna visível
        setVisible(true);
        
        // Espera ate que os recursos estejam carregados
        try { Thread.sleep(duration); } catch (Exception e) {}        
        setVisible(false);        
    }
    
    public void showSplashAndExit() {        
        showSplash();
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI();    
            }
        });
           
    }
    
    public static void main(String[] args) {        
        // Mostra uma imagem com o título da aplicação
        Splash splash = new Splash(2000);
        splash.showSplashAndExit();        
    }
}

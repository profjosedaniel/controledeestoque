package br.ufma.ppgee.eds.sistemacontroleestoque.gui.login;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.Desing;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.MainFrame;

public class LoginGUI extends JFrame {
    public static void main(String[] args) {
        new LoginGUI();
    }
    public LoginGUI(){
        new Desing().desing();
        JPanel panel=new JPanel( );
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel labelUSuario=new JLabel("Usuário");
        JTextField txtUsuario=new JTextField();
        JLabel labelSenha=new JLabel("Senha");
        JTextField txtSenha=new JPasswordField();
        JButton btn=new JButton("Entrar");
        panel.add(labelUSuario);
        panel.add(txtUsuario);
        panel.add(labelSenha);
        panel.add(txtSenha);
        panel.add(btn);
       
        getContentPane().add(panel);

        btn.addActionListener(e->{
            String usu=txtUsuario.getText();
            String sen=txtSenha.getText();
            FuncionarioDAO dao = new FuncionarioDAO(new SingletonConnectionDB().getConnection());
            try{
                if(dao.login(usu, sen)!=null){
                    new MainFrame();
                    this.dispose();
                    return;
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Usuário e/ou senha inválidos!");

        });
        setTitle("Login");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLComprador;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLGerente;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLVendedor;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario.Papel;
import br.ufma.util.LerTerminal;

public class CLILogin {
    public static Funcionario usuarioLogado;
    public static ACLInterface papelACL;

    public static void main(String[] args) {
        new CLILogin().show();
    }
    public void show(){
        LerTerminal terminal=new LerTerminal();
        FuncionarioDAO loginDAO = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        System.out.println("===========Login=========");
        String cpf = terminal.nextLine("CPF",true);
        String password = terminal.nextLine("Senha",true);

        Funcionario u;
        try {
            u = loginDAO.login(cpf, password);
            if(u!=null){
                System.out.println("=======================================");
                System.out.println("Bem vindo "+u.getNome());
                System.out.println("=======================================");
                usuarioLogado=u;
                setPapel(u.getPapel());
                
            }else{
                System.out.println("Usuario ou senha invalidos");
            }
        } catch (SQLException e) {
            System.out.println("Usuario ou senha invalidos");
            System.out.println(e.getMessage());
        }
        
    }
    public void setPapel(Papel papel){
        if(papel==Papel.GERENTE){
            papelACL = new ACLGerente();
        }else if(papel==Papel.VENDEDOR){
            papelACL = new ACLVendedor();
        }else if(papel==Papel.COMPRADOR){
            papelACL = new ACLComprador();
        }else{
            System.exit(0);
        }
    }

}

package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLComprador;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLGerente;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLVendedor;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.PapelUsuario;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario.Papel;
import br.ufma.util.LerTerminal;

public class CLILogin {
 
  

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
        
                PapelUsuario.setUsuario(u);
                
            }else{
                System.out.println("Usuario ou senha invalidos");
            }
        } catch (SQLException e) {
            System.out.println("Usuario ou senha invalidos");
            System.out.println(e.getMessage());
        }
        
    }
 

}

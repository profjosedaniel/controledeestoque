package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario.Papel;
import br.ufma.util.LerTerminal;

public class CLIFuncionario extends CLIAbstractCRUD<Funcionario>{
    public static void main(String[] args) {
        CLIFuncionario cli = new CLIFuncionario(new LerTerminal());
        cli.show();
    }
    public CLIFuncionario(LerTerminal terminal) {
        super(terminal);
     }

    @Override
    public void opcoesMenu() {
        super.opcoesMenu();
        System.out.println("6 - Alterar senha");
        System.out.println("7 - Alterar papel");
    }

    @Override
    public boolean acoesMenu(int opcao) {
        if(opcao==6){
            changePassword();
            return true;
        }else if(opcao==7){
            changePapel();
            return true;
        }else{
            return super.acoesMenu(opcao);
        }
       
    }
    @Override
    public FuncionarioDAO getDAO() {
         return new FuncionarioDAO( SingletonConnectionDB.getConnection()); 
    }
    @Override
    public String getName() {
        return Funcionario.class.getSimpleName();
    }
     @Override
    public void showEntity(Funcionario o) {
        System.out.println("=============Funcionario=================");
        System.out.println("CPF: " + o.getCpf());
        System.out.println("Nome: " + o.getNome());
        System.out.println("Email: " + o.getEmail());
        System.out.println("Telefone: " + o.getTelefone());
        if(o.getPapel()!=null )
            System.out.println("Papel: " + o.getPapel());
        System.out.println("=======================================");
    }

    @Override
    public Funcionario get() throws SQLException {
        System.out.println("Digite o CPF do funcionario");
        String cpf = terminal.nextLine(null, false);
        return getDAO().get(cpf);
        
    }

    @Override
    public Funcionario create() {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(terminal.nextLine("CPF", true));   
        funcionario.setNome(terminal.nextLine("Nome", false)); 
        funcionario.setEmail(terminal.nextLine("Email", false));
        funcionario.setTelefone(terminal.nextLine("Telefone", false));
        changePassword(funcionario);
        changePapel(funcionario);
        return funcionario;
        
    }

    @Override
    public Funcionario update(Funcionario funcionario) { 
        funcionario.setCpf(terminal.nextLine("CPF", true));   
        funcionario.setNome(terminal.nextLine("Nome", false)); 
        funcionario.setEmail(terminal.nextLine("Email", false));
        funcionario.setTelefone(terminal.nextLine("Telefone", false));
        changePassword(funcionario);
        return funcionario;
        
    }

 
    public String changePassword(Funcionario funcionario) { 
        //String oldPassword=terminal.nextLine("CPF", true);   
        do{
            System.out.println("Digite a senha com 8 caracteres no mínimo duas vezes");
            String newPassword=terminal.nextLine("Senha", true);   
            String newPassword2=terminal.nextLine("Senha", true);   
            if(!newPassword.equals(newPassword2)){
                System.out.println("Senhas não conferem");
            }else if(newPassword.length()<8){
                System.out.println("Senha deve ter no mínimo 8 caracteres");
            }else{
                return newPassword;
            }
        }while(true);
        
    }
    public void changePapel(Funcionario funcionario) { 
        //String oldPassword=terminal.nextLine("CPF", true);   
        System.out.println("Digite o papel do funcionário");
        System.out.println("1 - Vendedor");
        System.out.println("2 - Gerente");
        System.out.println("3 - COMPRADOR");
        int papel=terminal.nextInt("Papel", true);
        switch (papel) {
            case 1:
                funcionario.setPapel(Papel.VENDEDOR);
                break;
            case 2:
                funcionario.setPapel(Papel.GERENTE);
                break;
            case 3:
                funcionario.setPapel(Papel.COMPRADOR);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    public void changePapel() { 
        Funcionario funcionario;
        try {
            funcionario = get();
            if(funcionario!=null){
                changePapel(funcionario);
                getDAO().update(funcionario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao alterar papel");
            e.printStackTrace();
        }

    }
    public void changePassword(){
        Funcionario funcionario;
        try {
            funcionario = get();
            if(funcionario != null){
                String senha=changePassword(funcionario);
                getDAO().updatePassword(funcionario.getCpf(), senha);
               
            
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao alterar senha");
            e.printStackTrace();
        }

    }

}

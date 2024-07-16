package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

 
import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Estoque;
import br.ufma.util.LerTerminal;

public class CLIEstoque extends CLIAbstractCRUD<Estoque> {
   
    private EstoqueDAO estoqueDAO;

    @Override
    public void opcoesMenu() {
        super.opcoesMenu();
        System.out.println("6 - Listar produtos em estoque");
    }

    @Override
    public boolean acoesMenu(int opcao) {
        if(opcao == 6){
            relatorio();
            return true;
        }
        return super.acoesMenu(opcao);
    }

    public static void main(String[] args) {
            new CLIEstoque(new LerTerminal()).show();
            
    }
    public CLIEstoque( LerTerminal terminal){
        super(terminal);
        this.estoqueDAO = new EstoqueDAO( SingletonConnectionDB.getConnection());
    }
     @Override
     public String getName() {
         return Estoque.class.getSimpleName();
     }
    @Override
    public EstoqueDAO getDAO() {
        return  this.estoqueDAO;
    }
    @Override
    public void showEntity(Estoque o) {
        System.out.println("============Estoque================");
        System.out.println("ID: " + o.getId());
    //    System.out.println("Quantidade: " + o.getQuantidade());
        System.out.println("Nome: " + o.getNome());
        System.out.println("LOcalização: " + o.getLocalizacao());
        System.out.println("==================================");

    }
    @Override
    public Estoque get() throws Exception{
        int id = terminal.nextInt("Id estoque",false);
         
        return getDAO().get(id);
    }
    
    @Override
    public Estoque create() {
        Estoque estoque = new Estoque();

    //    estoque.setQuantidade(terminal.nextInt("quantidade",true));
        estoque.setLocalizacao( terminal.nextLine("localização",true));
        estoque.setNome( terminal.nextLine("nome",true));
        return estoque;

    }
    @Override
    public Estoque update(Estoque estoque) {
         
    //    estoque.setQuantidade(terminal.nextInt("quantidade",true,estoque.getQuantidade()));
        estoque.setLocalizacao( terminal.nextLine("localização",true,estoque.getLocalizacao()));
        estoque.setNome( terminal.nextLine("nome",true,estoque.getNome()));
        return estoque;
    }
          
    void relatorio() {
        try {
            new CliTable().visualize(getDAO().getAllResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

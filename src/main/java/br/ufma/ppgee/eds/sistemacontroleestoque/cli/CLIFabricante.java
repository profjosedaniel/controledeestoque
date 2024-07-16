package br.ufma.ppgee.eds.sistemacontroleestoque.cli;



import java.sql.ResultSet;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.util.LerTerminal;

public class CLIFabricante extends CLIAbstractCRUD<Fabricante>{
    
    private FabricanteDAO fabricanteDAO;

    public static void main(String[] args) {
        new CLIFabricante(new LerTerminal()).show();
    }
    public CLIFabricante(LerTerminal terminal){
        super(terminal);
        fabricanteDAO = new FabricanteDAO( SingletonConnectionDB.getConnection());
    }
    @Override
    public void opcoesMenu() {
        super.opcoesMenu();
        System.out.println("6 - Adicionar Produtos ao Fabricante");
        System.out.println("7 - Remover Produtos ao Fabricante");
        System.out.println("8 - Relatorio Produtos ao Fabricante");
    }

    @Override
    public boolean acoesMenu(int opcao) {
        if(opcao==6){
            adicionarProduto();
            return true;
        }else if(opcao==7){
            removerProduto();
            return true;
        }else if(opcao==8){
            relatorioProdutoFabricante();
            return true;
        }

        return super.acoesMenu(opcao);
    }
    
    @Override
    public void showEntity(Fabricante o) {
        System.out.println("===========Fabricante=========");
        System.out.println("Nome: " + o.getNome());
        System.out.println("CNPJ: " + o.getCnpj());
        System.out.println("Contato: " + o.getContato());
        System.out.println("Endereço: " + o.getEndereco());
        System.out.println("=======================================");

    }

    @Override
    public Fabricante get() throws Exception{
        String cnpj = terminal.nextLine("Digite o id do fabricante",false);
        Fabricante fabricante = fabricanteDAO.get(cnpj);
        return  fabricante;
         
    }

    @Override
    public Fabricante create() {

        try{

            Fabricante fabricante = new Fabricante();
            fabricante.setCnpj( terminal.nextLine("CNPJ",true));
            fabricante.setNome(terminal.nextLine("Nome",true));
            fabricante.setContato(terminal.nextLine("contato",true));
            fabricante.setEndereco(terminal.nextLine("endereço",true));
                
            return fabricante;
        }catch (Exception e){
            System.out.println("Fabricante não encontrado");
        }
        return null;
    }

    @Override
    public Fabricante update(Fabricante fabricante) {
         
        fabricante.setNome(terminal.nextLine("Nome",true,""+fabricante.getNome()));
        fabricante.setContato(terminal.nextLine("contato",true,""+fabricante.getContato()));
        fabricante.setEndereco(terminal.nextLine("endereço",true,""+fabricante.getEndereco()));
                
        return fabricante;
           
    }
    
    @Override
    public FabricanteDAO getDAO() { 
        return new FabricanteDAO( SingletonConnectionDB.getConnection());
    }
    @Override
    public String getName() {
        return Fabricante.class.getSimpleName();
    }

    public void adicionarProduto() {
        try {
            Produto produto= new CLIProduto(terminal).get();
            Fabricante fabricante = get();
            getDAO().relacionarProdutoFabricante(produto, fabricante);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto");
         //   e.printStackTrace();
        }
        
    }
    public void removerProduto() {
        try {
            Produto produto= new CLIProduto(terminal).get();
            Fabricante fabricante = get();
            getDAO().removerRelacaoProdutoFabricante(produto, fabricante);
        } catch (Exception e) {
            System.out.println("Erro ao remover produto");
          //  e.printStackTrace();
        }
        
    }
    
    public void relatorioProdutoFabricante() {
        try {
            System.out.println("Relatório de produtos por fabricante");
            System.out.println("=======================================");
            ResultSet relatorio = getDAO().relatorioProdutoFabricante();

            new CliTable().visualize(relatorio);
        } catch (Exception e) {
            System.out.println("Erro ao exibir relatório");
        }
    }
}

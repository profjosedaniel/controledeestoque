package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import java.sql.SQLException;
 
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.util.LerTerminal;

public class CLIProduto extends CLIAbstractCRUD<Produto>{
    private ProdutoDAO produtoDAO;
    
    @Override
    public void opcoesMenu() {
      
        super.opcoesMenu();
        System.out.println("6 - Relatório de produtos");
    }
    @Override
    public boolean acoesMenu(int opcao) {
        if(opcao==6){
            relatorio();
            return true;
        }
        return super.acoesMenu(opcao);
    }

    public static void main(String[] args) {
        
        new CLIProduto(new LerTerminal()).show();
    }

    public CLIProduto(LerTerminal terminal){
        super(terminal);
        SingletonConnectionDB.getConnection();
        produtoDAO=new ProdutoDAO( SingletonConnectionDB.getConnection());
        
    }
    
    @Override
    public ProdutoDAO getDAO() {
        return produtoDAO;
 
    }
    @Override
    public String getName() {
        return Produto.class.getSimpleName();
    }
    public void showEntity(Produto produto) {
        System.out.println("============Produto================");

        System.out.println("Id: " + produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Descrição: " + produto.getDescricao()); 
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Código de barras: " + produto.getCodigoDeBarras());
        // System.out.println("Quantidade em estoque: " + produto.getQuantidadeEmEstoque());
        System.out.println("=======================================");
    }


    public Produto get() throws SQLException {
        System.out.println("\n\n********************************************");
        Integer id = terminal.nextInt("Id produto",false);
        
        Produto produto = produtoDAO.get(id);
        return produto;
    }



    public Produto create() {
        
        Produto produto = new Produto();
        return update(produto);
    }

    public Produto update(Produto produto) {
        produto.setNome(terminal.nextString("nome",true));
        produto.setDescricao(terminal.nextLine("descrição",true));
        produto.setPreco(terminal.nextDouble("preço",true));
        // produto.setQuantidadeEmEstoque(terminal.nextInt("quantidade",true));
        produto.setCodigoDeBarras(terminal.nextString("código de barras",true));
        return produto;
         
    }
    void relatorio() {
        try {
            new CliTable().visualize(getDAO().relatorio());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
}
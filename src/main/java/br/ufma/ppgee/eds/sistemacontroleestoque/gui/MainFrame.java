package br.ufma.ppgee.eds.sistemacontroleestoque.gui;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLGerente;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.ACLInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.acl.PapelUsuario;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.EstoqueAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.FabricanteAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.FuncionarioAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.MovimentacaoAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.ProdutoAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.RepresentanteAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ArmazenamentoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.MovimentacaoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.UtilDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Movimentacao;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.EditableTable;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.SearchTable;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.CustomColumn;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.estoque.DeleteEstoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.estoque.SaveEstoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.fabricante.DeleteFabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.fabricante.FabricanteRepresentanteController;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.fabricante.SaveFabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.funcionario.DeleteFuncionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.funcionario.FuncionarioSenhaController;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.funcionario.SaveFuncionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.movimentacao.SaveMovimentacaoController;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.produto.DeleteProductController;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.produto.FabricanteProdutoController;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.produto.SaveProductController;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.representante.DeleteRepresentante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.representante.SaveRepresentante;
 
 

public class MainFrame extends JFrame{
    JPanel mainPanel;
    private EditableTable table;
    private JLabel title;

    public void setContent(String title,JComponent comp){
    
        getContentPane().removeAll();
        repaint();
        getContentPane().add(comp);
        getContentPane().setVisible(false);
        getContentPane().setVisible(true);
        repaint();
        
    }

   
    public MainFrame(){
        super();
        new Desing().desing();
     
        
        menu();
        

        this.setTitle("Sistema de Controle de Estoque");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setSize(1000, 800);
        this.setVisible(true);

        mainPanel=new JPanel();
        title=new JLabel();
        
        getContentPane().add(title);
        getContentPane().add(mainPanel);
    }

    public void estoqueTable(){
        System.out.println("abrir tabela de estoqueTable");
      

        ArrayList<ActionRow> actions=new ArrayList<ActionRow>();
        actions.add(new SaveEstoque());
        actions.add(new DeleteEstoque());
   
        ArrayList<CustomColumn> customColumns=new ArrayList<CustomColumn>();
 
        try {
            EstoqueAdapter adapter=new EstoqueAdapter();
            SearchTable table = new SearchTable(adapter.getColumns(),adapter.getList(),actions,customColumns);
            table.setHildeColumns(new String[]{"id"});
            table.show();
            setContent("Estoque", table);
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fabricanteTable(){
        System.out.println("abrir tabela de FuncionarioTable");
      
        ArrayList<ActionRow> actions=new ArrayList<ActionRow>();
        actions.add(new SaveFabricante());
        actions.add(new DeleteFabricante());
        actions.add(new FabricanteRepresentanteController());
   
        ArrayList<CustomColumn> customColumns=new ArrayList<CustomColumn>();
 
        try {
            FabricanteAdapter adapter=new FabricanteAdapter();
            SearchTable table = new SearchTable(adapter.getColumns(),adapter.getList(),actions,customColumns);
            table.show();
            setContent("Fabricantes",table);
         
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void representanteTable(){
        System.out.println("abrir tabela de FuncionarioTable");
      
        ArrayList<ActionRow> actions=new ArrayList<ActionRow>();
        actions.add(new SaveRepresentante());
        actions.add(new DeleteRepresentante());
   
        ArrayList<CustomColumn> customColumns=new ArrayList<CustomColumn>();
 
        try {
            RepresentanteAdapter adapter=new RepresentanteAdapter();
           
            SearchTable table=new SearchTable(adapter.getColumns(),adapter.getList(),actions,customColumns);
            table.show();
            setContent(" Representantes", table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void funcionarioTable(){
        System.out.println("abrir tabela de FuncionarioTable");
        

        ArrayList<ActionRow> actions=new ArrayList<ActionRow>();
        actions.add(new SaveFuncionario());
        actions.add(new DeleteFuncionario());
        actions.add(new FuncionarioSenhaController());
   
        ArrayList<CustomColumn> customColumns=new ArrayList<CustomColumn>();
        String[] values = new String[]{"GERENTE", "VENDEDOR","COMPRADOR"} ;
        customColumns.add(new CustomColumn(values,"papel"));
        try {
           
            FuncionarioAdapter adapter=new FuncionarioAdapter();
            String[] cols = adapter.getColumns();
         
            SearchTable table= new SearchTable(adapter.getColumns(),adapter.getList(),actions,customColumns);
            table.show();
            setContent(" Funcionarios ", table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void produtoTable(){
        System.out.println("abrir tabela de produtos");


        ArrayList<ActionRow> actions=new ArrayList<ActionRow>();
        actions.add(new SaveProductController());
        actions.add(new DeleteProductController());
        actions.add(new FabricanteProdutoController());
        try {
           
            ProdutoAdapter adapter=new ProdutoAdapter();
            SearchTable table=new SearchTable(adapter.getColumns(),adapter.getList(),actions,null);
            table.setHildeColumns(new String[]{"id"});
            table.show();
            setContent("Produtos", table);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void movimentacaoTable(){
        System.out.println("abrir tabela de produtos");
  

        ArrayList<ActionRow> actions=new ArrayList<ActionRow>();
        actions.add(new  SaveMovimentacaoController());

        String[] values = new String[]{Movimentacao.TipoDeTransacao.ENTRADA.toString(),Movimentacao.TipoDeTransacao.SAIDA.toString()} ;
        CustomColumn tipo=new CustomColumn(values,"tipo");
        ArrayList<CustomColumn> columns=new ArrayList<CustomColumn>();
        columns.add(tipo);

// =============================================
        ProdutoDAO  produtoDAO=new ProdutoDAO(SingletonConnectionDB.getConnection());
        ArrayList<String> valuesProdutos=new ArrayList<String>();
        ArrayList<String> nomeProdutos=new ArrayList<String>();
        try {
            produtoDAO.getAll().forEach(value->{
                valuesProdutos.add(""+value.getId());
                nomeProdutos.add(""+value.getNome());
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        
         
      
        CustomColumn produto=new CustomColumn(valuesProdutos.toArray(),"produto",nomeProdutos.toArray());
        columns.add(produto);
// =============================================
        EstoqueDAO  estoqueDAO=new EstoqueDAO(SingletonConnectionDB.getConnection());
        ArrayList<String> valuesEstoque=new ArrayList<String>();
        ArrayList<String> nomeEstoque=new ArrayList<String>();
        try {
            estoqueDAO.getAll().forEach(value->{
                valuesEstoque.add(""+value.getId());
                nomeEstoque.add(""+value.getNome());
                
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        CustomColumn estoque=new CustomColumn(valuesEstoque.toArray(),"estoque",nomeEstoque.toArray());
        columns.add(estoque);
// =============================================
        FuncionarioDAO  funcionarioDAO=new FuncionarioDAO(SingletonConnectionDB.getConnection());
        ArrayList<String> valuesFuncionario=new ArrayList<String>();
        ArrayList<String> nomeFuncionario=new ArrayList<String>();
        try {
            funcionarioDAO.getAll().forEach(value->{
                valuesFuncionario.add(""+value.getCpf());
                nomeFuncionario.add(""+value.getNome());
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        CustomColumn funcionario=new CustomColumn(valuesFuncionario.toArray(),"funcionario",nomeFuncionario.toArray());
        columns.add(funcionario);
// =============================================


        try {
           
            MovimentacaoAdapter adapter=new MovimentacaoAdapter();
            SearchTable table=new SearchTable(adapter.getColumns(),adapter.getList(),actions,columns,new String[]{"id","data"});
            table.show();
            setContent("Produtos", table);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void relatorioProduto(){
        System.out.println("abrir relatorio");
        mainPanel.removeAll();
        try {
           
            ProdutoDAO dao=new ProdutoDAO(SingletonConnectionDB.getConnection());
            ResultSet result=dao.relatorio();
            UtilDAO util=new UtilDAO();
       
            List<Map> data = util.get(result);
            String[] cols = util.getCollumns(result);

            SearchTable table=new SearchTable(cols,data,null,null);
            table.show();
            setContent("Relatório Produto", table);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void relatorioEstoqueProdutos(){
        System.out.println("abrir relatorio");
        mainPanel.removeAll();
        try {
           
            EstoqueDAO dao=new EstoqueDAO(SingletonConnectionDB.getConnection());
            ResultSet result=dao.relatorioEstoqueProdutos();
            UtilDAO util=new UtilDAO();
       
            List<Map> data = util.get(result);
            String[] cols = util.getCollumns(result);

            EditableTable table=new EditableTable(cols,data,null,null);
            table.show();
            setContent("Relatório Estoque de Produtos", table);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void relatorioRespresenantes(){
        System.out.println("abrir relatorio");
        mainPanel.removeAll();
        try {
           
            RepresentanteDAO dao=new RepresentanteDAO(SingletonConnectionDB.getConnection());
            ResultSet result=dao.relatorio();
            UtilDAO util=new UtilDAO();
       
            List<Map> data = util.get(result);
            String[] cols = util.getCollumns(result);

            EditableTable table=new EditableTable(cols,data,null,null);
            table.show();
            setContent("Relatório Representantes", table);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void relatorioMovimentacaoDAO(){
        System.out.println("abrir relatorio");
        mainPanel.removeAll();
        try {
           
            MovimentacaoDAO dao=new MovimentacaoDAO(SingletonConnectionDB.getConnection());
            ResultSet result=dao.relatorio();
            UtilDAO util=new UtilDAO();
       
            List<Map> data = util.get(result);
            String[] cols = util.getCollumns(result);
            EditableTable table=new EditableTable(cols,data,null,null);
            table.show();
           
            setContent("Relatório Movimentacao", table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void relatorioEstoque(){
        System.out.println("abrir relatorio");
        mainPanel.removeAll();
        try {
           
            EstoqueDAO dao=new EstoqueDAO(SingletonConnectionDB.getConnection());
            ResultSet result=dao.relatorio();
            UtilDAO util=new UtilDAO();
       
            List<Map> data = util.get(result);
            String[] cols = util.getCollumns(result);
            EditableTable table=new EditableTable(cols,data,null,null);
            table.show();
           
            setContent("Relatório Qauntidade de itens no Estoque", table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    public void relatorioArmazenamento(){
        System.out.println("abrir relatorio");
        mainPanel.removeAll();
        try {
           
            ArmazenamentoDAO dao=new ArmazenamentoDAO(SingletonConnectionDB.getConnection());
            ResultSet result=dao.relatorio();
            UtilDAO util=new UtilDAO();
       
            List<Map> data = util.get(result);
            String[] cols = util.getCollumns(result);
            EditableTable table=new EditableTable(cols,data,null,null);
            table.show();
           
            setContent("Relatório Armazenamento", table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 
    public void menu(){
        JMenuBar menu=new JMenuBar();
        ACLInterface acl=PapelUsuario.getPapel();
        if(acl==null){
            acl=new ACLGerente();
        }

        JMenu menuitemProduto=new JMenu("Produto");
        JMenu menuitemFuncionario=new JMenu("Funcionario");
        JMenu menuitemEstoque=new JMenu("Estoque");
        JMenu menuitemFabricante=new JMenu("Fabricante");
        JMenu menuitemRepresentante=new JMenu("Representante");

        if(acl.menuProduto())
            menu.add(menuitemProduto);
        if(acl.menuFuncionario())
            menu.add(menuitemFuncionario);
        if(acl.menuEstoque()|| acl.menuMovimentacao())
            menu.add(menuitemEstoque);

        if(acl.menuFabricante())
            menu.add(menuitemFabricante);
        if(acl.menuRepresentante())
            menu.add(menuitemRepresentante);
 


        JMenuItem itemProduto = new JMenuItem("Produto");
        itemProduto.addActionListener(e->produtoTable());
        menuitemProduto.add(itemProduto);
        JMenuItem itemRelatorioProduto = new JMenuItem("Relatorio Produto");
        itemRelatorioProduto.addActionListener(e->relatorioProduto());
        menuitemProduto.add(itemRelatorioProduto);

        JMenuItem itemFuncionario=new JMenuItem("Funcionario");
        itemFuncionario.addActionListener(e->funcionarioTable());
        menuitemFuncionario.add(itemFuncionario);
        
        JMenuItem itemEstoque=new JMenuItem("Estoque");
        JMenuItem itemMovimentacao=new JMenuItem("Movimentacao");
        JMenuItem itemRelatorioMovimentacao=new JMenuItem("Relatorio Movimentacao");
        JMenuItem itemRelatorioArmazenamento=new JMenuItem("Relatorio Quantidade Estoque");
        JMenuItem itemRelatorioQuantidadeProdutosEstoque=new JMenuItem("Relatorio Quantidade Produtos Estoque");

        if (acl.menuEstoque())
            menuitemEstoque.add(itemEstoque);
        

        if(acl.menuMovimentacao()){
            menuitemEstoque.add(itemMovimentacao);
            menuitemEstoque.add(itemRelatorioMovimentacao);
        }


        if (acl.menuEstoque()){
            menuitemEstoque.add(itemRelatorioArmazenamento);
            menuitemEstoque.add(itemRelatorioQuantidadeProdutosEstoque);
        }    
       

        itemMovimentacao.addActionListener(e->movimentacaoTable());
        itemEstoque.addActionListener(e->estoqueTable());
        itemRelatorioMovimentacao.addActionListener(e->relatorioMovimentacaoDAO());
        itemRelatorioArmazenamento.addActionListener(e->relatorioArmazenamento());
        itemRelatorioQuantidadeProdutosEstoque.addActionListener(e->relatorioEstoqueProdutos());


        JMenuItem itemFabricante=new JMenuItem("Fabricante");
        itemFabricante.addActionListener(e->fabricanteTable());
        menuitemFabricante.add(itemFabricante);

        JMenuItem itemRepresentante=new JMenuItem("Representante");
        itemRepresentante.addActionListener(e->representanteTable());
        menuitemRepresentante.add(itemRepresentante);
        JMenuItem itemRelatorioRepresentante=new JMenuItem("Relatorio Representantes");
        itemRelatorioRepresentante.addActionListener(e->relatorioRespresenantes());
        menuitemRepresentante.add(itemRelatorioRepresentante);
        
        

        this.setJMenuBar(menu);


    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
 
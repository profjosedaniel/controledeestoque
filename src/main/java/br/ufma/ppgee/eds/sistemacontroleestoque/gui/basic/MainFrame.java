package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.ProdutoAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.FabricanteProcutoController;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.SaveProductController;

public class MainFrame extends JFrame{

    public void produtoTable(){
        System.out.println("abrir tabela de produtos");
        this.getContentPane().removeAll();

        ArrayList<ActionRow> actions=new ArrayList<ActionRow>();
        actions.add(new SaveProductController());
        actions.add(new FabricanteProcutoController());
        try {
           
            ProdutoAdapter adapter=new ProdutoAdapter();
           
            getContentPane().add(new EditableTable(adapter.getColumns(),adapter.getList(),actions));
            this.getContentPane().setVisible(false);
            this.getContentPane().setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public MainFrame(){
        super();
        menu();
        

        this.setTitle("Editable Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setSize(1000, 800);
        this.setVisible(true);
    }

    public void menu(){
        JMenuBar menu=new JMenuBar();
        JMenuItem itemProduto=new JMenuItem("Produto");
        JMenuItem itemFuncionario=new JMenuItem("Funcionario");
        JMenuItem itemEstoque=new JMenuItem("Estoque");
        JMenuItem itemFabricante=new JMenuItem("Fabricante");
        JMenuItem itemRepresentante=new JMenuItem("Representante");

 

        menu.add(itemProduto);
        menu.add(itemFuncionario);
        menu.add(itemEstoque);
        menu.add(itemFabricante);
        menu.add(itemRepresentante);

        itemProduto.addActionListener(e->produtoTable());



        this.setJMenuBar(menu);


    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
 
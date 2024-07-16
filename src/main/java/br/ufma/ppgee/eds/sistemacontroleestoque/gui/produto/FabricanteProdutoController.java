package br.ufma.ppgee.eds.sistemacontroleestoque.gui.produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.accessibility.AccessibleAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.IconRenderer;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.list.ActionList;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.list.ListEntities;

public class FabricanteProdutoController implements ActionRow, ActionList{
    ProdutoDAO daoProduto;
    FabricanteDAO daoFabricanteDAO;
    Produto produto;

    public static void main(String[] args) {
   
        List all= new ArrayList<>();
        try {
            all = new FabricanteDAO(new SingletonConnectionDB().getConnection()).getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List selected = new ArrayList<>();
        JFrame frame = new JFrame();
        ListEntities<Fabricante> listEntities = new ListEntities<Fabricante>(all,selected,null);
        frame.getContentPane().add(listEntities);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        
        
        frame.setVisible(true);
    }
    public FabricanteProdutoController(){
        daoProduto = new ProdutoDAO(SingletonConnectionDB.getConnection());
        daoFabricanteDAO = new FabricanteDAO(SingletonConnectionDB.getConnection());
    }

    public ArrayList getFabricantes(Map<String, String> row) throws Exception {
        produto=daoProduto.get(Integer.parseInt(row.get("id") ));
        if(produto==null){
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
            return null;
        }
       
        List selected = new ArrayList<>();   
        selected = daoFabricanteDAO.getFabricantes(produto.getId());
        return (ArrayList) selected;
    }
    @Override
    public void action(Map<String, String> row) throws Exception {
        List all= new ArrayList<>();
        all = daoFabricanteDAO.getAll();
        List selected = getFabricantes(row);
        if(selected==null){
            return;
        }
        JFrame frame = new JFrame();
        ListEntities<Fabricante> listEntities = new ListEntities<Fabricante>(all,selected,this);
        frame.setContentPane(listEntities);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        
        
        frame.setVisible(true);

    }

    @Override
    public String column() {
        return "fabricantes";
    }

    @Override
    public TableCellRenderer render() {
        return new IconRenderer();
    }
    @Override
    public Object get(Object value, Map<String,String> row) {
        if(row.get("id")!=null && !row.get("id").isEmpty()){
            try {
                ArrayList fabricantes = getFabricantes(row);
                return fabricantes.size()+" fabricantes ⬆️";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String("0 fabricante ⬆️");
        }
        return "";
        
    }
 
    @Override
    public void process( List list) throws Exception{
        if(produto!=null){
            for (Object item : list) {
                Fabricante fabricante=(Fabricante)item;
                if(!daoFabricanteDAO.checkProdutoFabricante(produto, fabricante)){
                    daoFabricanteDAO.relacionarProdutoFabricante(produto, fabricante);
                    System.out.println("relacionado"+fabricante.getCnpj()+" ao produto "+produto.getId());
                }
            }
            daoFabricanteDAO.getFabricantes(produto.getId()).forEach((fabricante)->{
                if(!list.contains(fabricante)){
                    try {
                        daoFabricanteDAO.removerRelacaoProdutoFabricante(produto, fabricante);
                        System.out.println("removido"+fabricante.getCnpj()+" do produto "+produto.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
    }
     


}

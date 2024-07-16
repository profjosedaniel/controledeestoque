package br.ufma.ppgee.eds.sistemacontroleestoque.gui.fabricante;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.accessibility.AccessibleAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.IconRenderer;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.list.ActionList;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.list.ListEntities;

public class FabricanteRepresentanteController implements ActionRow, ActionList{
    private RepresentanteDAO representanteDAO;
    private FabricanteDAO daoFabricanteDAO;
    private Fabricante fabrincate;
    public FabricanteRepresentanteController(){
        representanteDAO = new RepresentanteDAO(SingletonConnectionDB.getConnection());
        daoFabricanteDAO = new FabricanteDAO(SingletonConnectionDB.getConnection());
    }

    public ArrayList getRepresentantes(Map<String, String> row) throws Exception {
        fabrincate=daoFabricanteDAO.get(row.get("cnpj"));
        if(fabrincate==null){
            JOptionPane.showMessageDialog(null, "fabrincate não encontrado");
            return null;
        }
       
        List selected = new ArrayList<>();   
        selected = representanteDAO.getRepresentantes(fabrincate.getCnpj());
        return (ArrayList) selected;
    }
    @Override
    public void action(Map<String, String> row) throws Exception {
        List<Representante> all= new ArrayList<>();
        all = representanteDAO.getAll();
        List<Representante> selected = getRepresentantes(row);
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
        return "representantes";
    }

    @Override
    public TableCellRenderer render() {
        return new IconRenderer();
    }
    @Override
    public Object get(Object value, Map<String,String> row) {
        if(row.get("cnpj")!=null && !row.get("cnpj").isEmpty()){
            try {
                ArrayList fabricantes = getRepresentantes(row);
                return fabricantes.size()+" representantes ⬆️";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String("0 representante ⬆️");
        }
        return "";
        
    }
 
    @Override
    public void process( List list) throws Exception{
        if(fabrincate!=null){
            for (Object item : list) {
                Representante representante=(Representante)item;
                try{
                    if(!representanteDAO.checkRepresentacao(representante, fabrincate)){
                        representanteDAO.createRepresentacao(representante, fabrincate);
                        System.out.println("relacionado"+fabrincate.getCnpj()+" ao produto "+representante.getCpf());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
               
            }
            representanteDAO.getRepresentantes(fabrincate.getCnpj()).forEach((representante)->{
                if(!list.contains(representante)){
                    try {
                        representanteDAO.removeRepresentacao(representante.getCpf(), fabrincate.getCnpj());
                        System.out.println("removido"+fabrincate.getCnpj()+" do produto "+representante.getCpf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
    }
     


}

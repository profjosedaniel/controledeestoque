package  br.ufma.ppgee.eds.sistemacontroleestoque.gui.funcionario;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.accessibility.AccessibleAction;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.FuncionarioAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.IconRenderer;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.list.ActionList;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.list.ListEntities;

public class FuncionarioSenhaController implements ActionRow{
   
    public  AdapterInterface<Funcionario> getAdapter(){
        return new FuncionarioAdapter();
    }


    public  FuncionarioDAO getDao() {
       return new FuncionarioDAO(SingletonConnectionDB.getConnection());
    }

   
   public FuncionarioSenhaController(){   
}

    @Override
    public void action(Map<String, String> row) throws Exception{
        String value= JOptionPane.showInputDialog("Senha");
        try{
            Funcionario funcionario = getAdapter().load(row);
            getDao().updatePassword(funcionario.getCpf(), value);
            JOptionPane.showMessageDialog(new JFrame(), "Senha alterada com sucesso");
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(new JFrame(), "Erro ao alterar senha");
    }



 
    @Override
    public String column(){
        return "senha";
    }

    @Override
    public TableCellRenderer render() {
        return new IconRenderer();
    }
    @Override
    public Object get(Object value,Map<String,String> row) {
        // imagem https://www.flaticon.com/free-icon/
        String path="C:\\Users\\Daniel\\OneDrive\\doutorado 2024\\disciplinas\\engenharia de software\\projeto\\sistemacontroleestoque\\assert\\img\\passwordg.png";
        File imgURL = new File(path);
        BufferedImage img;
        try {
            img = ImageIO.read(imgURL);
            Image scaledImg = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme necessário
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }   

}

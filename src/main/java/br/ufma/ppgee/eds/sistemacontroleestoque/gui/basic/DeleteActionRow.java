package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.TableCellRenderer;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.IconRenderer;

public abstract  class DeleteActionRow<T> implements ActionRow{
   

    public abstract AdapterInterface<T> getAdapter();
    public abstract DAOInterface getDao() ;

   
   public DeleteActionRow(){   
    }

    @Override
    public void action(Map<String, String> row) throws Exception{
        action(getAdapter().load(row));
    }

    public abstract boolean check(T t) throws Exception ;

    public void action(T t) throws Exception {
        if(check(t)){
            getDao().deleteE(t);
        }
        
    }
    @Override
    public String column(){
        return "Excluir";
    }

    @Override
    public TableCellRenderer render() {
        return new IconRenderer();
    }
    @Override
    public Object get(Object value,Map<String,String> row) {
        String path= "C:\\Users\\Daniel\\OneDrive\\doutorado 2024\\disciplinas\\engenharia de software\\projeto\\sistemacontroleestoque\\assert\\img\\trash.png";
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


package br.ufma.ppgee.eds.sistemacontroleestoque.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.TableCellRenderer;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.ProdutoAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.IconRenderer;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

public class SaveProductController implements ActionRow{
    private  ProdutoDAO dao;
    private ProdutoAdapter adapter;

    public SaveProductController(){
        
        dao=new ProdutoDAO(SingletonConnectionDB.getConnection());
        adapter=new ProdutoAdapter();
    }
    @Override
    public void action(Map<String, String> row) throws Exception{
        save(adapter.load(row));
    }

    public void save(Produto t) throws Exception {
        if(t.getId()==null||t.getId()==0){
            dao.create(t);
        }else{
            dao.update(t);
        }
        
    }
    @Override
    public String column() {
       return "save";
    }
    @Override
    public TableCellRenderer render() {
        return new IconRenderer();
    }
    @Override
    public Object get(Object value) {
        String path="C:\\Users\\Daniel\\OneDrive\\doutorado 2024\\disciplinas\\engenharia de software\\projeto\\sistemacontroleestoque\\assert\\img\\save.png";
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

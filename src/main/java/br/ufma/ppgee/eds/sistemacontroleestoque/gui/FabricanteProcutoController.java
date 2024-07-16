package br.ufma.ppgee.eds.sistemacontroleestoque.gui;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;

import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.IconRenderer;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.ListEntities;

public class FabricanteProcutoController implements ActionRow{

    @Override
    public void action(Map<String, String> row) throws Exception {
        ArrayList all = new ArrayList<>();
        ArrayList selected = new ArrayList<>();
        

        JFrame frame = new JFrame();
        ListEntities listEntities = new ListEntities(all,selected);
        frame.getContentPane().add(listEntities);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

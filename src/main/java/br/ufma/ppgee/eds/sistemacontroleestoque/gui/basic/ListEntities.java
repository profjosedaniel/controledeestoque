package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class ListEntities extends javax.swing.JPanel{
  

    private JList<String> sourceList;
    private JList<String> targetList;
    private DefaultListModel<String> sourceListModel;
    private DefaultListModel<String> targetListModel;
    private JButton addButton;
    private JButton removeButton;

    public ListEntities(ArrayList all,ArrayList selected) {
        
        setLayout(new BorderLayout());

        // Modelos das listas
        sourceListModel = new DefaultListModel<>();
        targetListModel = new DefaultListModel<>();

        all.forEach((item) -> {
            if(!selected.contains(item))
                sourceListModel.addElement(item.toString());
        });
        selected.forEach((item) -> {
            targetListModel.addElement(item.toString());
        });

        // Inicializando as listas
        sourceList = new JList<>(sourceListModel);
        targetList = new JList<>(targetListModel);

        // Inicializando os botões
        addButton = new JButton("Add >>");
        removeButton = new JButton("<< Remove");

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Adicionando ActionListeners aos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = sourceList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedItem = sourceList.getSelectedValue();
                    targetListModel.addElement(selectedItem);
                    sourceListModel.remove(selectedIndex);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = targetList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedItem = targetList.getSelectedValue();
                    sourceListModel.addElement(selectedItem);
                    targetListModel.remove(selectedIndex);
                }
            }
        });

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.add(new JScrollPane(sourceList));
        mainPanel.add(buttonPanel);
        mainPanel.add(new JScrollPane(targetList));

        // Adicionando o painel principal ao frame
        add(mainPanel, BorderLayout.CENTER);
        JButton saveButton = new JButton("Save");
        add(saveButton, BorderLayout.SOUTH);
    }
}

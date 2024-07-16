package br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.list;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
public class ListEntities <T> extends javax.swing.JPanel{ 
    private JList<String> sourceList;
    private JList<String> targetList;
    private DefaultListModel<Object> sourceListModel;
    private DefaultListModel<Object> targetListModel;
    private JButton addButton;
    private JButton removeButton;
    private ActionList actionList;

    @SuppressWarnings("unchecked")
    public ListEntities(List all,List selected, ActionList actionList) {
        this.actionList=actionList;

        
        // Modelos das listas
        sourceListModel = new DefaultListModel<>();
        targetListModel = new DefaultListModel<>();

        all.forEach(item -> {
            if(!selected.contains(item))
                sourceListModel.addElement(item);
        });
        selected.forEach((item) -> {
            targetListModel.addElement(item);
        });

        // Inicializando as listas
        sourceList = new JList(sourceListModel);
        targetList = new JList(targetListModel);

        // Inicializando os botões
    

        addButton = new JButton("Adicionar ➡️");
        removeButton = new JButton("⬅️ Remover");

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
       
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.setSize(new Dimension(50,50));
        addButton.setSize(new Dimension(50,50));
        removeButton.setSize(new Dimension(50,50));

        buttonPanel.add(addButton);
     
        buttonPanel.add(removeButton);
      
        // Adicionando ActionListeners aos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = sourceList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Object selectedItem = sourceList.getSelectedValue();
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
                    Object selectedItem = targetList.getSelectedValue();
                    sourceListModel.addElement(selectedItem);
                    targetListModel.remove(selectedIndex);
                }
            }
        });

        



        // Adicionando o painel principal ao frame
     
        JButton saveButton = new JButton("Salvar 💾");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List selected = new ArrayList<>();
                    for (int i = 0; i < targetListModel.size(); i++) {
                        System.out.println("targetListModel: "+targetListModel.getElementAt(i));
                        selected.add(targetListModel.getElementAt(i));
                    }
           
           
                    actionList.process(selected);
                    JOptionPane.showMessageDialog(new JFrame(), "Salvo com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }

            }
        
        });
        /////////////
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1;
        c.weighty = 1;

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        add(new JScrollPane(sourceList),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        add(buttonPanel,c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        add(new JScrollPane(targetList),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 2;
        c.gridy = 1;
        add(saveButton,c);
          
    }
}

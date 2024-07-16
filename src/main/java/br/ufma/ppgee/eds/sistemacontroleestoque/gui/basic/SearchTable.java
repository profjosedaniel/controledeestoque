package  br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.customColumn.CustomColumn;

public class SearchTable extends EditableTable{
    private JComboBox campos;
    public SearchTable(String[] columns, List<Map> data, List<ActionRow> actions,
            ArrayList<CustomColumn> customColumns) {
        this(columns, data, actions, customColumns,null);
    }
    public SearchTable(String[] columns, List<Map> data, List<ActionRow> actions,
            ArrayList<CustomColumn> customColumns, String[] strings) {
        super(columns, data, actions, customColumns);
    }

    @Override
    public void show() {
        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));

        campos=new JComboBox<>();
        Stream.of(super.columns).forEach(campos::addItem);
        panel.add(campos);
        JLabel label=new JLabel("Pesquisar");
        panel.add(label);
        JTextField textField=new JTextField();
        textField.setColumns(30);
        panel.add(textField);
        JButton button=new JButton("Pesquisar");
        button.addActionListener(e->{
            pesquisar(textField.getText());
        });
        panel.add(button);
        super.show(panel);
    }

    public void pesquisar(String text) {
        int index=campos.getSelectedIndex();
        String value=super.columns[index];

        System.out.println("Pesquisando por "+text);
        String column=value;
        List<Map> data2 = super.data.stream().filter(row->{
            String nome=(String) row.get(column);
            System.out.println(nome);
            return nome.toLowerCase().contains(text.toLowerCase());
        }).toList();
        super.model.setData(data2);
        super.model.fireTableDataChanged();

    }
  

}

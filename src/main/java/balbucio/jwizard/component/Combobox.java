package balbucio.jwizard.component;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class Combobox implements WizardComponent{

    @Getter
    public String id;
    private String title;
    private String selected;
    private List<String> options;
    private boolean required;
    private int width, height;

    public Combobox(String id, String title, List<String> options) {
        this(id, title, null, options, false);
    }

    public Combobox(String id, String title, String selected, List<String> options) {
        this(id, title, selected, options, false);
    }
    public Combobox(String id, String title, String selected, List<String> options, boolean required) {
        this.id = id;
        this.title = title;
        this.selected = selected;
        this.options = options;
        this.required = required;
    }

    private JPanel panel;
    private JLabel text;
    private JComboBox<String> comboBox;


    @Override
    public boolean isRequired() {
        return false;
    }

    @Override
    public boolean isCompleted() {
        return comboBox.getSelectedItem() != null;
    }

    @Override
    public void warn() {
        panel.setBorder(new LineBorder(Color.RED));
        comboBox.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        String finalTitle = isRequired() ? title +"*" : title;
        text = new JLabel(finalTitle);
        comboBox = new JComboBox<String>(new Vector<>(options));
        comboBox.addActionListener(e -> panel.setBorder(new EmptyBorder(0,0,0,0)));
        comboBox.setPreferredSize(new Dimension(240, 24));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(text);
        panel.add(comboBox);
        return panel;
    }

    @Override
    public Object getValue() {
        return comboBox.getSelectedItem();
    }
}

package balbucio.jwizard.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class Combobox implements WizardComponent{

    private String title;
    private String selected;
    private List<String> options;
    private boolean required;
    private int width, height;

    public Combobox(String title, List<String> options) {
        this(title, null, options, false);
    }

    public Combobox(String title, String selected, List<String> options) {
        this(title, selected, options, false);
    }
    public Combobox(String title, String selected, List<String> options, boolean required) {
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
        return false;
    }

    @Override
    public void warn() {

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
        return null;
    }
}

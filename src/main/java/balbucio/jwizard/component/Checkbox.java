package balbucio.jwizard.component;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Checkbox implements WizardComponent{

    @Getter
    private String id;
    private String title;
    private boolean required = false;

    public Checkbox(String id, String title){
        this(id, title, false);
    }

    public Checkbox(String id, String title, boolean required){
        this.id = id;
        this.title = title;
        this.required = required;
    }

    private JPanel panel;
    private JCheckBox checkBox;


    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void warn() {
        panel.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        String finalTitle = isRequired() ? title +"*" : title;
        checkBox = new JCheckBox(finalTitle);
        checkBox.addActionListener(e -> panel.setBorder(new EmptyBorder(0,0,0,0)));
        checkBox.setPreferredSize(new Dimension(240, 24));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(checkBox);
        return panel;
    }

    @Override
    public Object getValue() {
        return checkBox.isSelected();
    }
}

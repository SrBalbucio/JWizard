package balbucio.jwizard.component;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TextField implements WizardComponent {
    @Getter
    private String id;
    private String title;
    private JPanel panel;
    @Getter
    @Setter
    private boolean required = false;
    @Getter
    @Setter
    private int width = 240,  height = 24;

    public TextField(String id, String title) {
        this(id, title, false);
    }

    public TextField(String id, String title, boolean required){
        this.id = id;
        this.title = title;
        this.required = required;
    }

    private JLabel text;
    private JTextField field;

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isCompleted() {
        return !field.getText().isEmpty();
    }

    @Override
    public void warn() {
        panel.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        String finalTitle = isRequired() ? title +"*" : title;
        text = new JLabel(finalTitle);
        field = new JTextField();
        field.addActionListener(e -> panel.setBorder(new EmptyBorder(0,0,0,0)));
        field.setPreferredSize(new Dimension(width, height));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(text);
        panel.add(field);
        return panel;
    }

    public void setValue(String value){
        field.setText(value);
    }

    @Override
    public String getValue() {
        return field.getText();
    }
}

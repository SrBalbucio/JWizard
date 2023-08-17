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

    private String title;
    private JPanel panel;
    private boolean required;

    @Getter
    @Setter
    private int width = 240;
    @Getter
    @Setter
    private int height = 24;

    public TextField(String title) {
        this.title = title;
    }

    private JLabel text;
    private JTextField field;

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
        panel.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        text = new JLabel(title);
        field = new JTextField();
        field.addActionListener(e -> panel.setBorder(new EmptyBorder(0,0,0,0)));
        field.setPreferredSize(new Dimension(width, height));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(text);
        panel.add(field);
        return panel;
    }

    @Override
    public String getValue() {
        return field.getText();
    }
}

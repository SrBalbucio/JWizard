package balbucio.jwizard.component;

import javax.swing.*;
import java.awt.*;

public class TextField implements WizardComponent {

    private String title;

    public TextField(String title) {
        this.title = title;
    }

    private JLabel text;
    private JTextField field;

    @Override
    public JComponent getComponent() {
        text = new JLabel(title);
        field = new JTextField();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(text);
        panel.add(field);
        return panel;
    }

    @Override
    public String getValue() {
        return field.getText();
    }
}

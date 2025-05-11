package balbucio.jwizard.component;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Label implements WizardComponent {

    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private int layout = FlowLayout.CENTER;

    public Label(String text) {
        this.text = text;
    }

    public Label(String text, int layout) {
        this.text = text;
        this.layout = layout;
    }

    @Override
    public String getId() {
        return "label-wizard-component";
    }

    @Override
    public boolean isRequired() {
        return false;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void warn() {

    }

    @Override
    public JComponent getComponent() {
        JPanel panel = new JPanel(new FlowLayout(layout));

        panel.add(new JLabel(text));

        return panel;
    }

    @Override
    public Object getValue() {
        return null;
    }
}

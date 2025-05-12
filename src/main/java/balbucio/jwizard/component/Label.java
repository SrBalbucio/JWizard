package balbucio.jwizard.component;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.plaf.multi.MultiLabelUI;
import java.awt.*;

public class Label implements WizardComponent {

    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private int layout = FlowLayout.CENTER;
    @Getter
    @Setter
    private String maxWidth = "100%";

    public Label(String text) {
        this.text = text;
    }

    public Label(String text, int layout) {
        this.text = text;
        this.layout = layout;
    }

    public Label(String text, int layout, String maxWidth) {
        this.text = text;
        this.layout = layout;
        this.maxWidth = maxWidth;
    }

    public Label(String text, int layout, int maxWidthInPx) {
        this.text = text;
        this.layout = layout;
        this.maxWidth = (maxWidthInPx + "px");
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

        JLabel label = new JLabel("<html><p  style=\"width:" + maxWidth + "\">" + text + "</p></html>");
        panel.add(label);

        return panel;
    }

    @Override
    public Object getValue() {
        return "";
    }
}

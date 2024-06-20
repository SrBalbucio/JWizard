package balbucio.jwizard.component;

import balbucio.jwizard.WizardLang;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

@RequiredArgsConstructor
public class TermsBox implements WizardComponent{

    @NonNull
    private String id;
    @NonNull
    private String text;
    @NonNull
    private boolean required;

    private JPanel panel;
    private JScrollPane scroll;
    private JLabel obgText;
    private JTextArea termsText;
    private JCheckBox checkbox;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isCompleted() {
        return checkbox.isSelected();
    }

    @Override
    public void warn() {
        panel.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        obgText = new JLabel(WizardLang.TEXT_TERMS);
        panel.add(obgText);
        termsText = new JTextArea(text);
        termsText.setEditable(false);
        termsText.setBorder(new LineBorder(Color.BLACK));
        termsText.setMargin(new Insets(20, 20, 20, 20));
        scroll = new JScrollPane(termsText);
        scroll.setMaximumSize(new Dimension(1280, 200));
        panel.add(scroll);
        checkbox = new JCheckBox(WizardLang.TEXT_TERMS_CHECK);
        panel.add(checkbox);
        return panel;
    }

    @Override
    public Object getValue() {
        return checkbox.isSelected();
    }
}

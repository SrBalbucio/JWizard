package balbucio.jwizard.component;

import balbucio.jwizard.WizardLang;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        obgText = new JLabel(WizardLang.TEXT_TERMS);
        titlePanel.add(obgText);
        titlePanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        panel.add(titlePanel);

        termsText = new JTextArea(text);
        termsText.setEditable(false);
        termsText.setBorder(new LineBorder(Color.BLACK));
        scroll = new JScrollPane(termsText);
        scroll.setBorder(new EmptyBorder(20, 20, 20, 20));
//        scroll.setMaximumSize(new Dimension(1280, 200));
        panel.add(scroll);

        JPanel options = new JPanel();
        options.setLayout(new FlowLayout(FlowLayout.LEFT));
        checkbox = new JCheckBox(WizardLang.TEXT_TERMS_CHECK);
        options.add(checkbox);
        options.setBorder(new EmptyBorder(0, 20, 0, 20));
        panel.add(options);
        return panel;
    }

    @Override
    public Object getValue() {
        return checkbox.isSelected();
    }
}

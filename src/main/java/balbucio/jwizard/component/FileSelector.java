package balbucio.jwizard.component;

import balbucio.jwizard.WizardLang;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class FileSelector implements WizardComponent{

    @Getter
    private String id;
    private String title;
    private boolean required;
    private File selectedFiles = null;

    public FileSelector(String id, String title){
        this(id, title, false);
    }

    public FileSelector(String id, String title, boolean required){
        this.id = id;
        this.title = title;
        this.required = required;
    }

    private JPanel panel;
    private JLabel label;
    private JButton fileChooser;


    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isCompleted() {
        return selectedFiles != null;
    }

    @Override
    public void warn() {
        panel.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        String finalTitle = isRequired() ? title +"*" : title;
        label = new JLabel(finalTitle);
        fileChooser = new JButton(WizardLang.SELECT_FILE_BUTTON);
        fileChooser.addActionListener(e -> search());
        fileChooser.setPreferredSize(new Dimension(240, 24));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);
        panel.add(fileChooser);
        return panel;
    }

    public void search(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.showOpenDialog(null);
        this.selectedFiles = chooser.getSelectedFile();
    }

    @Override
    public Object getValue() {
        return selectedFiles;
    }
}

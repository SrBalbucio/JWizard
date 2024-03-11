package balbucio.jwizard.component;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class PathSelector implements WizardComponent{
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

    public PathSelector(String id, String title) {
        this(id, title, false);
    }

    public PathSelector(String id, String title, boolean required){
        this.id = id;
        this.title = title;
        this.required = required;
    }

    private File selectedFile;
    private JLabel text;
    private JTextField field;
    private JButton explore;
    private JFileChooser chooser = new JFileChooser();

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isCompleted() {
        if(!field.getText().isEmpty()) {
            File file = new File(field.getText());
            if (!file.exists() || !file.isDirectory()) {
                warn();
                JOptionPane.showMessageDialog(null, "The folder you wrote does not exist, is not a folder or is completely incorrect.");
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void warn() {
        panel.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        String envPath = System.getenv("ProgramFiles");
        File systemFiles = new File(envPath.isEmpty() ? "/ProgramFiles" : envPath);
        String finalTitle = isRequired() ? title +"*" : title;
        text = new JLabel(finalTitle);
        field = new JTextField(systemFiles.getAbsolutePath());
        field.addActionListener(e -> panel.setBorder(new EmptyBorder(0,0,0,0)));
        field.setPreferredSize(new Dimension(width, height));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        explore = new JButton("Explore...");
        explore.addActionListener((e) -> {
            chooser.setCurrentDirectory(systemFiles);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showDialog(null, "Selected...");
            File newDir = chooser.getSelectedFile();
            if(newDir.exists() && newDir.isDirectory()){
                field.setText(newDir.getAbsolutePath());
            }
        });
        panel.add(text);
        panel.add(field);
        panel.add(explore);
        return panel;
    }

    @Override
    public File getValue() {
        return new File(field.getText());
    }

}

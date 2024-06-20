package balbucio.jwizard.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProgressPage extends WizardPage {

    public ProgressPage(String title, String description, Image icon) {
        super(title, description, icon);
    }

    @Getter
    private JProgressBar progressBar = new JProgressBar(0, 100);
    @Getter
    private JTextArea console;;

    public void addToProgress(int size){
        progressBar.setValue(progressBar.getValue() + size);
    }

    public void print(String msg){
        console.append(msg);
    }

    public void println(String msg){
        console.append(msg+"\n");
    }

    @Override
    public boolean isCompleted() {
        return progressBar.getValue() >= 100;
    }

    public JPanel getPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(30,30,30,30));
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        panel.add(progressBar);
        panel.add((console = new JTextArea()));
        console.setEditable(false);
        return panel;
    }
}

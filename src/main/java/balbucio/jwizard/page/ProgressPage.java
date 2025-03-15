package balbucio.jwizard.page;

import balbucio.jwizard.WizardLang;
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
        console = new JTextArea();
    }

    @Getter
    private JProgressBar progressBar = new JProgressBar(0, 100);
    @Getter
    private JTextArea console;

    public void addToProgress(int size) {
        progressBar.setValue(progressBar.getValue() + size);
    }

    public void print(String msg) {
        if (console == null) return;
        console.append(msg);
    }

    public void println(String msg) {
        print(msg + "\n");
    }

    @Override
    public boolean isCompleted() {
        return progressBar.getValue() >= 100;
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);

//        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        titlePanel.add(new JLabel(WizardLang.PROGRESS_TITLE));
//        panel.add(titlePanel);

        panel.add(progressBar);

        console.setEditable(false);
        console.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(console);
        panel.add(scroll);
        return panel;
    }
}

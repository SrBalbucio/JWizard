package balbucio.jwizard;

import balbucio.jwizard.page.WizardPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JWizard {

    private String title;
    private Window dialog;
    private List<WizardPage> pages;
    private int page = 0;

    public JWizard(String title, JFrame owner){
        this.title = title;
        this.dialog = new JDialog(owner, title);
        dialog.setSize(680, 420);
        dialog.setLayout(new BorderLayout());
        this.pages = new ArrayList<>();
        dialog.add(getNorthPanel(), BorderLayout.NORTH);
        dialog.add(getCenterPanel(), BorderLayout.CENTER);
        dialog.add(getSouthPanel(), BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public JWizard(String title){
        this.title = title;
        this.dialog = new JFrame(title);
        dialog.setSize(680, 420);
        dialog.setLayout(new BorderLayout());
        this.pages = new ArrayList<>();
        dialog.add(getNorthPanel(), BorderLayout.NORTH);
        dialog.add(getCenterPanel(), BorderLayout.CENTER);
        dialog.add(getSouthPanel(), BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public WizardPage createPage(String title, String description, Image icon){
        WizardPage page = new WizardPage(title, description, icon);
        addPage(page);
        return page;
    }

    public void previous(){
        if((page - 1) >= 0){
            page--;
            setPage(page);
        }
        buttonReload();
    }

    public void next(){
        WizardPage p = pages.get(page);
        if(p.isCompleted()) {
            if ((page + 1) < pages.size()) {
                page++;
                setPage(page);
            } else{
                dialog.setVisible(false);
                dialog.dispose();
            }
            buttonReload();
        } else{
            p.warn();
        }
    }

    public void cancel(){
        dialog.setVisible(false);
        dialog.dispose();
    }

    public void addPage(WizardPage page){
        pages.add(page);
        if(pages.size() == 1){
            setPage(0);
        }
        buttonReload();
    }

    public void setPage(int i){
        page = i;
        WizardPage p = pages.get(i);
        stepTitle.setText(p.getTitle());
        stepDesc.setText(p.getDescription());
        centerPanel.removeAll();
        centerPanel.add(p.getPanel());
        dialog.revalidate();
        dialog.repaint();
    }

    private void buttonReload(){
        if(page > 0){
            previous.setEnabled(true);
        } else{
            previous.setEnabled(false);
        }

        if((page+1) >= pages.size()){
            next.setEnabled(true);
            next.setText(WizardLang.FINISH_BUTTON);
        } else{
            next.setEnabled(true);
            next.setText(WizardLang.NEXT_BUTTON);
        }
    }

    private JLabel stepTitle;
    private JLabel stepDesc;
    private JLabel stepIcon;

    private JPanel getNorthPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.setBackground(Color.lightGray);
        JPanel info = new JPanel();
        info.setBackground(Color.LIGHT_GRAY);
        BoxLayout layout = new BoxLayout(info, BoxLayout.Y_AXIS);
        info.setLayout(layout);
        stepTitle = new JLabel("");
        stepTitle.setBackground(Color.lightGray);
        stepTitle.setFont(stepTitle.getFont().deriveFont(Font.BOLD, 14f));
        stepDesc = new JLabel("");
        stepDesc.setBackground(Color.lightGray);
        info.add(stepTitle);
        info.add(stepDesc);
        panel.add(info);
        stepIcon = new JLabel("");
        stepDesc.setBackground(Color.lightGray);
        panel.add(stepIcon);
        return panel;
    }

    private JPanel centerPanel;
    private JPanel getCenterPanel(){
        centerPanel = new JPanel();
        return centerPanel;
    }

    private JButton previous;
    private JButton next;
    private JButton cancel;

    private JPanel getSouthPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(Color.lightGray);
        previous = new JButton(WizardLang.PREVIOUS_BUTTON);
        previous.addActionListener(e -> previous());
        previous.setEnabled(false);
        next = new JButton(WizardLang.NEXT_BUTTON);
        next.addActionListener(e -> next());
        next.setEnabled(false);
        cancel = new JButton(WizardLang.CANCEL_BUTTON);
        cancel.addActionListener(e -> cancel());
        panel.add(previous);
        panel.add(next);
        panel.add(cancel);
        return panel;
    }
}

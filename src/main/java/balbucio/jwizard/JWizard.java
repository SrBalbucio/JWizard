package balbucio.jwizard;

import balbucio.jwizard.listener.WizardListener;
import balbucio.jwizard.page.ProgressPage;
import balbucio.jwizard.page.TermsPage;
import balbucio.jwizard.page.WizardPage;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class JWizard {

    private String title;
    @Getter
    private Window dialog;
    @Getter
    private List<WizardPage> pages;
    private int page = 0;
    private List<WizardListener> listeners = new ArrayList<>();

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

    public void addListener(WizardListener listener){
        listeners.add(listener);
    }

    public void setSize(int w, int h){
        dialog.setSize(w, h);
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
            listeners.forEach(l -> l.completedPage(p, page));
            if ((page + 1) < pages.size()) {
                page++;
                setPage(page);
            } else{
                ConcurrentHashMap<String, Object> results = new ConcurrentHashMap<>();
                pages.forEach(resultPage -> results.putAll(resultPage.getResults()));
                listeners.forEach(l -> l.finished(this, results));
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
        listeners.forEach(l -> l.changePage(p, i));
        stepTitle.setText(p.getTitle());
        stepDesc.setText(p.getDescription());
        centerPanel.removeAll();
        JPanel cp = p.getPanel();
        dialog.remove(centerPanel);
        if(p instanceof TermsPage){
            centerPanel = cp;
        } else if(p instanceof ProgressPage){
            centerPanel = ((ProgressPage) p).getPanel();
            ((ProgressPage) p).getProgressBar().addChangeListener((e) -> {
                buttonReload();
            });
        } else {
            centerPanel = new JScrollPane(cp);
        }
        dialog.add(centerPanel, BorderLayout.CENTER);
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
            next.setEnabled(getPages().get(page).isCompleted());
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
        panel.setBackground(WizardColors.BACKGROUND_COLOR);
        JPanel info = new JPanel();
        info.setBackground(WizardColors.BACKGROUND_COLOR);
        BoxLayout layout = new BoxLayout(info, BoxLayout.Y_AXIS);
        info.setLayout(layout);
        stepTitle = new JLabel("");
        stepTitle.setBackground(WizardColors.BACKGROUND_COLOR);
        stepTitle.setFont(stepTitle.getFont().deriveFont(Font.BOLD, 14f));
        stepDesc = new JLabel("");
        stepDesc.setBackground(WizardColors.BACKGROUND_COLOR);
        info.add(stepTitle);
        info.add(stepDesc);
        panel.add(info);
        stepIcon = new JLabel("");
        stepDesc.setBackground(WizardColors.BACKGROUND_COLOR);
        panel.add(stepIcon);
        return panel;
    }

    private JComponent centerPanel;
    private JScrollPane getCenterPanel(){
        centerPanel = new JScrollPane();
        return (JScrollPane) centerPanel;
    }

    private JButton previous;
    private JButton next;
    private JButton cancel;

    private JPanel getSouthPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(WizardColors.BACKGROUND_COLOR);
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

package balbucio.jwizard;

import balbucio.jwizard.component.WizardComponent;
import balbucio.jwizard.page.WizardPage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JWizard {

    private String title;
    private JDialog dialog;
    private CardLayout card;
    private List<WizardPage> pages;
    private int page = 0;

    public JWizard(String title, JFrame owner){
        this.title = title;
        this.dialog = new JDialog(owner, title);
        dialog.setSize(480, 320);
        dialog.setLayout(new BorderLayout());
        this.card = new CardLayout(5, 5);
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

    public void next(){
        card.next(centerPanel);
    }

    public void addPage(WizardPage page){
        pages.add(page);
        card.addLayoutComponent(page.getPanel(), String.valueOf(pages.size()));
    }

    public void setPage(int i){
        card.show(centerPanel, String.valueOf(i));
        WizardPage p = pages.get(i-1);
        stepTitle.setText(p.getTitle());
        stepDesc.setText(p.getDescription());
    }

    private JLabel stepTitle;
    private JLabel stepDesc;
    private JLabel stepIcon;

    private JPanel getNorthPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel info = new JPanel();
        BoxLayout layout = new BoxLayout(info, BoxLayout.Y_AXIS);
        info.setLayout(layout);
        stepTitle = new JLabel("");
        stepDesc = new JLabel("");
        info.add(stepTitle);
        info.add(stepDesc);
        panel.add(info);
        stepIcon = new JLabel("");
        panel.add(stepIcon);
        return panel;
    }

    private JPanel centerPanel;
    private JPanel getCenterPanel(){
        centerPanel = new JPanel(card);
        return centerPanel;
    }

    private JButton previous;
    private JButton next;
    private JButton cancel;

    private JPanel getSouthPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        previous = new JButton("Voltar");
        previous.setEnabled(false);
        next = new JButton("Continuar");
        next.setEnabled(false);
        cancel = new JButton("Cancelar");
        panel.add(previous);
        panel.add(next);
        panel.add(cancel);
        return panel;
    }
}

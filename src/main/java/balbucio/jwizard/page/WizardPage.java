package balbucio.jwizard.page;

import balbucio.jwizard.component.WizardComponent;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WizardPage{

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Image icon;
    @Getter
    private List<WizardComponent> componentList;
    @Getter
    private JPanel panel;
    private JPanel componentPanel;
    private GridBagConstraints gbc = new GridBagConstraints();

    public WizardPage(String title, String description, Image icon) {
        this(title, description, icon, new ArrayList<>());
    }

    public WizardPage(String title, String description, Image icon, List<WizardComponent> componentList) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.componentList = componentList;
        this.panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        this.componentPanel = new JPanel();
        BoxLayout layout = new BoxLayout(componentPanel, BoxLayout.Y_AXIS);
        componentPanel.setLayout(layout);
        panel.add(componentPanel, gbc);
        reload();
    }

    public void addComponent(WizardComponent... component){
        componentList.addAll(List.of(component));
        reload();
    }

    public void reload(){
        componentPanel.removeAll();
        componentList.forEach(w -> componentPanel.add(w.getComponent()));
    }
}

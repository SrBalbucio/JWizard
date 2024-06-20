package balbucio.jwizard.page;

import balbucio.jwizard.component.WizardComponent;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.*;
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

    public WizardPage(String title, String description, Image icon) {
        this(title, description, icon, new ArrayList<>());
    }

    public WizardPage(String title, String description, Image icon, List<WizardComponent> componentList) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.componentList = componentList;
        this.panel = new JPanel();
        this.componentPanel = new JPanel();

        BoxLayout layout = new BoxLayout(componentPanel, BoxLayout.Y_AXIS);
        componentPanel.setLayout(layout);
        panel.add(componentPanel);

        reload();
    }

    public Map<String, Object> getResults(){
        Map<String, Object> results = new HashMap<>();
        componentList.forEach((w) -> results.put(w.getId(), w.getValue()));
        return results;
    }

    public boolean isCompleted(){
        return componentList.stream().allMatch(c -> {
            if(c.isRequired() && c.isCompleted()){
                return true;
            } else return !c.isRequired() || c.isCompleted();
        });
    }

    public void warn(){
        componentList.forEach(c -> {
            if(!c.isCompleted() && c.isRequired()){
                c.warn();
            }
        });
    }

    public void addComponent(WizardComponent... component){
        componentList.addAll(Arrays.asList(component));
        reload();
    }

    public void reload(){
        componentPanel.removeAll();
        componentList.forEach(w -> componentPanel.add(w.getComponent()));
        componentPanel.revalidate();
        componentPanel.repaint();
    }
}

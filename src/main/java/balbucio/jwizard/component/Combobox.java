package balbucio.jwizard.component;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Combobox implements WizardComponent {

    @Getter
    public String id;
    private String title;
    private String selected;
    private Map<String, Object> options;
    private boolean required = false;
    private int width, height;

    public Combobox(String id, String title, List<String> options) {
        this(id, title, null, options, false);
    }

    public Combobox(String id, String title, String selected, List<String> options) {
        this(id, title, selected, options, false);
    }

    public Combobox(String id, String title, String selected, List<String> options, boolean required) {
        this.id = id;
        this.title = title;
        this.selected = selected;
        this.options = new HashMap<>();
        options.forEach((option) -> this.options.put(option, option));
        this.required = required;
    }

    public Combobox(String id, String title, Map<String, Object> options) {
        this.id = id;
        this.title = title;
        this.options = options;
    }

    public Combobox(String id, String title, String selected, Map<String, Object> options) {
        this.id = id;
        this.title = title;
        this.options = options;
        this.selected = selected;
    }

    private JPanel panel;
    private JLabel text;
    private JComboBox<String> comboBox;
    @Getter
    @Setter
    private ComboxUpdateListener updateListener;

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isCompleted() {
        return comboBox.getSelectedItem() != null;
    }

    @Override
    public void warn() {
        panel.setBorder(new LineBorder(Color.RED));
        comboBox.setBorder(new LineBorder(Color.RED));
    }

    @Override
    public JComponent getComponent() {
        String finalTitle = isRequired() ? title + "*" : title;
        text = new JLabel(finalTitle);
        comboBox = new JComboBox<String>(new Vector<>(options.keySet()));
        comboBox.addItemListener((e) -> {
            if (updateListener != null) updateListener.onUpdate(this, e.getItem());
        });
        comboBox.addActionListener(e -> panel.setBorder(new EmptyBorder(0, 0, 0, 0)));
        comboBox.setPreferredSize(new Dimension(240, 24));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(text);
        panel.add(comboBox);
        return panel;
    }

    @Override
    public Object getValue() {
        return options.get(comboBox.getSelectedItem());
    }

    public static interface ComboxUpdateListener {
        public void onUpdate(Combobox component, Object value);
    }
}

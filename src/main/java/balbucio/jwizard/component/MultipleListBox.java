package balbucio.jwizard.component;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MultipleListBox implements WizardComponent{

    private String id;
    private String title;
    private boolean required;
    private Map<String, Object> selected = new HashMap<>();
    private Map<String, Object> options;

    public MultipleListBox(String id, String title, Map<String, Object> options) {
        this.id = id;
        this.title = title;
        this.options = options;
    }

    public MultipleListBox(String id, String title, List<String> options) {
        this(id, title, false, options);
    }

    public MultipleListBox(String id, String title, boolean required, Map<String, Object> options) {
        this.id = id;
        this.title = title;
        this.required = required;
        this.options = options;
    }

    public MultipleListBox(String id, String title, boolean required, List<String> options) {
        this.id = id;
        this.title = title;
        this.required = required;
        this.options = new HashMap<>();
        options.forEach((option) -> this.options.put(option, option));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void warn() {

    }

    private DefaultListModel<String> listModel;
    private JList<String> list;

    @Override
    public JComponent getComponent() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(new JLabel(title));
        root.add(titlePanel);

        this.list = new JList(new Vector<>(options.keySet()));
        list.setMinimumSize(new Dimension(100, 40));
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setDragEnabled(false);
        root.add(new JScrollPane(list));

        return root;
    }

    @Override
    public Object getValue() {
        return selected.values();
    }
}

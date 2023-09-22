package balbucio.jwizard.component;

import javax.swing.*;

public interface WizardComponent {
    public String getId();
    public boolean isRequired();
    public boolean isCompleted();
    public void warn();

    public JComponent getComponent();

    public Object getValue();
}

package balbucio.jwizard.page;

import balbucio.jwizard.component.TermsBox;

import javax.swing.*;
import java.awt.*;

public class TermsPage extends WizardPage{

    private TermsBox box;

    public TermsPage(String title, String description, Image icon, TermsBox box) {
        super(title, description, icon);
        this.box = box;
    }


    @Override
    public JPanel getPanel() {
        return (JPanel) box.getComponent();
    }
}

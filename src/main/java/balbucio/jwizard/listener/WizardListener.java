package balbucio.jwizard.listener;

import balbucio.jwizard.JWizard;
import balbucio.jwizard.page.WizardPage;

public interface WizardListener {

    void changePage(WizardPage page, int index);
    void completedPage(WizardPage page, int index);
    void finished(JWizard wizard);
}

import balbucio.jwizard.JWizard;
import balbucio.jwizard.component.TextField;
import balbucio.jwizard.page.WizardPage;

import java.awt.*;

public class WizardTest {

    public static void main(String[] args) {
        JWizard wizard = new JWizard("Teste de Wizard", null);
        WizardPage page = wizard.createPage("Suas informações", "Complete todos os campos", null);
        page.addComponent(new TextField("Qual seu nome?"), new TextField("Qual seu vulgo?"), new TextField("Qual seu email?"));
        wizard.next();
    }
}

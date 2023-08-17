import balbucio.jwizard.JWizard;
import balbucio.jwizard.component.TextField;
import balbucio.jwizard.page.WizardPage;

import java.awt.*;

public class WizardTest {

    public static void main(String[] args) {
        JWizard wizard = new JWizard("Teste de Wizard", null);
        WizardPage page = wizard.createPage("Informações Pessoais", "Complete todos os campos", null);
        page.addComponent(new TextField("Qual seu nome?"),
                new TextField("Qual seu vulgo?"),
                new TextField("Qual seu email?"));
        WizardPage page2 = wizard.createPage("Informações bancárias", "Complete todos os campos", null);
        page2.addComponent(new TextField("Quanto dinheiro você tem no banco?"),
                new TextField("Quanto você pretende investir no futuro?"),
                new TextField("Quanto você recebe mensalmente?"));
        WizardPage page3 = wizard.createPage("Informações relacionais", "Complete todos os campos", null);
        page3.addComponent(new TextField("Qual nome da sua mae?"),
                new TextField("Qual nome do seu pai?"),
                new TextField("Qual nome do seu melhor amigo?"),
                new TextField("Qual nome da sua melhor amiga?"));
    }
}

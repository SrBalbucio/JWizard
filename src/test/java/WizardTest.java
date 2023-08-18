import balbucio.jwizard.JWizard;
import balbucio.jwizard.component.Combobox;
import balbucio.jwizard.component.TextField;
import balbucio.jwizard.page.WizardPage;

import java.util.Arrays;

public class WizardTest {

    public static void main(String[] args) {
        JWizard wizard = new JWizard("Teste de Wizard", null);
        WizardPage page = wizard.createPage("Informações Pessoais", "Complete todos os campos", null);
        page.addComponent(new TextField("Qual seu nome?", true),
                new TextField("Qual seu vulgo?", true),
                new TextField("Qual seu email?"),
                new Combobox("Qual seu trabalho?", "Padeiro", Arrays.asList("Padeiro", "Desenvolvedor", "TI")));
        WizardPage page2 = wizard.createPage("Informações bancárias", "Complete todos os campos", null);
        page2.addComponent(new TextField("Quanto dinheiro você tem no banco?", true),
                new TextField("Quanto você pretende investir no futuro?"),
                new TextField("Quanto você recebe mensalmente?"));
        WizardPage page3 = wizard.createPage("Informações relacionais", "Complete todos os campos", null);
        page3.addComponent(new TextField("Qual nome da sua mae?", true),
                new TextField("Qual nome do seu pai?"),
                new TextField("Qual nome do seu melhor amigo?"),
                new TextField("Qual nome da sua melhor amiga?"));
    }
}

import balbucio.jwizard.JWizard;
import balbucio.jwizard.component.Checkbox;
import balbucio.jwizard.component.Combobox;
import balbucio.jwizard.component.FileSelector;
import balbucio.jwizard.component.TextField;
import balbucio.jwizard.page.WizardPage;

import java.util.Arrays;

public class WizardTest {

    public static void main(String[] args) {
        JWizard wizard = new JWizard("Teste de Wizard");
        WizardPage page = wizard.createPage("Informações Pessoais", "Complete todos os campos", null);
        page.addComponent(new TextField("nome","Qual seu nome?", true),
                new TextField("vulgo", "Qual seu vulgo?", true),
                new TextField("email", "Qual seu email?"),
                new Combobox("work", "Qual seu trabalho?", "Padeiro", Arrays.asList("Padeiro", "Desenvolvedor", "TI")));
        WizardPage page2 = wizard.createPage("Informações bancárias", "Complete todos os campos", null);
        page2.addComponent(new TextField("money", "Quanto dinheiro você tem no banco?", true),
                new TextField("monthinvestiment","Quanto você pretende investir no futuro?"),
                new TextField("montlymoney","Quanto você recebe mensalmente?"));
        WizardPage page3 = wizard.createPage("Informações relacionais", "Complete todos os campos", null);
        page3.addComponent(new TextField("mae", "Qual nome da sua mae?", true),
                new TextField("pai", "Qual nome do seu pai?"),
                new TextField("amigo", "Qual nome do seu melhor amigo?"),
                new TextField("amiga", "Qual nome da sua melhor amiga?"));
        WizardPage page4 = wizard.createPage("Informações extras", "Complete todos os campos", null);
        page4.addComponent(new Checkbox("java", "Você que isto é feito em java?"),
                new FileSelector("files", "Selecione a foto dos documento: "));
    }
}

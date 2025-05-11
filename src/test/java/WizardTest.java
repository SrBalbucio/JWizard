import balbucio.jwizard.JWizard;
import balbucio.jwizard.component.*;
import balbucio.jwizard.listener.WizardListener;
import balbucio.jwizard.page.ProgressPage;
import balbucio.jwizard.page.TermsPage;
import balbucio.jwizard.page.WizardPage;

import java.util.Arrays;
import java.util.Map;

public class WizardTest {

    public static void main(String[] args) {
        JWizard wizard = new JWizard("Teste de Wizard");
        wizard.addPage(new TermsPage("tERMOS", "Aceita que doi menos", null, new TermsBox("terms", "**HYPERPOWERED PUBLIC GENERAL LICENSE (HPPGL) v1.0**\n" +
                "\n" +
                "Este Contrato de Licença Pública Geral (doravante \"Licença\") é estabelecido entre a HyperPowered (doravante \"Licenciante\") e qualquer pessoa ou entidade que adquira os direitos de utilizar o código fornecido sob esta Licença (doravante \"Licenciado\").\n" +
                "\n" +
                "**TERMOS E CONDIÇÕES**\n" +
                "\n" +
                "1. **Concessão de Licença:**\n" +
                "   A Licenciante concede ao Licenciado uma licença mundial, não exclusiva, gratuita, revogável e intransferível para usar, modificar, integrar, recriar, estudar e vender o código-fonte sob a HPPGL. A Licenciante reserva todos os direitos não expressamente concedidos ao Licenciado.\n" +
                "\n" +
                "2. **Colaboradores:**\n" +
                "   Os colaboradores têm direitos sobre a porção do código que contribuíram. Todos os colaboradores têm acesso permanente ao projeto e podem exercer os direitos conforme descrito nesta Licença.\n" +
                "\n" +
                "3. **Restrições:**\n" +
                "   O código sob a HPPGL não deve ser utilizado para prejudicar indivíduos ou instituições, incluindo a HyperPowered e seus desenvolvedores. Projetos derivados devem manter os créditos à HyperPowered e seus desenvolvedores.\n" +
                "\n" +
                "4. **Integração com Outras Licenças:**\n" +
                "   Projetos derivados podem integrar o código HPPGL sem a necessidade de alterar a licença primária. A HPPGL pode coexistir com outras licenças de código aberto ou com a HPPL (HyperPowered Private License).\n" +
                "\n" +
                "5. **Privacidade e Modificação do Status do Código:**\n" +
                "   A Licenciante tem o direito de decidir, a qualquer momento, tornar o código privado. Nesse caso, apenas os desenvolvedores e colaboradores têm acesso ao código.\n" +
                "\n" +
                "6. **UNLICENSE:**\n" +
                "   Todo código, artefato ou arquivo gerado por um projeto HPPGL é considerado UNLICENSE, o que significa que não possui licença e pode ser utilizado livremente a critério do proprietário.\n" +
                "\n" +
                "7. **Restrição à Mudança para GPL:**\n" +
                "   A HPPGL não permite a mudança para uma licença ao estilo GPL (GNU General Public License).\n" +
                "\n" +
                "8. **Uso Obrigatório da HPPGL:**\n" +
                "   A HyperPowered reserva o direito de, em alguns casos, exigir o uso da HPPGL em projetos relacionados à HyperPowered.\n" +
                "\n" +
                "9. **Direitos Especiais para Desenvolvedores Empregados na HyperPowered:**\n" +
                "   Os desenvolvedores empregados na HyperPowered têm direitos especiais sobre o código que desenvolvem, conforme definido internamente pela HyperPowered.\n" +
                "\n" +
                "10. **Aplicabilidade:**\n" +
                "   A HPPGL pode ser utilizada por qualquer empresa ou desenvolvedor, aplicando-se os direitos que seriam da HyperPowered para a entidade em questão, preferencialmente em projetos relacionados à HyperPowered.\n" +
                "\n" +
                "**DISPOSIÇÕES FINAIS:**\n" +
                "Este Contrato constitui a totalidade do entendimento entre as partes e prevalece sobre quaisquer acordos ou entendimentos anteriores. A invalidade de qualquer disposição deste Contrato não afetará a validade das demais. A Licença pode ser atualizada pela Licenciante a seu critério.\n" +
                "\n" +
                "**CONCORDÂNCIA:**\n" +
                "Ao utilizar, modificar ou redistribuir o código sob a HPPGL, o Licenciado concorda integralmente com os termos e condições desta Licença.\n" +
                "\n" +
                "*© 2024 HyperPowered. Todos os direitos reservados.*", true)));
        WizardPage page = wizard.createPage("Informações Pessoais", "Complete todos os campos", null);
        page.addComponent(
                new Label("Teste dos mais fortes já vistos no mundo, spnv"),
                new TextField("nome", "Qual seu nome?", true),
                new TextField("vulgo", "Qual seu vulgo?", true),
                new TextField("email", "Qual seu email?"),
                new Combobox("work", "Qual seu trabalho?", "Padeiro", Arrays.asList("Padeiro", "Desenvolvedor", "TI")),
                new MultipleListBox("multiplelist", "Selecione as opções bb:", Arrays.asList("Novo Balanço", "Clickbait", "Devolve as Correntes", "Invisível")));
        WizardPage page2 = wizard.createPage("Informações bancárias", "Complete todos os campos", null);
        page2.addComponent(new TextField("money", "Quanto dinheiro você tem no banco?", true),
                new TextField("monthinvestiment", "Quanto você pretende investir no futuro?"),
                new TextField("montlymoney", "Quanto você recebe mensalmente?"));
        WizardPage page3 = wizard.createPage("Informações relacionais", "Complete todos os campos", null);
        page3.addComponent(new TextField("mae", "Qual nome da sua mae?", true),
                new TextField("pai", "Qual nome do seu pai?"),
                new TextField("amigo", "Qual nome do seu melhor amigo?"),
                new TextField("amiga", "Qual nome da sua melhor amiga?"));
        WizardPage page4 = wizard.createPage("Informações extras", "Complete todos os campos", null);
        page4.addComponent(new Checkbox("java", "Você sabia que isto é feito em java?"),
                new FileSelector("files", "Selecione a foto dos documento: "), new PathSelector("path", "Selecione um path: ", true));
        ProgressPage progress = new ProgressPage("Instalando...", "Copiando o virus :D", null);
        wizard.addPage(progress);
        wizard.addListener(new WizardListener() {
            @Override
            public void changePage(WizardPage page, int index) {
                if (index == 5) {
                    Thread th = new Thread(() -> {
                        int i = 0;
                        while (i < 100) {
                            try {
                                i++;
                                progress.addToProgress(i);
                                progress.println("Posição: " + i);
                                Thread.sleep(1500);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    th.start();
                }
            }

            @Override
            public void completedPage(WizardPage page, int index) {

            }

            @Override
            public void finished(JWizard wizard, Map<String, Object> resultados) {
                resultados.forEach((k, v) -> {
                    System.out.println(k + " : " + v);
                });
            }
        });
    }
}

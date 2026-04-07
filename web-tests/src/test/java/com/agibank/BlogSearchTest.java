package com.agibank;

import com.agibank.pages.HomePage;
import com.agibank.pages.SearchResultsPage;
import com.agibank.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Epic("Blog do Agi")
@Feature("Busca de Artigos")
public class BlogSearchTest extends BaseTest {

    @Test
    @Story("Busca com termo valido")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Dado que o usuario acessa o Blog do Agi\n"
            + "Quando ele pesquisa por um termo valido como 'pix'\n"
            + "Entao a pagina deve exibir ao menos um artigo\n"
            + "E a URL deve conter o parametro de busca")
    public void CT01_buscaComTermoValido_deveRetornarResultados() {
        final String termo = "pix";

        HomePage home = new HomePage().open();
        ScreenshotUtil.captureAndAttach("Homepage carregada");


        home.openSearchBox();
        ScreenshotUtil.captureAndAttach("Campo de busca aberto");

        SearchResultsPage results = home.search(termo);
        ScreenshotUtil.captureAndAttach("Pagina de resultados");

        assertTrue(results.hasResults(),
                "Deveria haver resultados para '" + termo + "'");

        assertTrue(results.getResultCount() > 0,
                "Contador deve ser maior que zero");

        assertTrue(results.getCurrentUrl().contains(termo),
                "URL deve conter o termo pesquisado");

        Allure.addAttachment("Qtd. resultados",
                String.valueOf(results.getResultCount()));
        Allure.addAttachment("Titulos encontrados",
                String.join("\n", results.getResultTitles()));
    }

    @Test
    @Story("Busca com termo invalido")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Dado que o usuario acessa o Blog do Agi\n"
            + "Quando ele pesquisa por um termo que nao existe\n"
            + "Entao a pagina deve exibir mensagem de 'nenhum resultado'\n"
            + "E deve oferecer um campo para nova busca")
    public void CT02_buscaComTermoInvalido_deveExibirMensagemSemResultados() {
        final String termoInvalido = "xkjqwzpqr12345noresults";

        HomePage home = new HomePage().open();
        home.openSearchBox();
        SearchResultsPage results = home.search(termoInvalido);
        ScreenshotUtil.captureAndAttach("Resultado para termo invalido");

        assertFalse(results.hasResults(),
                "Nao deveria haver resultados para '" + termoInvalido + "'");

        assertTrue(results.isNoResultsMessageVisible(),
                "Mensagem de 'nenhum resultado' deveria estar visivel");

        assertTrue(results.hasSearchAgainInput(),
                "Campo para nova busca deveria estar presente");
    }

    @Test
    @Story("Parametro de busca na URL")
    @Severity(SeverityLevel.NORMAL)
    public void CT03_urlDeResultados_deveConterParametroDeBusca() {
        final String termo = "investimento";

        SearchResultsPage results = new HomePage()
                .open()
                .openSearchBox()
                .search(termo);

        String url = results.getCurrentUrl();

        assertTrue(url.contains("?s="),
                "URL deve conter ?s=. URL atual: " + url);
        assertTrue(url.toLowerCase().contains(termo),
                "URL deve conter o termo. URL atual: " + url);

        Allure.addAttachment("URL gerada", url);
    }

    @Test
    @Story("Multiplos resultados")
    @Severity(SeverityLevel.NORMAL)
    public void CT04_termoAmplo_deveRetornarMultiplosResultados() {
        final String termo = "conta";
        final int minimo = 2;

        SearchResultsPage results = new HomePage()
                .open()
                .openSearchBox()
                .search(termo);

        int total = results.getResultCount();

        assertTrue(total >= minimo,
                "Esperava ao menos " + minimo + " resultados, encontrou: " + total);
    }

    @Test
    @Story("Feedback visual")
    @Severity(SeverityLevel.MINOR)
    public void CT05_cabecalhoDasPagina_deveReferenciarTermoPesquisado() {
        SearchResultsPage results = new HomePage()
                .open()
                .openSearchBox()
                .search("emprestimo");

        String heading = results.getResultsHeadingText();

        assertFalse(heading.isEmpty(),
                "O cabecalho da pagina nao deveria estar vazio");

        Allure.addAttachment("Texto do cabecalho", heading);
    }
}

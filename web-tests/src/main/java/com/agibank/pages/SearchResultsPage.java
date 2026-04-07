package com.agibank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object para a pagina de resultados da busca.
 */
public class SearchResultsPage extends BasePage {

    // elemento de busca: a[aria-label="Search button"]
    private final By resultArticles = By.cssSelector("article.post");
    private final By resultTitles = By.cssSelector("article.post h2.entry-title a");
    private final By noResultsMessage = By.cssSelector(".no-results");
    private final By searchAgainInput = By.cssSelector(".search-no-results input.search-field");
    private final By resultsHeading = By.cssSelector("h1.page-title");

    public SearchResultsPage() {
        super();
        waitForResultsState();
    }

    private void waitForResultsState() {
        wait.until(driver ->
                !driver.findElements(resultArticles).isEmpty()
                        || !driver.findElements(noResultsMessage).isEmpty()
        );
    }

    private boolean hasElements(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    /** true se ha ao menos um artigo na pagina de resultados */
    public boolean hasResults() {
        return hasElements(resultArticles);
    }

    /** Quantidade de artigos retornados */
    public int getResultCount() {
        return driver.findElements(resultArticles).size();
    }

    /** Lista com os titulos dos artigos encontrados */
    public List<String> getResultTitles() {
        if (!hasResults()) {
            return List.of();
        }
        return driver.findElements(resultTitles)
                .stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .collect(Collectors.toList());
    }

    /** true se a mensagem de "nenhum resultado" esta visivel */
    public boolean isNoResultsMessageVisible() {
        return hasElements(noResultsMessage);
    }

    /** true se o campo "pesquise novamente" esta presente */
    public boolean hasSearchAgainInput() {
        return hasElements(searchAgainInput);
    }

    /** Texto do cabecalho da pagina */
    public String getResultsHeadingText() {
        if (hasElements(resultsHeading)) {
            return waitForVisibility(resultsHeading).getText();
        }
        return "";
    }

    /** URL atual usada para verificar o parametro ?s= */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

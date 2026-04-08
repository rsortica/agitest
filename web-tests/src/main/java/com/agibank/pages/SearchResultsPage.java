package com.agibank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object para a pagina de resultados da busca.
 */
public class SearchResultsPage extends BasePage {

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

    public boolean hasResults() {
        return hasElements(resultArticles);
    }

    public int getResultCount() {
        return driver.findElements(resultArticles).size();
    }

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

    public boolean isNoResultsMessageVisible() {
        return hasElements(noResultsMessage);
    }

    public boolean hasSearchAgainInput() {
        return hasElements(searchAgainInput);
    }

    public String getSearchAgainInputValue() {
        if (!hasSearchAgainInput()) {
            return "";
        }
        return driver.findElement(searchAgainInput).getAttribute("value");
    }

    public String getResultsHeadingText() {
        if (hasElements(resultsHeading)) {
            return waitForVisibility(resultsHeading).getText();
        }
        return "";
    }

    public boolean headingContainsTerm(String term) {
        return getResultsHeadingText().toLowerCase().contains(term.toLowerCase());
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean currentUrlContainsSearchTerm(String term) {
        return getCurrentUrl().toLowerCase().contains(term.toLowerCase());
    }
}

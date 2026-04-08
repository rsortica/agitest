package com.agibank.pages;

import com.agibank.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Page Object para a pagina inicial do Blog do Agi.
 */
public class HomePage extends BasePage {

    private final By searchIconButton = By.cssSelector("a[aria-label='Search button']");
    private final By searchInput = By.cssSelector("input.search-field");

    public HomePage() {
        super();
    }

    public HomePage open() {
        driver.get(TestConfig.baseUrl());
        return this;
    }

    public HomePage openSearchBox() {
        boolean hasVisibleSearchInput = driver.findElements(searchInput).stream()
                .anyMatch(WebElement::isDisplayed);

        if (!hasVisibleSearchInput) {
            WebElement trigger = waitForClickable(searchIconButton);
            try {
                trigger.click();
            } catch (RuntimeException ex) {
                clickWithJS(trigger);
            }
        }

        try {
            waitForVisibility(searchInput);
        } catch (TimeoutException ignored) {
            // O tema atual mantem o campo oculto em alguns contextos. O search() trata fallback via URL.
        }
        return this;
    }

    public SearchResultsPage search(String term) {
        WebElement input = driver.findElements(searchInput).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null);

        if (input != null) {
            input.clear();
            input.sendKeys(term, Keys.ENTER);
        } else {
            return searchByUrl(term);
        }

        return new SearchResultsPage();
    }

    public SearchResultsPage searchByUrl(String term) {
        String encodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8);
        driver.get(TestConfig.baseUrl() + "?s=" + encodedTerm);
        return new SearchResultsPage();
    }

    public boolean isSearchInputVisible() {
        return driver.findElements(searchInput).stream()
                .anyMatch(WebElement::isDisplayed);
    }

    public boolean hasSearchInputElement() {
        return !driver.findElements(searchInput).isEmpty();
    }

    public String getVisibleSearchInputValue() {
        return driver.findElements(searchInput).stream()
                .filter(WebElement::isDisplayed)
                .map(element -> element.getAttribute("value"))
                .findFirst()
                .orElse("");
    }
}

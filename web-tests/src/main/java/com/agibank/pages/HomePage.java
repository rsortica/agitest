package com.agibank.pages;

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

    private static final String URL = "https://blog.agibank.com.br/";

    private final By searchIconButton = By.cssSelector("a[aria-label='Search button']");
    private final By searchInput = By.cssSelector("input.search-field");

    public HomePage() {
        super();
    }

    public HomePage open() {
        driver.get(URL);
        return this;
    }

    public HomePage openSearchBox() {
        if (driver.findElements(searchInput).isEmpty()) {
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
            String encodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8);
            driver.get(URL + "?s=" + encodedTerm);
        }

        return new SearchResultsPage();
    }
}

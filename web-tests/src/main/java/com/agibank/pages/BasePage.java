package com.agibank.pages;

import com.agibank.config.TestConfig;
import com.agibank.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Classe base para todos os Page Objects.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(TestConfig.explicitTimeoutSeconds())
        );
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }
}

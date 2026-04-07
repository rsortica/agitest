package com.agibank.pages;

import com.agibank.utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Classe base para todos os Page Objects.
 *
 * Centraliza:
 * - Referência ao driver e ao wait
 * - Esperas explícitas reutilizáveis (sem Thread.sleep!)
 * - Ações comuns como scroll e click via JS
 */

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int TIMEOUT = 15; //segundos

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(driver, this); // Inicializa @FindBy se usar
    }

    // Espera o elemento ficar visível
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Espera o elemento ficar clicável
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Espera todos os elementos ficarem visíveis
    protected List<WebElement> waitForAllVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
    }
    // Verifica presença sem lançar exceção
    protected boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void scrollToElement(WebElement el) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", el);
    }

    // Útil quando o click normal é bloqueado por overlay
    protected void clickWithJS(WebElement el) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", el);
    }
}

package com.agibank.utils;

import com.agibank.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Gerencia o ciclo de vida do WebDriver por thread.
 */
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverFactory() {}

    public static void initDriver() {
        if (driverThread.get() != null) {
            return;
        }

        String browser = TestConfig.browser();
        validateBrowser(browser);

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = buildChromeOptions(TestConfig.headless());

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(TestConfig.pageLoadTimeoutSeconds()));
        driverThread.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThread.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "Driver nao inicializado. Chame initDriver() no @BeforeMethod."
            );
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }

    static void validateBrowser(String browser) {
        if (!"chrome".equals(browser)) {
            throw new IllegalArgumentException("Browser nao suportado: " + browser);
        }
    }

    static ChromeOptions buildChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        return options;
    }
}

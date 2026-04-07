package com.agibank.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.ByteArrayInputStream;

/**
 * Captura screenshots e anexa ao Allure quando a sessao ainda estiver valida.
 */
public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static void captureAndAttach(String name) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver instanceof TakesScreenshot) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(
                        name,
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        "png"
                );
            } catch (WebDriverException ignored) {
                // Mantem a falha original do teste.
            }
        }
    }
}

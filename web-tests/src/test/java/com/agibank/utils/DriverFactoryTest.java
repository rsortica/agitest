package com.agibank.utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

public class DriverFactoryTest {

    @Test
    public void deveRejeitarBrowserNaoSuportado() {
        IllegalArgumentException exception = expectThrows(
                IllegalArgumentException.class,
                () -> DriverFactory.validateBrowser("firefox")
        );

        assertTrue(exception.getMessage().contains("Browser nao suportado"));
    }

    @Test
    public void deveMontarChromeOptionsParaHeadless() {
        ChromeOptions options = DriverFactory.buildChromeOptions(true);
        Map<String, Object> optionsMap = options.asMap();
        @SuppressWarnings("unchecked")
        Map<String, Object> chromeOptions = (Map<String, Object>) optionsMap.get("goog:chromeOptions");
        @SuppressWarnings("unchecked")
        List<String> args = (List<String>) chromeOptions.get("args");

        assertTrue(args.contains("--headless=new"));
        assertTrue(args.contains("--window-size=1920,1080"));
        assertEquals(String.valueOf(optionsMap.get("pageLoadStrategy")), PageLoadStrategy.NORMAL.toString());
    }

    @Test
    public void deveMontarChromeOptionsParaModoGrafico() {
        ChromeOptions options = DriverFactory.buildChromeOptions(false);
        Map<String, Object> optionsMap = options.asMap();
        @SuppressWarnings("unchecked")
        Map<String, Object> chromeOptions = (Map<String, Object>) optionsMap.get("goog:chromeOptions");
        @SuppressWarnings("unchecked")
        List<String> args = (List<String>) chromeOptions.get("args");

        assertTrue(args.contains("--start-maximized"));
        assertTrue(args.contains("--disable-gpu"));
        assertEquals(String.valueOf(optionsMap.get("pageLoadStrategy")), PageLoadStrategy.NORMAL.toString());
    }
}

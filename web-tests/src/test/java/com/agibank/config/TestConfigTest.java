package com.agibank.config;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestConfigTest {

    @AfterMethod(alwaysRun = true)
    public void clearProperties() {
        System.clearProperty("base.url");
        System.clearProperty("browser");
        System.clearProperty("headless");
        System.clearProperty("explicit.timeout.seconds");
        System.clearProperty("page.load.timeout.seconds");
    }

    @Test
    public void deveUsarValoresPadraoQuandoNaoHaPropriedades() {
        assertEquals(TestConfig.baseUrl(), "https://blog.agibank.com.br/");
        assertEquals(TestConfig.browser(), "chrome");
        assertTrue(TestConfig.headless());
        assertEquals(TestConfig.explicitTimeoutSeconds(), 15);
        assertEquals(TestConfig.pageLoadTimeoutSeconds(), 30);
    }

    @Test
    public void deveRespeitarPropriedadesDeSistema() {
        System.setProperty("base.url", "https://example.org");
        System.setProperty("browser", "CHROME");
        System.setProperty("headless", "false");
        System.setProperty("explicit.timeout.seconds", "22");
        System.setProperty("page.load.timeout.seconds", "44");

        assertEquals(TestConfig.baseUrl(), "https://example.org/");
        assertEquals(TestConfig.browser(), "chrome");
        assertFalse(TestConfig.headless());
        assertEquals(TestConfig.explicitTimeoutSeconds(), 22);
        assertEquals(TestConfig.pageLoadTimeoutSeconds(), 44);
    }
}

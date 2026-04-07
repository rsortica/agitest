package com.agibank;

import com.agibank.utils.DriverFactory;
import com.agibank.utils.ScreenshotUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Classe base para todos os testes Web.
 * Gerencia o ciclo de vida do driver e captura screenshot em falhas.
 */
public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Captura screenshot automaticamente se o teste falhou
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.captureAndAttach("FALHA - " + result.getName());
        }
        DriverFactory.quitDriver();
    }
}

package com.agibank.utils;

import com.agibank.config.TestConfig;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Publica evidencias textuais uteis para diagnostico rapido no Allure.
 */
public final class TestEvidenceUtil {

    private TestEvidenceUtil() {}

    public static void attachExecutionContext(ITestResult result) {
        String groups = Arrays.stream(result.getMethod().getGroups())
                .collect(Collectors.joining(", "));

        String content = String.format(
                "Teste: %s%nStatus: %s%nGrupos: %s%nBase URL: %s%nBrowser: %s%nHeadless: %s",
                result.getName(),
                statusLabel(result.getStatus()),
                groups.isBlank() ? "-" : groups,
                TestConfig.baseUrl(),
                TestConfig.browser(),
                TestConfig.headless()
        );

        Allure.addAttachment("Contexto de execucao", content);
    }

    public static void attachDriverContext() {
        try {
            WebDriver driver = DriverFactory.getDriver();
            String content = String.format(
                    "URL atual: %s%nTitulo: %s",
                    driver.getCurrentUrl(),
                    driver.getTitle()
            );
            Allure.addAttachment("Contexto do navegador", content);
        } catch (IllegalStateException | WebDriverException ignored) {
            // Mantem o teardown resiliente quando nao ha sessao disponivel.
        }
    }

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, content);
    }

    private static String statusLabel(int status) {
        switch (status) {
            case ITestResult.SUCCESS:
                return "SUCCESS";
            case ITestResult.FAILURE:
                return "FAILURE";
            case ITestResult.SKIP:
                return "SKIP";
            default:
                return "UNKNOWN";
        }
    }
}

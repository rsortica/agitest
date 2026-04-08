package com.agibank.config;

public final class TestConfig {

    private static final String DEFAULT_BASE_URL = "https://blog.agibank.com.br/";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final boolean DEFAULT_HEADLESS = true;
    private static final int DEFAULT_EXPLICIT_TIMEOUT_SECONDS = 15;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT_SECONDS = 30;

    private TestConfig() {}

    public static String baseUrl() {
        String value = read("base.url", "BASE_URL");
        if (value == null || value.isBlank()) {
            return DEFAULT_BASE_URL;
        }
        return value.endsWith("/") ? value : value + "/";
    }

    public static String browser() {
        String value = read("browser", "BROWSER");
        if (value == null || value.isBlank()) {
            return DEFAULT_BROWSER;
        }
        return value.trim().toLowerCase();
    }

    public static boolean headless() {
        String value = read("headless", "HEADLESS");
        if (value == null || value.isBlank()) {
            return DEFAULT_HEADLESS;
        }
        return Boolean.parseBoolean(value);
    }

    public static int explicitTimeoutSeconds() {
        return intValue(
                read("explicit.timeout.seconds", "EXPLICIT_TIMEOUT_SECONDS"),
                DEFAULT_EXPLICIT_TIMEOUT_SECONDS
        );
    }

    public static int pageLoadTimeoutSeconds() {
        return intValue(
                read("page.load.timeout.seconds", "PAGE_LOAD_TIMEOUT_SECONDS"),
                DEFAULT_PAGE_LOAD_TIMEOUT_SECONDS
        );
    }

    private static String read(String propertyName, String envName) {
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue != null && !propertyValue.isBlank()) {
            return propertyValue;
        }
        return System.getenv(envName);
    }

    private static int intValue(String rawValue, int defaultValue) {
        if (rawValue == null || rawValue.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(rawValue);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Valor numerico invalido: " + rawValue, ex);
        }
    }
}

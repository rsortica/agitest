# Testes Web - Blog do Agi

Automacao de testes da busca do Blog do Agi com Java, Selenium, TestNG e Allure.

## Stack

- Java 11+
- Maven 3.8+
- Selenium 4.18
- WebDriverManager 5.7
- TestNG 7.9
- Allure 2.25

## Estrutura

```text
web-tests/
|-- src/
|   |-- main/java/com/agibank/
|   |   |-- pages/
|   |   |   |-- BasePage.java
|   |   |   |-- HomePage.java
|   |   |   `-- SearchResultsPage.java
|   |   `-- utils/
|   |       `-- DriverFactory.java
|   `-- test/
|       |-- java/com/agibank/
|       |   |-- BaseTest.java
|       |   |-- BlogSearchTest.java
|       |   `-- utils/ScreenshotUtil.java
|       `-- resources/testng.xml
`-- pom.xml
```

## Como executar

Execucao padrao:

```bash
mvn test
```

Execucao com interface grafica:

```bash
mvn test -Dheadless=false
```

Executar um teste especifico:

```bash
mvn test -Dtest=BlogSearchTest#CT01_buscaComTermoValido_deveRetornarResultados
```

## Allure

Gerar relatorio:

```bash
mvn allure:serve
```

Ou:

```bash
mvn allure:report
```

## Observacoes

- O blog atual responde em `https://blog.agibank.com.br/`.
- O ChromeDriver e resolvido automaticamente pelo WebDriverManager.
- O projeto hoje nao possui workflow de GitHub Actions versionado neste repositorio.

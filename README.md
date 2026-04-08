# Testes Web - Blog do Agi

Automacao de testes da busca do Blog do Agi com Java, Selenium, TestNG e Allure.

## Arquitetura

- `pom.xml` na raiz atua como agregador Maven.
- `web-tests/` contem o modulo de automacao web.
- A configuracao de execucao fica centralizada em `com.agibank.config.TestConfig`.
- A pipeline executa a smoke suite do modulo `web-tests`.

## Stack

- Java 11+
- Maven 3.8+
- Selenium 4.39
- WebDriverManager 5.7
- TestNG 7.9
- Allure 2.25

## Estrutura

```text
.
|-- docs/
|   `-- cenarios-bdd.md
|-- pom.xml
`-- web-tests/
    |-- pom.xml
    `-- src/
        |-- main/java/com/agibank/
        |   |-- config/
        |   |   `-- TestConfig.java
        |   |-- pages/
        |   |   |-- BasePage.java
        |   |   |-- HomePage.java
        |   |   `-- SearchResultsPage.java
        |   `-- utils/
        |       `-- DriverFactory.java
        `-- test/
            |-- java/com/agibank/
            |   |-- BaseTest.java
            |   |-- BlogSearchTest.java
            |   `-- utils/ScreenshotUtil.java
            `-- resources/
                |-- testng-full.xml
                `-- testng-smoke.xml
```

## Cenarios

- Catalogo BDD dos cenarios automatizados: [docs/cenarios-bdd.md](/C:/workspace/agitest/docs/cenarios-bdd.md)

## Como executar

Execucao padrao da smoke suite:

```bash
mvn test -pl web-tests
```

Execucao com interface grafica:

```bash
mvn test -pl web-tests -Dheadless=false
```

Executar a regressao completa por classe:

```bash
mvn test -pl web-tests -Dtest=BlogSearchTest
```

## Parametros suportados

- `-Dbase.url=https://blog.agibank.com.br/`
- `-Dbrowser=chrome`
- `-Dheadless=true`
- `-Dexplicit.timeout.seconds=15`
- `-Dpage.load.timeout.seconds=30`

As mesmas configuracoes tambem podem ser fornecidas por variaveis de ambiente:
`BASE_URL`, `BROWSER`, `HEADLESS`, `EXPLICIT_TIMEOUT_SECONDS` e `PAGE_LOAD_TIMEOUT_SECONDS`.

## Allure

Gerar relatorio:

```bash
mvn -pl web-tests allure:serve
```

Ou:

```bash
mvn -pl web-tests allure:report
```

## Observacoes

- O blog atual responde em `https://blog.agibank.com.br/`.
- O pipeline de GitHub Actions esta em `.github/workflows/web-tests.yml`.
- A execucao em CI usa a smoke suite com `mvn -B -pl web-tests test -Dheadless=true`.
- Os artefatos publicados pela pipeline incluem `web-tests/target/surefire-reports` e `web-tests/target/allure-results`.

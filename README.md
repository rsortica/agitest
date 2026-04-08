# Testes Web - Blog do Agi

Automação de testes da busca do Blog do Agi com Java, Selenium, TestNG e Allure.

## Arquitetura

- `pom.xml` na raiz atua como agregador Maven.
- `web-tests/` contém o módulo de automação web.
- A configuração de execução fica centralizada em `com.agibank.config.TestConfig`.
- A pipeline executa a smoke suite do módulo `web-tests`.

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

## Cenários

- Catálogo BDD dos cenários automatizados: [docs/cenarios-bdd.md](/C:/workspace/agitest/docs/cenarios-bdd.md)
- Estratégia de testes: [docs/estrategia-de-testes.md](/C:/workspace/agitest/docs/estrategia-de-testes.md)
- Matriz de cobertura funcional: [docs/matriz-cobertura-funcional.md](/C:/workspace/agitest/docs/matriz-cobertura-funcional.md)

## Abordagem

Este projeto foi estruturado como portfólio de automação funcional black-box.
Como a aplicação testada é externa e não há acesso ao seu código-fonte, a cobertura aqui representa cobertura funcional dos fluxos automatizados e não cobertura de código da aplicação real.

As suítes priorizam comportamento observável, feedback de falha e resiliência a mudanças de conteúdo do site.
Por isso, a automação separa smoke e regression e evita asserts acoplados a detalhes editoriais excessivamente voláteis.

## Como executar

Execução padrão da smoke suite:

```bash
mvn test -pl web-tests -Dsuite.xml.file=src/test/resources/testng-smoke.xml
```

Execução com interface gráfica:

```bash
mvn test -pl web-tests -Dheadless=false
```

Executar a regressão funcional:

```bash
mvn test -pl web-tests -Dsuite.xml.file=src/test/resources/testng-regression.xml
```

Executar a suíte completa:

```bash
mvn test -pl web-tests -Dsuite.xml.file=src/test/resources/testng-full.xml
```

## Parâmetros suportados

- `-Dbase.url=https://blog.agibank.com.br/`
- `-Dbrowser=chrome`
- `-Dheadless=true`
- `-Dexplicit.timeout.seconds=15`
- `-Dpage.load.timeout.seconds=30`

As mesmas configurações também podem ser fornecidas por variáveis de ambiente:
`BASE_URL`, `BROWSER`, `HEADLESS`, `EXPLICIT_TIMEOUT_SECONDS` e `PAGE_LOAD_TIMEOUT_SECONDS`.

## Allure

Gerar relatório:

```bash
mvn -pl web-tests allure:serve
```

Ou:

```bash
mvn -pl web-tests allure:report
```

## Observações

- O blog atual responde em `https://blog.agibank.com.br/`.
- O pipeline de GitHub Actions está em `.github/workflows/web-tests.yml`.
- A execução em CI usa a smoke suite com `mvn -B -pl web-tests test -Dheadless=true -Dsuite.xml.file=src/test/resources/testng-smoke.xml`.
- Os artefatos publicados pela pipeline incluem `web-tests/target/surefire-reports` e `web-tests/target/allure-results`.

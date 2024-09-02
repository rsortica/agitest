# Projeto de Testes Automatizados com Cypress

Este repositório contém testes automatizados para o blog do Agi, utilizando o Cypress para garantir a qualidade e o funcionamento do site. O projeto também está configurado para rodar os testes automaticamente através de GitHub Actions.

## Configuração do Projeto

Siga as instruções abaixo para configurar e executar o projeto localmente.

### Pré-requisitos

Certifique-se de ter o Node.js e o npm instalados em sua máquina. Você pode baixar e instalar o Node.js [aqui](https://nodejs.org/).

### Passos para Configuração

1. **Clone o repositório**

- * Abra o navegador
- * Acesse a URL https://github.com/rsortica/agitest
- * Faça um clone do projeto, utilizando o comando:

    >   ```bash
    >   git clone https://github.com/rsortica/agitest.git


2. **Instalação as dependências** 🌲

1. Na raiz do projeto, execute o comando `npm install cypress --save-dev`
2. Logo após, execute o comando `npx cypress open` para abrir o Cypress
3. Por fim, com o _Test Runner_ aberto, execute o teste em modo interativo.
4. Pronto!

Obs.: 
Para executar os testes no modo headless (sem interface gráfica), execute o comando:

> npx cypress run

Os resultados dos testes serão exibidos no terminal.

### Configuração do GitHub Actions
O projeto está configurado para executar os testes automaticamente em cada push para a branch principal ou em pull requests. Isso é feito através do GitHub Actions.

**Configuração do Workflow**
O arquivo de configuração do GitHub Actions está localizado em `.github/workflows/cypress.yml`. Este arquivo define o workflow para:

Instalar as dependências do projeto.
    * Instalar o Cypress.
    * Executar os testes automatizados.
    * Workflow de GitHub Actions

O arquivo `.github/workflows/cypress.yml` tem a seguinte configuração:

> name: Run Cypress Tests

    on:
    push:
        branches:
        - main
    pull_request:
        branches:
        - main

    jobs:
    cypress-tests:
        runs-on: ubuntu-latest

        steps:
        - name: Checkout code
        uses: actions/checkout@v3

        - name: Setup Node.js
        uses: actions/setup-node@v3
        with:
            node-version: '16'

        - name: Install dependencies
        run: npm install

        - name: Install Cypress
        run: npm install cypress

        - name: Run Cypress tests
        run: npx cypress run
        env:
            CYPRESS_baseUrl: https://blogdoagi.com.br/

## Contribuindo
Se você encontrar problemas ou tiver dúvidas sobre o projeto, abra uma issue no repositório ou entre em contato.
___

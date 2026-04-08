# Estratégia de Testes

## Contexto

Este projeto automatiza testes funcionais black-box sobre o site público do Blog do Agi.
Como não há acesso ao código-fonte da aplicação nem controle sobre massa de dados, a estratégia prioriza comportamento observável, resiliência e evidências de execução.

## Objetivo

Demonstrar capacidade de:

- estruturar uma automação web para pipeline
- selecionar cenários com base em risco funcional
- produzir evidências claras de execução e falha
- reduzir fragilidade em ambiente externo e não controlado

## Escopo

A automação cobre a feature de busca do blog, com foco em:

- retorno de resultados para termos válidos
- comportamento para termos inválidos
- consistência entre URL, heading e campo de busca
- recuperação da experiência do usuário quando não há resultados
- presença do componente de busca na home

## Tipos de suíte

### Smoke

Usada na pipeline para validar rapidamente o fluxo principal da busca em ambiente real.

### Regression

Usada para ampliar a cobertura funcional da feature, incluindo feedback visual e cenários auxiliares.

## Princípios adotados

- Validar comportamento de negócio observável em vez de implementação interna.
- Evitar asserts excessivamente acoplados à quantidade exata de conteúdo editorial.
- Manter fallback controlado quando o tema do site altera a interação visual da busca.
- Registrar evidências suficientes para diagnosticar falhas sem precisar reproduzir localmente.

## Fora de escopo

- cobertura de código da aplicação real
- validação de APIs internas
- controle de massa de dados
- testes de performance, segurança e acessibilidade profunda

## Riscos conhecidos

- Mudanças de conteúdo no site podem alterar relevância e quantidade de resultados.
- Mudanças de tema podem afetar a visibilidade do campo de busca sem quebrar o backend de busca.
- Como a execução depende de navegador e site externos, a suíte pode sofrer variações ambientais.

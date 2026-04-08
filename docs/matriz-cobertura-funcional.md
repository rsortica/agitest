# Matriz de Cobertura Funcional

## Visão geral

| ID | Cenário | Tipo | Risco coberto | Status |
| --- | --- | --- | --- | --- |
| CT01 | Busca com termo válido | Smoke, Regression | Busca principal indisponível ou sem retorno | Automatizado |
| CT02 | Busca com termo inválido | Smoke, Regression | Usuário sem feedback em busca sem resultado | Automatizado |
| CT03 | URL de resultados contém o termo | Smoke, Regression | Navegação inconsistente após busca | Automatizado |
| CT04 | Termo amplo retorna ao menos um resultado | Regression | Falha de indexação ou retorno vazio indevido | Automatizado |
| CT05 | Cabeçalho referencia o termo pesquisado | Regression | Feedback visual inconsistente na página de resultados | Automatizado |
| CT06 | Componente de busca presente na home | Regression | Ponto de entrada da busca indisponível | Automatizado |
| CT07 | Busca direta por URL retorna resultados | Regression | Fluxo de busca indisponível sem dependência de UI | Automatizado |
| CT08 | Busca com caixa alta retorna resultados | Regression | Tratamento inconsistente de caixa no termo pesquisado | Automatizado |
| CT09 | Busca com espaços extras retorna resultados | Regression | Falha de normalização de entrada do usuário | Automatizado |

## Cobertura por objetivo funcional

| Objetivo funcional | Cenários |
| --- | --- |
| Pesquisar com sucesso | CT01, CT03, CT04, CT07 |
| Tratar busca sem resultado | CT02 |
| Exibir feedback visual coerente | CT05 |
| Disponibilizar entrada para busca | CT06 |
| Normalizar entrada do usuário | CT08, CT09 |

## Leitura da cobertura

- A cobertura atual é funcional e orientada a comportamento observável.
- Os cenários priorizam o fluxo principal da feature de busca.
- Há cobertura específica tanto para busca por interação quanto para busca direta por URL.
- Como o sistema testado é externo, a cobertura evita depender de massa fixa ou estrutura interna da aplicação.

# Cenarios BDD - Blog do Agi

Este documento descreve os cenarios automatizados da feature de busca do Blog do Agi.

## Listagem de cenarios

### CT01 - Busca com termo valido

**Objetivo:** validar que uma busca valida retorna resultados.

**Cenario**

```gherkin
Cenario: Buscar artigo com termo valido
  Dado que o usuario acessa a pagina inicial do Blog do Agi
  Quando ele pesquisa por um termo valido como "pix"
  Entao a pagina deve exibir ao menos um resultado
  E a URL deve conter o termo pesquisado
```

### CT02 - Busca com termo invalido

**Objetivo:** validar a resposta da aplicacao quando nao existem resultados.

**Cenario**

```gherkin
Cenario: Buscar artigo com termo inexistente
  Dado que o usuario acessa a pagina inicial do Blog do Agi
  Quando ele pesquisa por um termo inexistente
  Entao a pagina deve exibir mensagem de nenhum resultado
  E deve disponibilizar um campo para nova busca
  E deve preservar o termo pesquisado no campo de nova busca
```

### CT03 - URL de resultados

**Objetivo:** validar que a navegacao de busca preserva o termo na URL final.

**Cenario**

```gherkin
Cenario: Exibir termo buscado na URL de resultados
  Dado que o usuario acessa a pagina inicial do Blog do Agi
  Quando ele pesquisa por um termo como "investimento"
  Entao a URL da pagina de resultados deve conter o termo pesquisado
```

### CT04 - Retorno para termo amplo

**Objetivo:** validar que termos amplos retornam ao menos um artigo.

**Cenario**

```gherkin
Cenario: Buscar artigo com termo amplo
  Dado que o usuario acessa a pagina inicial do Blog do Agi
  Quando ele pesquisa por um termo amplo como "conta"
  Entao a pagina deve retornar ao menos um resultado
```

### CT05 - Cabecalho da pagina de resultados

**Objetivo:** validar o feedback visual da pagina de busca.

**Cenario**

```gherkin
Cenario: Exibir o termo pesquisado no cabecalho dos resultados
  Dado que o usuario acessa a pagina inicial do Blog do Agi
  Quando ele pesquisa por um termo como "emprestimo"
  Entao o cabecalho da pagina de resultados nao deve estar vazio
  E o cabecalho deve referenciar o termo pesquisado
```

### CT06 - Exibicao do componente de busca

**Objetivo:** validar a presenca do componente de busca na home.

**Cenario**

```gherkin
Cenario: Exibir componente de busca ao abrir a home
  Dado que o usuario acessa a pagina inicial do Blog do Agi
  Quando ele abre o componente de busca
  Entao a pagina deve conter o componente de busca
  E se o campo estiver visivel ele deve iniciar vazio
```

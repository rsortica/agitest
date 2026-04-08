# Cenários BDD - Blog do Agi

Este documento descreve os cenários automatizados da feature de busca do Blog do Agi.

## Listagem de cenários

### CT01 - Busca com termo válido

**Objetivo:** validar que uma busca válida retorna resultados.

**Cenário**

```gherkin
Cenário: Buscar artigo com termo válido
  Dado que o usuário acessa a página inicial do Blog do Agi
  Quando ele pesquisa por um termo válido como "pix"
  Então a página deve exibir ao menos um resultado
  E a URL deve conter o termo pesquisado
```

### CT02 - Busca com termo inválido

**Objetivo:** validar a resposta da aplicação quando não existem resultados.

**Cenário**

```gherkin
Cenário: Buscar artigo com termo inexistente
  Dado que o usuário acessa a página inicial do Blog do Agi
  Quando ele pesquisa por um termo inexistente
  Então a página deve exibir mensagem de nenhum resultado
  E deve disponibilizar um campo para nova busca
  E deve preservar o termo pesquisado no campo de nova busca
```

### CT03 - URL de resultados

**Objetivo:** validar que a navegação de busca preserva o termo na URL final.

**Cenário**

```gherkin
Cenário: Exibir termo buscado na URL de resultados
  Dado que o usuário acessa a página inicial do Blog do Agi
  Quando ele pesquisa por um termo como "investimento"
  Então a URL da página de resultados deve conter o termo pesquisado
```

### CT04 - Retorno para termo amplo

**Objetivo:** validar que termos amplos retornam ao menos um artigo.

**Cenário**

```gherkin
Cenário: Buscar artigo com termo amplo
  Dado que o usuário acessa a página inicial do Blog do Agi
  Quando ele pesquisa por um termo amplo como "conta"
  Então a página deve retornar ao menos um resultado
```

### CT05 - Cabeçalho da página de resultados

**Objetivo:** validar o feedback visual da página de busca.

**Cenário**

```gherkin
Cenário: Exibir o termo pesquisado no cabeçalho dos resultados
  Dado que o usuário acessa a página inicial do Blog do Agi
  Quando ele pesquisa por um termo como "emprestimo"
  Então o cabeçalho da página de resultados não deve estar vazio
  E o cabeçalho deve referenciar o termo pesquisado
```

### CT06 - Exibição do componente de busca

**Objetivo:** validar a presença do componente de busca na home.

**Cenário**

```gherkin
Cenário: Exibir componente de busca ao abrir a home
  Dado que o usuário acessa a página inicial do Blog do Agi
  Quando ele abre o componente de busca
  Então a página deve conter o componente de busca
  E se o campo estiver visível ele deve iniciar vazio
```

### CT07 - Busca direta por URL

**Objetivo:** validar o fluxo de busca sem depender da interação visual do componente.

**Cenário**

```gherkin
Cenário: Navegar diretamente para uma URL de busca válida
  Dado que o usuário acessa o Blog do Agi
  Quando ele navega diretamente para a URL de busca com o termo "pix"
  Então a página deve exibir resultados para o termo informado
  E a URL final deve conter o termo pesquisado
```

### CT08 - Busca com caixa alta

**Objetivo:** validar que a busca continua funcional para termos em caixa alta.

**Cenário**

```gherkin
Cenário: Buscar artigo com termo em caixa alta
  Dado que o usuário acessa o Blog do Agi
  Quando ele pesquisa por um termo em caixa alta como "PIX"
  Então a busca deve retornar resultados
```

### CT09 - Busca com espaços extras

**Objetivo:** validar que a busca tolera espaços extras no termo pesquisado.

**Cenário**

```gherkin
Cenário: Buscar artigo com espaços extras no termo
  Dado que o usuário acessa o Blog do Agi
  Quando ele pesquisa por um termo com espaços extras como " pix "
  Então a busca deve retornar resultados
```

/// <reference types="Cypress" />

describe('Pesquisa no site', () => {
  beforeEach(() => {
      // Acessar o site
      cy.visit('https://blogdoagi.com.br/');
  });

  it('verifica o título da aplicação', () => {
      cy.title().should('eq', 'Blog do Agi | Tudo sobre empréstimo e educação financeira');
  });

  it('deve permitir pesquisar no site e exibir resultados', () => {
      // Clicar na lupa de pesquisa
      cy.get('.site-header-above-section-right > .ast-builder-layout-element').click();
      
      // Digitar o texto 'portabilidade' no campo de pesquisa e submeter
      cy.get('#search-field')
        .type('portabilidade')
        .type('{enter}');

      // Validar que a página de resultados contém o texto esperado
      cy.get('.page-title')
        .should('contain.text', 'Resultados encontrados para: portabilidade');
  });
});

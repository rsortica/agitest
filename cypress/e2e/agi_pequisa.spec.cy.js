
/// <reference types="Cypress" />
// cypress/integration/pesquisa.spec.js

describe('Pesquisa no site', () => {
    beforeEach(function(){
      // Acessar o site
      cy.visit('https://blogdoagi.com.br/');
    })

    it('verifica o titulo da aplicacao', () => {
        cy.title().should('be.equal', 'Blog do Agi | Tudo sobre empréstimo e educação financeira')
    })

    it('deve permitir pesquisar no site e exibir resultados', () => {
     
      // Clicar na lupa de pesquisa
      cy.get('.site-header-above-section-right > .ast-builder-layout-element').click();
      
      // Digitar o texto 'portabilidade' no campo de pesquisa
      cy.get('#search-field')
        .type('portabilidade')
        .type('{enter}'); // Clicar Enter para submeter a pesquisa

     
      // Validar que na nova página o texto esperado está presente
      cy.get('.page-title')
        .should('contain.text', 'Resultados encontrados para: portabilidade');
    });
  });
  
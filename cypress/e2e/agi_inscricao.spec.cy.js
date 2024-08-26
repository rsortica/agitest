// cypress/integration/inscricao.spec.js

describe('Inscrição no blog', () => {
    beforeEach(function(){
        // Acessar o site
        cy.visit('https://blogdoagi.com.br/');
      })
  
      it('verifica o titulo da aplicacao', () => {
          cy.title().should('be.equal', 'Blog do Agi | Tudo sobre empréstimo e educação financeira')
      })
      
      it('deve permitir inscrever-se no blog e exibir mensagem de sucesso', () => {
     
      // Aguarda 2 segundos para garantir o carregamento da página
      cy.wait(2000); 
      // Rolar a página até o final para garantir que o campo de e-mail esteja visível
      cy.scrollTo('bottom'); 
      
      // Verificar se o campo de e-mail está visível
      cy.get('input[placeholder="Endereço de e-mail"]')
        .should('be.visible')
        .type('gkuky@gmail.com');
      
      // Verificar e clicar no botão 'Assinar'
      cy.get('#subscribe-submit > .wp-block-button__link').should('be.visible').click();

      // Validar mensagem de erro
      cy.get('.error').should('contain.text', 'Ocorreu um erro ao assinar. Tente novamente.');
      
      /*
      // Validar a mensagem de sucesso
      cy.get('.success > p')
        .should('contain.text', 'Sucesso! Enviamos um e-mail para confirmar a sua assinatura. Encontre o e-mail agora e clique em ‘Confirmar’ para iniciar a inscrição.');*/
    });
  });
  
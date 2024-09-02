
describe('Inscrição no blog', () => {
  beforeEach(() => {
      // Acessar o site
      cy.visit('https://blogdoagi.com.br/');
  });

  it('verifica o titulo da aplicação', () => {
      cy.title().should('eq', 'Blog do Agi | Tudo sobre empréstimo e educação financeira');
  });

  it('deve permitir inscrever-se no blog e exibir mensagem de sucesso', () => {
      // Rolar a página até o final para garantir que o campo de e-mail esteja visível
      cy.scrollTo('bottom');

      // Verificar se o campo de e-mail está visível e preencher
      cy.get('input[placeholder="Endereço de e-mail"]')
        .should('be.visible')
        .type('gkuky@gmail.com');

      // Clicar no botão 'Assinar'
      cy.get('#subscribe-submit > .wp-block-button__link')
        .should('be.visible')
        .click();

      // Validar a mensagem de sucesso
      cy.get('.success > p')
        .should('contain.text', 'Sucesso! Enviamos um e-mail para confirmar a sua assinatura. Encontre o e-mail agora e clique em ‘Confirmar’ para iniciar a inscrição.');
  });
});

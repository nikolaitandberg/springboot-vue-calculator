describe('Form validation and submission', () => {
  it('should disable the submit button when the email is invalid', () => {
    cy.visit('/contact')

    cy.get('input[id="email"]').type('invalid-email')
    cy.get('button[id="submit"]').should('be.disabled')
  })

  it('should get successful response when form is correctly filled and submit button is pressed', () => {
    cy.visit('/contact')

    cy.get('input[id="name"]').type('test testesen')
    cy.get('input[id="email"]').type('test@example.com')
    cy.get('input[id="msg"]').type('dette er en e2e test gjort med cyprus.')

    cy.get('button[id="submit"]').should('not.be.disabled')

    // Intercept the POST request and provide a mock response
    cy.intercept('POST', 'http://localhost:3000/contact-messages', {
      statusCode: 201,
      body: { message: 'Form submitted' },
    }).as('formSubmit')

    cy.get('button[id="submit"]').click()

    // Wait for the intercepted request and verify the response
    cy.wait('@formSubmit')
      .its('response')
      .then((response) => {
        expect(response.statusCode).to.equal(201)
        expect(response.body).to.have.property('message', 'Form submitted')
      })
  })
})

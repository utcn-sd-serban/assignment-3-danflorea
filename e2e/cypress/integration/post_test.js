describe("The Post list", function() {

    beforeEach(function () {
        cy.request("http://localhost:8080/test/reseed")
            .its("status").should("be.equal", 200);
    });

    it("Create post test", function() {
        cy.visit("/#/");

        cy.get('[data-cy="ask_question_button"]').click();
        cy.get('[data-cy="input_title"]').type("testpost");
        cy.get('[data-cy="input_text"]').type("blablablabla");
        cy.get('[data-cy="input_tags"]').type('testtag');
        cy.get('[data-cy="post_question_button"]').click();
        cy.wait(2000);

        cy.get('[data-cy="posts_list"]').should("have.length", 4);
    });

    it("Search by title test", function() {
        cy.visit("/#/");

        cy.get('[data-cy="ask_question_button"]').click();
        cy.get('[data-cy="input_title"]').type("testpost");
        cy.get('[data-cy="input_text"]').type("blablablabla");
        cy.get('[data-cy="input_tags"]').type('testtag');
        cy.get('[data-cy="post_question_button"]').click();
        cy.wait(1000);
        cy.get('[data-cy="search_input"]').type("testpost");
        cy.get('[data-cy="search_title_button"]').click();
        cy.wait(1000);

        cy.get('[data-cy="filtered_posts"]').should("have.length", 1);
    });

    it("Search by tag test", function() {
        cy.visit("/#/");

        cy.get('[data-cy="search_input"]').type("java");
        cy.get('[data-cy="search_tag_button"]').click();
        cy.wait(1000);

        cy.get('[data-cy="filtered_posts"]').should("have.length", 2);
    });

});
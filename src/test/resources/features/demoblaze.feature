@web
Feature: Web Testing for demoblaze

  Scenario: User logs in successfully
    Given User is on the Demoblaze homepage
    When User clicks on the login "Log in"
    And User enters valid "blackdice1" and "blackdice1"
    And User clicks "Login button"
    Then User should be logged in successfully

  Scenario: User adds a product to the cart successfully
    Given User is on the Demoblaze homepage
    When User navigates to a product page
    When User clicks on the product "Add to cart"
    Then The product should be added to the cart successfully

  Scenario: User verifies product in cart
    Given User is on the Demoblaze homepage
    When User navigates to a product page
    When User clicks on the product "Add to cart"
    When User navigates to the cart page
    Then The added product should be visible in the cart

  Scenario: User removes product from cart
    Given User is on the Demoblaze homepage
    When User navigates to a product page
    And User clicks on the product "Add to cart"
    And User navigates to the cart page
    Then The added product should be visible in the cart
    When User removes the product from the cart
    Then The cart should be empty

  Scenario: User attempts to remove a product from an empty cart
    Given User is on the Demoblaze homepage
    When User navigates to the cart page
    Then The cart should be empty
    When User removes the product from the cart
    Then An error message should be displayed indicating no products in the cart


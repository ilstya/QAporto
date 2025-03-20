Feature: Web Testing for saucedemo.com

  Scenario: Login with invalid credentials
    Given User opens SauceDemo login page
    When User enters username "invalid_user" and password "wrongpassword"
    And User clicks login button
    Then User should see an error message

  Scenario: Login with valid credentials
    Given User opens SauceDemo login page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks login button
    Then User should be redirected to the Products page

  Scenario: Add product to cart
    Given User opens SauceDemo login page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks login button
    Then User should be redirected to the Products page
    When User adds the first product to the cart
    Then The cart should display the added product

  Scenario: Add multiple products to cart
    Given User opens SauceDemo login page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks login button
    Then User should be redirected to the Products page
    When User adds the first product to the cart
    And User adds the second product to the cart




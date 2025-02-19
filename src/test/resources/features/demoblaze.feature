@web
Feature: Web Testing for demoblaze.com

  Scenario: Verify homepage title
    When I navigate to homepage
    Then The page title should be "STORE"

  Scenario: Add product to cart
    Given I am on the homepage
    When I click "Laptops" category
    And I select "MacBook Pro"
    And I add to cart
    Then Product should be added to cart

  Scenario: Remove product from cart
    Given I have a product in my cart
    When I remove the product
    Then The cart should be empty

  Scenario: Checkout process
    Given I have a product in my cart
    When I proceed to checkout
    And I enter my details
    And I confirm the purchase
    Then I should see the order confirmation message

  Scenario: Negative - Add product to cart without selecting
    Given I am on the homepage
    When I try to add to cart without selecting a product
    Then I should see an error message "Please select a product first"

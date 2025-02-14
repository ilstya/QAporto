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



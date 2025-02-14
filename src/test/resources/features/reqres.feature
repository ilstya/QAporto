@api
Feature: API Testing for reqres.in

  Scenario: Get user list
    Given I send GET request to "/api/users?page=2"
    When The response status code should be 200
    Then The response should contain "total_pages" field

  Scenario: create new user
    Given I send POST request to "/api/users" with:
    """
    {
      "name": "setya",
      "job": "QA"
    }
    """
    When The response status code should be 201
    Then The response should contain "createdAt" field

  Scenario: Update user information
    Given I send PUT request to "/api/users/2" with:
    """
    {
      "name": "neo",
      "job": "matrix"
    }
    """
    When The response status code should be 200
    Then The response should contain "updatedAt" field
    And The "job" field should be "matrix"

  Scenario: Delete user
    Given I send DELETE request to "/api/users/3"
    When The response status code should be 204
    Then The response body should be empty

  Scenario: Get non-existing user
    Given I send GET request to "/api/users/23"
    When The response status code should be 404
    Then The response body should be empty
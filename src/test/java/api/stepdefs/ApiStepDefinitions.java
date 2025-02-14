package api.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.Assertions.*;

public class ApiStepDefinitions {
    private Response response;
    private RequestSpecification request;

    @Given("I send GET request to {string}")
    public void i_send_get_request_to(String endpoint) {
        response = RestAssured.get("https://reqres.in" + endpoint);
    }

    @Given("I send POST request to {string} with:")
    public void i_send_post_request_to_with(String endpoint, String requestBody) {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody);
        response = request.post("https://reqres.in" + endpoint);
    }

    @Given("I send PUT request to {string} with:")
    public void i_send_put_request_to_with(String endpoint, String requestBody) {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody);
        response = request.put("https://reqres.in" + endpoint);
    }

    @Given("I send DELETE request to {string}")
    public void i_send_delete_request_to(String endpoint) {
        request = RestAssured.given();
        response = request.delete("https://reqres.in" + endpoint);
    }

    @When("The response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @Then("The response should contain {string} field")
    public void the_response_should_contain_field(String fieldName) {
        Object fieldValue = response.getBody().jsonPath().get(fieldName);
        assertThat(fieldValue).as("Field '" + fieldName + "' should not be null").isNotNull();
    }

    @Then("The response body should be empty")
    public void the_response_body_should_be_empty() {
        String responseBody = response.getBody().asString();
        assertThat(responseBody)
                .withFailMessage("Response body should be empty or {} but was: %s", responseBody)
                .matches(body -> body.isEmpty() || body.equals("{}"));
    }

    @And("The {string} field should be {string}")
    public void the_field_should_be(String fieldName, String expectedValue) {
        String actualValue = response.getBody().jsonPath().get(fieldName);
        assertThat(actualValue).isEqualTo(expectedValue);
    }
}


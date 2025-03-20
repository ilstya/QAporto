package web.stepdefs;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import web.pages.CartsPage;
import web.pages.CheckoutPage;
import web.Utility.DriverManager;
import web.pages.ProductsPage;


public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;

    public CheckoutSteps() {
        this.driver = DriverManager.getDriver();
        this.checkoutPage = new CheckoutPage(driver);
    }

    @And("User enters checkout details with first name {string}, last name {string}, and postal code {string}")
    public void user_enters_checkout_details(String firstName, String lastName, String postalCode) {
        checkoutPage.enterCheckoutDetails(firstName, lastName, postalCode);
    }

    @Then("The order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        Assertions.assertTrue(checkoutPage.isOrderSuccessful(), "Order should be successfully placed.");
    }
}

package DemoblazeWeb.stepdefs;

import DemoblazeWeb.Utility.Hooks;
import DemoblazeWeb.pages.CartPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutSteps {
    private WebDriver driver;
    private CartPage cartPage;

    public CheckoutSteps() {
        this.driver = Hooks.getDriver();
        this.cartPage = new CartPage(Hooks.getDriver());
    }

    @And("User confirms the order")
    public void user_confirms_the_order() {
        cartPage.confirmOrder();
    }

    @Then("User should see an order confirmation message")
    public void user_should_see_order_confirmation() {
        String confirmationMessage = cartPage.getOrderConfirmationMessage();
        assertThat(confirmationMessage).containsIgnoringCase("Thank you for your purchase!");
    }
}
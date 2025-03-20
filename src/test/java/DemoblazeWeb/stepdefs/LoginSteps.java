package DemoblazeWeb.stepdefs;

import DemoblazeWeb.pages.LoginPage;
import io.cucumber.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import DemoblazeWeb.Utility.Hooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @When("User clicks on the login {string}")
    public void user_clicks(String button) {
        System.out.println("Klik tombol: " + button);
        if (button.equalsIgnoreCase("Log in")) {
            loginPage.clickLogin();
        } else if (button.equalsIgnoreCase("Login button")) {
            loginPage.clickSubmit();
        }
    }


    @When("User enters valid {string} and {string}")
    public void user_enters_valid_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        System.out.println("Entered username: " + username);
        System.out.println("Entered password: " + password);
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));//15s karena sinyal sangat lag

    }

    @And("User clicks {string}")
    public void userClicks(String arg0) {
    }
}

package web.stepdefs;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import web.Utility.Hooks;
import web.pages.LoginPage;
import web.Utility.DriverManager;
import web.pages.ProductsPage;


public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    public LoginSteps() {
        this.driver = Hooks.getDriver(); // Pastikan Hooks.getDriver() mengembalikan driver yang benar
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver); // Inisialisasi productsPage
    }

    @Given("User opens SauceDemo login page")
    public void user_opens_saucedemo_login_page() {
        driver = DriverManager.getDriver();  // Pakai DriverManager
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("User clicks login button")
    public void user_clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("User should see an error message")
    public void user_should_see_an_error_message() {
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed.");
    }

    @Then("User should be redirected to the Products page")
    public void user_should_be_redirected_to_the_products_page() {
        Assertions.assertTrue(loginPage.isProductsPageDisplayed(), "User should be on the Products page.");

    }

    @When("User clicks the logout button")
    public void user_clicks_the_logout_button() {
        ProductsPage productsPage = new ProductsPage(Hooks.getDriver());
        productsPage.clickLogout();
    }

    @Then("User should be redirected to the login page")
    public void user_should_be_redirected_to_the_login_page() {
        Assertions.assertTrue(loginPage.isOnLoginPage(), "User should be redirected to login page.");
    }
}
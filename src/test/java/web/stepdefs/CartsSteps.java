package web.stepdefs;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.pages.CartsPage;
import web.pages.ProductsPage;
import web.Utility.DriverManager;
import static org.assertj.core.api.Assertions.assertThat;

public class CartsSteps {
    private WebDriver driver;
    private ProductsPage productsPage;
    private CartsPage cartsPage;

    public CartsSteps() {
        this.driver = DriverManager.getDriver();
        this.productsPage = new ProductsPage(driver);
        this.cartsPage = new CartsPage(driver);
    }

    @When("User adds the first product to the cart")
    public void user_adds_the_first_product_to_the_cart() {
        productsPage.addFirstProductToCart();
    }

    @Then("User should be redirected to the Cart page")
    public void user_should_be_redirected_to_the_cart_page() {
        String expectedUrl = "https://www.saucedemo.com/cart.html"; // URL halaman Cart
        String actualUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, actualUrl, "User is not on the Cart Page!");
    }

    @Then("The cart should display the added product")
    public void the_cart_should_display_the_added_product() {
        Assertions.assertTrue(productsPage.isCartBadgeDisplayed(), "Cart badge should be displayed.");
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartsPage.clickCheckout();
    }

    @When("User adds the second product to the cart")
    public void user_adds_the_second_product_to_the_cart() {
        productsPage.addSecondProductToCart();
    }

    @Then("The cart should display the added products")
    public void the_cart_should_display_the_added_products() {
        // Verifikasi bahwa jumlah produk di cart sesuai dengan jumlah yang ditambahkan
        int expectedProductCount = 3; // Sesuaikan jumlah produk yang ditambahkan
        int actualProductCount = cartsPage.getProductCountInCart();

        assertThat(actualProductCount)
                .as("Verifying the number of products in the cart")
                .isEqualTo(expectedProductCount);
    }
}

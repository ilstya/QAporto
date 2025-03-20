package DemoblazeWeb.stepdefs;

import DemoblazeWeb.Utility.Hooks;
import DemoblazeWeb.pages.CartPage;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import DemoblazeWeb.pages.HomePage;
import DemoblazeWeb.pages.ProductPage;


public class AddToCartSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    // konstruktor tanpa parameter
    public AddToCartSteps() {
        this.driver = Hooks.getDriver(); // Ambil driver dari Hooks.java bukan dari library lansung
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.homePage = new HomePage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
    }

    public boolean isProductInCart(String productName) {
        driver.findElement(By.id("cartur")).click(); // Klik tombol Cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> cartItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tbodyid tr td:nth-child(2)")));

        for (WebElement item : cartItems) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    @Given("User is on the Demoblaze homepage")
    public void user_is_on_homepage() {
        driver.get("https://www.demoblaze.com/");
    }

    @When("User navigates to a product page")
    public void user_navigates_to_product() {
        homePage.openProduct("Samsung galaxy s6");
    }

    @When("User clicks on the product {string}")
    public void user_clicks_add_to_cart(String button) {
        if (button.equalsIgnoreCase("Add to cart")) {
            productPage.addToCart();
        }
    }

    @Then("The product should be added to the cart successfully")
    public void product_added_successfully() {
        try {
            System.out.println("🔍 Menunggu alert muncul...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert ditemukan: " + alert.getText());
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println(" Alert tidak muncul! Coba cek cart langsung...");
            assertThat(isProductInCart("Samsung galaxy s6"))
                    .as("Produk tidak ditemukan di cart!")
                    .isTrue();
        }
    }

    // verifikasi produk

    @Given("User has added a product to the cart")
    public void user_has_added_product_to_cart() {
        productPage.addToCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept(); // Menutup alert
    }

    @When("User navigates to the cart page")
    public void user_navigates_to_cart_page() {
        productPage.goToCart();
    }

    @Then("The added product should be visible in the cart")
    public void product_should_be_visible_in_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cartProduct = driver.findElement(By.xpath("//td[contains(text(),'Samsung galaxy s6')]"));
        assertThat(cartProduct.isDisplayed()).isTrue();
    }

    @When("User removes the product from the cart")
    public void user_removes_the_product_from_cart() {
        cartPage.removeFromCart();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Refresh halaman
        driver.navigate().refresh();
    }

    @Then("The cart should be empty")
    public void the_cart_should_be_empty() {
        boolean isEmpty = cartPage.isCartEmpty(); // Gunakan return dari cartpage
        System.out.println("Apakah cart kosong? " + isEmpty);
        Assertions.assertTrue(isEmpty, "Cart is not empty!");
    }

    @Then("An error message should be displayed indicating no products in the cart")
    public void error_message_no_products_in_cart() {
        List<WebElement> cartRows = driver.findElements(By.xpath("//tbody[@id='tbodyid']//tr"));
    }
}

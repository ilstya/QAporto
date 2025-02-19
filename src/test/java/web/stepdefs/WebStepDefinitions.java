package web.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class WebStepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://www.demoblaze.com/";

    @Before
    public void setup() {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        // headless mode
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @When("I navigate to homepage")
    public void navigateToHomepage() {
        driver.get(BASE_URL);
    }

    @Then("The page title should be {string}")
    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Given("I am on the homepage")
    public void iAmOnHomepage() {
        driver.get(BASE_URL);
    }

    @When("I click {string} category")
    public void clickCategory(String category) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(category))).click();
    }

    @When("I select {string}")
    public void selectProduct(String product) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(product))).click();
    }

    @When("I add to cart")
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Then("Product should be added to cart")
    public void verifyProductAddedToCart() {
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success")));
        assertThat(driver.findElements(By.cssSelector(".success")).size()).isGreaterThan(0);
    }

    @Given("I have a product in my cart")
    public void iHaveAProductInMyCart() {
        iAmOnHomepage();
        clickCategory("Laptops");
        selectProduct("MacBook Pro");
        addToCart();
    }

    @When("I remove the product")
    public void iRemoveTheProduct() {
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Delete')]"))).click();

        // Tunggu produk benar-benar terhapus/jika jaringan lag atau load lama untuk terhapus
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tr"), 1));
    }

    @Then("The cart should be empty")
    public void theCartShouldBeEmpty() {
        List<WebElement> cartItems = driver.findElements(By.xpath("//tr"));
        System.out.println("Jumlah item di keranjang: " + cartItems.size());
        assertThat(cartItems).hasSize(1);
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Place Order')]"))).click();
    }

    @And("I enter my details")
    public void iEnterMyDetails() {
        driver.findElement(By.id("name")).sendKeys("ilham S");
        driver.findElement(By.id("country")).sendKeys("Indonesia");
        driver.findElement(By.id("city")).sendKeys("Yogyakarta");
        driver.findElement(By.id("card")).sendKeys("1234567812345678");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2025");
    }

    @And("I confirm the purchase")
    public void iConfirmThePurchase() {
        driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
    }

    @Then("I should see the order confirmation message")
    public void iShouldSeeTheOrderConfirmationMessage() {
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Thank you')]")));
        assertThat(confirmationMessage.isDisplayed()).isTrue();
    }

    @When("I try to add to cart without selecting a product")
    public void iTryToAddToCartWithoutSelectingAProduct() {
        driver.get(BASE_URL); // Pastikan di homepage
        List<WebElement> addToCartButtons = driver.findElements(By.linkText("Add to cart"));
        // Pastikan tombol tidak ditemukan (tidak muncul)
        assertThat(addToCartButtons).isEmpty();
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        System.out.println("Error: " + expectedMessage);
    }

}

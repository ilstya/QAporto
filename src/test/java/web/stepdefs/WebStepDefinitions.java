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
        // Uncomment the line below if you want to run tests in headless mode
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
}

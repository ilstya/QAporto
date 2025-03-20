package DemoblazeWeb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By productList = By.cssSelector(".card-title a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openProduct(String productName) {
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productList));
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                wait.until(ExpectedConditions.elementToBeClickable(product)).click();
                break;
            }
        }
    }
}


package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstProductAddToCart = By.xpath("(//button[contains(text(),'Add to cart')])[1]");
    private By secondProductAddToCart = By.xpath("(//button[contains(text(),'Add to cart')])[2]");
    private By cartBadge = By.className("shopping_cart_badge");

    public void addFirstProductToCart() {
        driver.findElement(firstProductAddToCart).click();
    }

    public void addSecondProductToCart() {
        driver.findElement(secondProductAddToCart).click();
    }

    public boolean isCartBadgeDisplayed() {
        return driver.findElement(cartBadge).isDisplayed();
    }

    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Tunggu hingga tombol menu burger terlihat
        By menuButton = By.id("react-burger-menu-btn");
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();

        // Tunggu hingga tombol logout terlihat dan klik
        By logoutButton = By.id("logout_sidebar_link");
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}

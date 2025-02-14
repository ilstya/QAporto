package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoblazePage {
    private WebDriver driver;

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "loginusername")
    private WebElement loginUsername;

    @FindBy(id = "loginpassword")
    private WebElement loginPassword;

    @FindBy(css = "button[onclick='logIn()']")
    private WebElement loginButton;

    @FindBy(id = "nameofuser")
    private WebElement nameOfUser;

    public DemoblazePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginLink() {
        loginLink.click();
    }

    public void enterLoginCredentials(String username, String password) {
        // Tunggu hingga elemen muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginUsername)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(loginPassword)).sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getLoggedInUsername() {
        // Tunggu hingga elemen "nameofuser" muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(nameOfUser)).getText();
    }
}
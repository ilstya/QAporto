package web.Utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir=/tmp/chrome-profile");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");

            driver = new ChromeDriver(options);
        }
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

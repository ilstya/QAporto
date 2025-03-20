package DemoblazeWeb.Utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Mode headless wajib di GitHub Actions
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

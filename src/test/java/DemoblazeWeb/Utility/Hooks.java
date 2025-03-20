package DemoblazeWeb.Utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Jalankan tanpa UI
            options.addArguments("--no-sandbox"); // Hindari permission issue di Linux
            options.addArguments("--disable-dev-shm-usage"); // Gunakan /tmp untuk shared memory
            options.addArguments("--disable-gpu"); // Matikan akselerasi GPU
            options.addArguments("--remote-allow-origins=*"); // Pastikan akses remote
            options.addArguments("--user-data-dir=/tmp/chrome-user-data"); // Direktori unik untuk Chrome
            options.addArguments("--remote-debugging-port=9222"); // Debugging

            driver = new ChromeDriver(options);
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

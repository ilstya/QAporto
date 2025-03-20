package DemoblazeWeb.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Lokator elemen
    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By orderModal = By.id("orderModal");
    private By purchaseButton = By.xpath("//button[text()='Purchase']");
    private By confirmationMessage = By.cssSelector(".sweet-alert h2");
    private By cartTable = By.id("tbodyid"); // Lokasi tabel cart
    private By deleteButton = By.xpath("//a[text()='Delete']"); // Tombol hapus produk

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void confirmOrder() {
        WebElement purchaseBtn = wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        purchaseBtn.click();
    }

    public void removeFromCart() {
        try {
            WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
            deleteBtn.click();

            // Tunggu elemen menghilang sebelum lanjut
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[contains(text(),'Samsung galaxy s6')]")));
            System.out.println(" Produk berhasil dihapus dari cart.");
        } catch (TimeoutException e) {
            System.out.println(" ERROR: Produk masih ada di cart setelah penghapusan!");
        }
    }

    public boolean isCartEmpty() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTable));

        List<WebElement> cartRows = driver.findElements(By.xpath("//tbody[@id='tbodyid']//tr"));
        System.out.println("Jumlah item di cart: " + cartRows.size());

        return cartRows.isEmpty();
    }

    public String getOrderConfirmationMessage() {
        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        return confirmationMsg.getText();
    }
}

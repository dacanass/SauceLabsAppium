package pages;

import driver.DriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Gestures;

import java.time.Duration;

public class BasePage {
    protected AndroidDriver driver;
    protected Gestures gestures;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        // La página le pide el driver al proveedor para poder trabajar
        this.driver = DriverProvider.getDriver();
        this.gestures = new Gestures(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Aquí defines métodos genéricos como esperar elemento, hacer scroll, etc.
}

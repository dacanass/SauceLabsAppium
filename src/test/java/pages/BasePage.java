package pages;

import driver.DriverProvider;
import io.appium.java_client.android.AndroidDriver;
import utilities.Gestures;

public class BasePage {
    protected AndroidDriver driver;
    protected Gestures gestures;

    public BasePage(AndroidDriver driver) {
        // La página le pide el driver al proveedor para poder trabajar
        this.driver = DriverProvider.getDriver();
        this.gestures = new Gestures(driver);
    }

    // Aquí defines métodos genéricos como esperar elemento, hacer scroll, etc.
}

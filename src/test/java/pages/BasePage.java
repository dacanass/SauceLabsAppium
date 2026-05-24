package pages;

import driver.DriverProvider;
import io.appium.java_client.android.AndroidDriver;
import utilities.GesturesUtils;

public class BasePage {
    protected AndroidDriver driver;
    protected GesturesUtils gestures;

    public BasePage(AndroidDriver driver) {
        // La página le pide el driver al proveedor para poder trabajar
        this.driver = DriverProvider.getDriver();
        this.gestures = new GesturesUtils(driver);
    }

    // Aquí defines métodos genéricos como esperar elemento, hacer scroll, etc.
}

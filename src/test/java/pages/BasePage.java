package pages;

import driver.DriverProvider;
import io.appium.java_client.android.AndroidDriver;

public class BasePage {
    protected AndroidDriver driver;

    public BasePage() {
        // La página le pide el driver al proveedor para poder trabajar
        this.driver = DriverProvider.getDriver();
    }

    // Aquí defines métodos genéricos como esperar elemento, hacer scroll, etc.
}

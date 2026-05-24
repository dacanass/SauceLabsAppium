package tests;

import driver.DriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import utilities.TestListener;

@ExtendWith(TestListener.class) // <--- Aquí conectamos el Listener
public class BaseTest {
    // Definimos el driver aquí. Sin el objeto driver en la BaseTest, cada vez tendríamos que escribir
    //DriverProvider.getDriver().findElement(By.id("login")).click(); para poder interactuar
    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() {
        // Inicializar el driver. DriverProvider se encarga de toda la configuración pesada
        this.driver = DriverProvider.getDriver();
    }

    @AfterEach
    public void tearDown() {
        // Cerrar driver. Cerramos la sesión a través del proveedor
        DriverProvider.quitDriver();
    }
}

package com.saucelabs.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        // 1. Construir la ruta relativa dinámicamente
        String appPath = System.getProperty("user.dir") + "/src/test/resources/apps/mda-2.2.0-25.apk";

        // 1. Configurar las capacidades del dispositivo
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Pixel_9") // El nombre que ya probamos en consola
                .setAutomationName("UiAutomator2")
                .setApp(appPath) // Asegúrate de que la ruta sea correcta
                .setNoReset(false); // Reinicia la app en cada test para tener un estado limpio

        // 2. Inicializar el driver conectando al servidor de Appium
        // Por defecto Appium corre en http://127.0.0.1:4723
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        // 3. Configurar una espera implícita para que no falle si el elemento tarda en aparecer
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        // 4. Cerrar la sesión y la app al finalizar cada test
        if (driver != null) {
            driver.quit();
        }
    }
}

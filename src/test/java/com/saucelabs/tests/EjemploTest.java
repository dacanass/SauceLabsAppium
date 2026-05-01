package com.saucelabs.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Heredamos de BaseTest para que use el @BeforeEach (abrir app)
// y el @AfterEach (cerrar app)
public class EjemploTest extends BaseTest {

    @Test
    @DisplayName("Prueba de humo: Verificar conexión con el Pixel 9")
    public void testVerificarConexion() {
        // Si el código llega aquí, significa que el setUp() en BaseTest funcionó
        System.out.println(">>> ¡Conexión exitosa!");
        System.out.println(">>> Appium logró instalar y abrir la APK en el Pixel 9.");

        // Verificamos que el objeto driver no sea nulo
        assertNotNull(driver, "El driver de Appium no se inició correctamente.");

        // Imprimimos el nombre del paquete actual para confirmar que estamos en la app correcta
        String appPackage = (String) driver.getCapabilities().getCapability("appPackage");
        System.out.println(">>> Estamos dentro de la aplicación: " + appPackage);
    }
}
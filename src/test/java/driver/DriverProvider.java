package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.net.MalformedURLException;

public class DriverProvider {
    // 1. Creamos el contenedor ThreadLocal para aislar el driver por cada hilo
    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        // 2. Revisamos si el hilo actual ya tiene un driver asignado
        if (driverThreadLocal.get() == null) {
            try {
                UiAutomator2Options options = new UiAutomator2Options()
                        .setPlatformName("Android")
                        .setAutomationName("UiAutomator2")
                        .setDeviceName("Pixel_9")
                        .setApp(System.getProperty("user.dir") + "/src/test/resources/apps/mda-2.2.0-25.apk");
                // 3. Guardamos la nueva instancia directamente en el ThreadLocal
                driverThreadLocal.set(new AndroidDriver(new URL("http://127.0.0.1:4723"), options));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error al configurar la URL del servidor Appium", e);
            }
        }
        // 4. Retornamos el driver que le pertenece exclusivamente al hilo que lo pide
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        // 5. Obtenemos el driver del hilo actual para cerrarlo
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            // 6. ¡MUY IMPORTANTE!: Limpiamos el ThreadLocal para evitar fugas de memoria
            driverThreadLocal.remove();
        }
    }
}

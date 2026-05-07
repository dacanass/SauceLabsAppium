package utilities;

import driver.DriverProvider;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;

public class TestListener implements TestWatcher {

    // Se ejecuta automáticamente solo cuando el test falla
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test fallido: capturando evidencia visual...");
        saveScreenshot();
    }

    // La anotación @Attachment le dice a Allure que guarde el retorno de este método
    @Attachment(value = "Screenshot en el momento del fallo", type = "image/png")
    public byte[] saveScreenshot() {
        // Obtenemos el driver desde tu DriverProvider con ThreadLocal
        return DriverProvider.getDriver().getScreenshotAs(OutputType.BYTES);
    }
}

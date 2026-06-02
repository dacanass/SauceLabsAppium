package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CataloguePage extends BasePage{
   private final By productTitle = AppiumBy.accessibilityId("title");

    public CataloguePage(AndroidDriver driver){
        super(driver);
    }

    public boolean isTitleDisplayed() {
        try {
            // Esperamos de forma segura hasta 10 segundos a que el elemento aparezca
            var titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
            return titleElement.isDisplayed();
        } catch (Exception e) {
            // Si pasa el tiempo y no aparece (ej. credenciales incorrectas), devolvemos false
            return false;
        }
    }
}

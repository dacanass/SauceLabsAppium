package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    //selectores
    private final By menuButton = AppiumBy.accessibilityId("View menu");
    private final By menuLoginOption = AppiumBy.accessibilityId("Login Menu Item");
    private final By usernameField = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    private final By passwordField = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private final By loginButton = AppiumBy.accessibilityId("Tap to login with given credentials");
    private final By locketOutError = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordErrorTV");
    private final By emptyUsernameError = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameErrorTV");
    private final By emptyPasswordError = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordErrorTV");

    //constructor
    public LoginPage(AndroidDriver driver){
        super(driver);
    }

    //Métodos
    //flujo: Selector Privado ➡️ Método de Página que expone el estado como boolean o String ➡️ Aserción en el Test

    public void loginSuccess(String usuario, String password){
        waitForDisplayed(usernameField).sendKeys(usuario);
        waitForDisplayed(passwordField).sendKeys(password);
        gestures.tap(waitForClickable(loginButton));
    }

    public boolean isLocketOutErrorDisplayed(){
        try {
            // Esperamos de forma segura hasta 10 segundos a que el elemento aparezca
            return waitForDisplayed(locketOutError).isDisplayed();

        } catch (Exception e) {
            // Si pasa el tiempo y no aparece (ej. credenciales incorrectas), devolvemos false
            return false;
        }
    }

    public boolean isUsernameRequiredErrorDisplayed(){
        try {
            return waitForDisplayed(emptyUsernameError).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public boolean isPasswordRequiredErrorDisplayed(){
        try{
            return waitForDisplayed(emptyPasswordError).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}

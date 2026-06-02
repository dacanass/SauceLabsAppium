package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    private final By menuButton = AppiumBy.accessibilityId("View menu");
    private final By menuLoginOption = AppiumBy.accessibilityId("Login Menu Item");
    private final By usernameField = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    private final By passwordField = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private final By loginButton = AppiumBy.accessibilityId("Tap to login with given credentials");

    public LoginPage(AndroidDriver driver){
        super(driver);
    }

    public void openLoginPage(){
//        gestures.tap(driver.findElement(menuButton));
//        gestures.tap(driver.findElement(menuLoginOption));

        var menu = wait.until(ExpectedConditions.visibilityOfElementLocated(menuButton));
        gestures.tap(menu);
        var opcionLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(menuLoginOption));
        gestures.tap(opcionLogin);

    }

    public void loginSucces(String usuario, String password){
        driver.findElement(usernameField).sendKeys(usuario);
        driver.findElement(passwordField).sendKeys(password);
        gestures.tap(driver.findElement(loginButton));
    }

}

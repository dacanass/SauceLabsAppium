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

    //constructor
    public LoginPage(AndroidDriver driver){
        super(driver);
    }

    //Métodos
    public void openLoginPage(){
        gestures.tap(waitForDisplayed(menuButton));
        gestures.tap(waitForDisplayed(menuLoginOption));
    }

    public void loginSuccess(String usuario, String password){
        waitForDisplayed(usernameField).sendKeys(usuario);
        waitForDisplayed(passwordField).sendKeys(password);
        gestures.tap(waitForClickable(loginButton));
    }

}

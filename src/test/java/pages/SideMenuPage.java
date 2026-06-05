package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SideMenuPage extends BasePage{
    // 1. Selector del disparador universal (El botón de las 3 líneas / Hamburguesa)
    private final By menuButton = AppiumBy.accessibilityId("View menu");

    // 2. Selectores de las opciones internas del menú lateral
    private final By catalogOption  = AppiumBy.androidUIAutomator("new UiSelector().text('Catalog')");
    private final By webviewOption  = AppiumBy.androidUIAutomator("new UiSelector().text('WebView')");
    private final By drawingOption  = AppiumBy.androidUIAutomator("new UiSelector().text('Drawing')");
    private final By aboutOption    = AppiumBy.androidUIAutomator("new UiSelector().text('About')");
    private final By resetOption    = AppiumBy.androidUIAutomator("new UiSelector().text('Reset App State')");
    private final By loginOption = AppiumBy.accessibilityId("Login Menu Item");
    private final By logoutOption   = AppiumBy.accessibilityId("Logout Menu Item");

    public SideMenuPage(AndroidDriver driver){super(driver);}

    public void openMenu(){
        gestures.tap(waitForDisplayed(menuButton));
    }
    public void goToLogin(){
        openMenu();
        gestures.tap(waitForDisplayed(loginOption));
    }
    public void clickLogout(){
        openMenu();
        gestures.tap(waitForDisplayed(logoutOption));
    }
    public void goToCatalog(){
        openMenu();
        gestures.tap(waitForDisplayed(catalogOption));
    }
    public void goToWebView(){
        openMenu();
        gestures.tap(waitForDisplayed(webviewOption));
    }
    public void goToDrawing(){
        openMenu();
        gestures.tap(waitForDisplayed(drawingOption));
    }
    public void goToAbout(){
        openMenu();
        gestures.tap(waitForDisplayed(aboutOption));
    }
    public void goToreset(){
        openMenu();
        gestures.tap(waitForDisplayed(resetOption));
    }

}

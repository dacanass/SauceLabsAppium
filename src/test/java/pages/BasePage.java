package pages;

import driver.DriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Gestures;

import java.time.Duration;

public class BasePage {
    protected AndroidDriver driver;
    protected Gestures gestures;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        // La página le pide el driver al proveedor para poder trabajar
        this.driver = driver;
        this.gestures = new Gestures(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Aquí defines métodos genéricos como esperar elemento, etc.
    public void pressBack(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    protected WebElement waitForDisplayed(By locator, int time){
        if (time == 10) {
            return waitForDisplayed(locator);
        }
        // Solo creamos uno nuevo si explícitamente se pide un tiempo atípico
        final var customWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected WebElement waitForDisplayed(By locator) {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator, int time) {
        if (time == 10) return waitForClickable(locator);
        final var customWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected WebElement waitForClickable(By locator) {
        return this.wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean waitForInvisibility(By locator) {
        return this.wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForInvisibility(By locator, int time) {
        if (time == 10) return waitForInvisibility(locator);
        final var customWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForTextToBePresent(By locator, String text) {
        return this.wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected boolean waitForTextToBePresent(By locator, String text, int time) {
        if (time == 10) return waitForTextToBePresent(locator, text);
        final var customWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return customWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Espera a que el elemento exista en la estructura de la app, sin importar si es visible o no (Tiempo estándar 10s).
     */
    protected WebElement waitForPresence(By locator) {
        return this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}

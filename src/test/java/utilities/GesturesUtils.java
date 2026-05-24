package utilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class GesturesUtils {
    private final AndroidDriver driver;
    private final PointerInput finger;

    // El constructor recibe el driver activo de las pruebas
    public  GesturesUtils(AndroidDriver driver){
        this.driver = driver;
        this.finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    }

    /**
     * Gesto de deslizamiento universal basado en porcentajes de la pantalla.
     *
     * @param startXPrc Porcentaje inicial en el eje X (ej. 0.5 para el centro)
     * @param startYPrc Porcentaje inicial en el eje Y (ej. 0.8 para abajo)
     * @param endXPrc   Porcentaje final en el eje X (ej. 0.5 para mantener la línea)
     * @param endYPrc   Porcentaje final en el eje Y (ej. 0.2 para arriba)
     */
    public void swipe(double startXPrc, double startYPrc, double endXPrc, double endYPrc) {
        // 1. Obtenemos las dimensiones de la pantalla actual de forma 100% dinámica
        Dimension size = driver.manage().window().getSize();

        // 2. Calculamos los píxeles exactos multiplicando el tamaño por el porcentaje recibido
        int startX = (int) (size.width * startXPrc);
        int startY = (int) (size.height * startYPrc);
        int endX = (int) (size.width * endXPrc);
        int endY = (int) (size.height * endYPrc);

        // 3. Configuramos la secuencia W3C con los puntos calculados
        Sequence swipeSequence = new Sequence(finger, 1);

        swipeSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Usamos una duración controlada para que el sistema operativo registre el arrastre
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipeSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipeSequence));

        /*
        * Ejemplo de uso: Scroll hacia abajo estándar(80% al 20%)
        * Se mueve el dedo desde abajo (desde 0.8) hacia arriba (hasta 0.2) en el centro de la pantalla.
        * Eje X se mantiene al centro (0.5). Y se mueve de 0.8 a 0.2
        * gestures.swipe(0.5, 0.8, 0.5, 0.2);
        *
        * Ejemplo de uso: swipe horizontal:
        * Se mueve el dedo de derecha a izquierda en la mitad vertical de la pantalla
        * Eje Y se mantiene fijo a la mitad (0.5). X se mueve del 90% al 10%
        * gestures.swipe(0.9, 0.5, 0.1, 0.5);
        * */
    }

    //Metodos de conveniencia
    public void scrollDownEstandar() {
        this.swipe(0.5, 0.8, 0.5, 0.2);
    }

    public void scrollDownShort() {
        this.swipe(0.5, 0.6, 0.5, 0.4);
    }

    public void pasarCarruselDerecha() {
        this.swipe(0.9, 0.5, 0.1, 0.5);
    }

    /**
     * Realiza un toque prolongado (Long Press) sobre coordenadas específicas.
     */
    public void longPress(int x, int y) {
        // 4. Reutilizamos el mismo 'finger' global aquí también
        Sequence longPressSequence = new Sequence(finger, 1);

        longPressSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        longPressSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPressSequence.addAction(finger.createPointerMove(Duration.ofMillis(1500), PointerInput.Origin.viewport(), x, y));
        longPressSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(longPressSequence));
    }
}

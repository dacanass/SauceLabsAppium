package utilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class Gestures {
    private final AndroidDriver driver;
    private final PointerInput finger1;
    private final PointerInput finger2;

    // El constructor recibe el driver activo de las pruebas
    public Gestures(AndroidDriver driver){
        this.driver = driver;
        this.finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        this.finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
    }

    public static Point getCenterPoint(WebElement element){
        final var rect = element.getRect();
        final var centroX = rect.getX() + (rect.getWidth() / 2);
        final var centroY = rect.getY() + (rect.getHeight() / 2);
        return new Point(centroX,centroY);
    }

    /**
     * Realiza un toque rápido (Tap) en coordenadas específicas.
     * Útil para elementos no mapeados, Canvas, o cuando .click() falla.
     */
    public void tap(int x, int y) {
        Sequence tapSequence = new Sequence(finger1, 1);

        // 1. Mover al punto en 0 milisegundos
        tapSequence.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        // 2. Presionar
        tapSequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // 3. Una mini pausa para que el sistema operativo registre el toque físico (importante)
        tapSequence.addAction(new Pause(finger1, Duration.ofMillis(50)));
        // 4. Levantar el dedo
        tapSequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tapSequence));
    }

    public void tap(WebElement element) {
        final var centerPoint = getCenterPoint(element);

        this.tap(centerPoint.getX(), centerPoint.getY());
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
        Sequence swipeSequence = new Sequence(finger1, 1);

        swipeSequence.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Usamos una duración controlada para que el sistema operativo registre el arrastre
        swipeSequence.addAction(finger1.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipeSequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Empaquetado en una lista (en este caso singletonList) y ejecucion de la sequence mediante .perform()
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
        Sequence longPressSequence = new Sequence(finger1, 1);

        longPressSequence.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        longPressSequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPressSequence.addAction(finger1.createPointerMove(Duration.ofMillis(1500), PointerInput.Origin.viewport(), x, y));
        longPressSequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(longPressSequence));
    }

    /**
     * Gesto de Zoom universal (Multi-touch) basado en porcentajes de la pantalla.
     *
     * @param startDistancePrc Distancia inicial entre los dedos (porcentaje del alto/ancho).
     * @param endDistancePrc   Distancia final entre los dedos al terminar el gesto.
     */
    public void zoom(double startDistancePrc, double endDistancePrc) {
        Dimension size = driver.manage().window().getSize();

        // Encontramos el centro de la pantalla, que será nuestro eje de rotación/zoom
        int centerX = size.width / 2;
        int centerY = size.height / 2;

        // Determinamos los puntos de inicio y fin para el Dedo 1 y Dedo 2 en base a los porcentajes
        int startYF1 = (int) (centerY - (size.height * (startDistancePrc / 2)));
        int endYF1 = (int) (centerY - (size.height * (endDistancePrc / 2)));

        int startYF2 = (int) (centerY + (size.height * (startDistancePrc / 2)));
        int endYF2 = (int) (centerY + (size.height * (endDistancePrc / 2)));

        // 3. Creamos las dos secuencias independientes (una para cada dedo)
        Sequence sequenceF1 = new Sequence(finger1, 1);
        Sequence sequenceF2 = new Sequence(finger2, 2);

        // CONFIGURACIÓN DEDO 1 (Se mueve en la mitad superior)
        sequenceF1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startYF1));
        sequenceF1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequenceF1.addAction(finger1.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), centerX, endYF1));
        sequenceF1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // CONFIGURACIÓN DEDO 2 (Se mueve en la mitad inferior de forma simétrica u opuesta)
        sequenceF2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startYF2));
        sequenceF2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequenceF2.addAction(finger2.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), centerX, endYF2));
        sequenceF2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // 4. ¡La magia de la W3C!: Enviamos AMBAS secuencias en una lista para ejecutarse EN SIMULTÁNEO
        driver.perform(Arrays.asList(sequenceF1, sequenceF2));
    }

    /**
     * Zoom In (Agrandar / Alejar dedos):
     * Los dedos empiezan muy juntos (10% de distancia) y se separan (80% de distancia).
     */
    public void zoomIn() {
        this.zoom(0.10, 0.80);
    }

    /**
     * Zoom Out (Achicar / Pellizcar pantalla):
     * Los dedos empiezan muy separados (80% de distancia) y se juntan (10% de distancia).
     */
    public void zoomOut() {
        this.zoom(0.80, 0.10);
    }

}

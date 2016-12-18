/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.12.2016 
 * Aufgabe: Praktikum 4
 */

package aufgabenblatt04.view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import aufgabenblatt04.braitenbergvehikel.BVBewegungAttraktion;
import aufgabenblatt04.braitenbergvehikel.BVSimulation;
import aufgabenblatt04.braitenbergvehikel.BraitenbergVehikel;
import aufgabenblatt04.braitenbergvehikel.Vektor2;

/**
 * Zeichenfläche für eine Braitenberg-Vehikle-Simulation.<BR>
 * <BR>
 * Changes in v2.0: <BR>
 * Improved drawing (with name and movement method)<BR>
 * Removed zeichneGedrehtesBild() because it's no longer needed
 * 
 * @author Philipp Jenke, Moritz Höwer, Philip Scheer
 * @version 2.0 - 18.12.2016
 */
public class BVCanvas extends Canvas implements Observer {

    /**
     * Bild eines Vehikels. Achtung: Package mit dem Bild muss korrekt angegeben
     * werden.
     */
    private Image bvImage = new Image(
            "aufgabenblatt04/assets/braitenberg_vehikel.png");
    
    /**
     * Image to show attraction movement mode
     */
    private Image attractionImage = new Image(
            "aufgabenblatt04/assets/icon_attraktion.png");
    
    /**
     * Image to show deflection movement mode
     */
    private Image deflectionImage = new Image(
            "aufgabenblatt04/assets/icon_abstossung.png");
    
    /**
     * Image to show "up close" - whatever that means...
     */
    private Image upCloseImage = new Image(
            "aufgabenblatt04/assets/icon_im_abstand.png");

    /**
     * Referenz auf die Simulation.
     */
    private final BVSimulation sim;

    public BVCanvas(int breite, int hoehe, BVSimulation sim) {
        super(breite, hoehe);
        this.sim = sim;
        sim.addObserver(this);

        ChangeListener<Number> cL = (a, b, c) -> zeichneSimulation();
        widthProperty().addListener(cL);
        heightProperty().addListener(cL);

        setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent e) {
        sim.setSignal(bild2Welt(e.getX(), e.getY()));
    }

    /**
     * Rechnet von Bild- und Welt-Koordinaten um.
     */
    public Vektor2 bild2Welt(double x, double y) {
        double xWorld = x - getWidth() / 2;
        double yWorld = getHeight() / 2 - y;
        return new Vektor2(xWorld, yWorld);
    }

    /**
     * Zeichnet die gesamte Simulation neu.
     */
    public void zeichneSimulation() {
        GraphicsContext gc = getGraphicsContext2D();
        // Alles löschen
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
        // Vehikel zeichnen
        for (int i = 0; i < sim.getAnzahlVehike(); i++) {
            zeichneVehikel(gc, sim.getVehikel(i));
        }
        // Signal zeichnen
        zeichneSignal(gc, sim.getSignal());
    }

    /**
     * Rechnet von Welt- und Bild-Koordinaten um.
     */
    public Point welt2BildKoordinaten(Vektor2 position) {
        return new Point((int) (position.x() + getWidth() / 2),
                (int) (getHeight() / 2 - position.y()));
    }

    /**
     * Rotiert den aktuellen Grafik-Kontext (zum Zeichnen eines rotierten
     * Bildes).
     */
    private void rotieren(GraphicsContext gc, double winkel, double px,
            double py) {
        Rotate r = new Rotate(winkel, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(),
                r.getTx(), r.getTy());
    }

    /**
     * Zeichnet ein Braitenberg-Vehikel.
     */
    protected void zeichneVehikel(GraphicsContext gc, BraitenbergVehikel bv) {
        Point p = welt2BildKoordinaten(bv.getPosition());
        double winkelInGrad = bv.getRotationGradImUhrzeigersinn();

        gc.save();
        rotieren(gc, winkelInGrad, p.x, p.y);
        // Vehikel Image
        gc.drawImage(bvImage, p.x - bv.getSeitenlaenge() / 2,
                p.y - bv.getSeitenlaenge() / 2);
        
        // Vehikel name
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.TOP);
        gc.setFill(Color.BLACK);
        gc.fillText(bv.getName(), p.x, p.y + bv.getSeitenlaenge() / 2);
        
        // select mode image
        Image img;
        if(bv.getBewegung() instanceof BVBewegungAttraktion){
            img = attractionImage;
        } else {
            img = deflectionImage;
        }
        // draw mode image
        gc.drawImage(img, p.x + bv.getSeitenlaenge() / 2,
                p.y - bv.getSeitenlaenge() / 2);
        gc.restore();
    }

    /**
     * Zeichnet das Signal.
     */
    private void zeichneSignal(GraphicsContext gc, Vektor2 signal) {
        int breite = 10;
        int offset = 2;
        Point p = welt2BildKoordinaten(signal);
        gc.setFill(Color.BLACK);
        gc.fillOval(p.x - breite / 2 - offset, p.y - breite / 2 - offset,
                breite + offset * 2, breite + offset * 2);
        gc.setFill(Color.YELLOW);
        gc.fillOval(p.x - breite / 2, p.y - breite / 2, breite, breite);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Zeichenroutine wird im JavaFX-Thread aufgerufen.
        Platform.runLater(() -> zeichneSimulation());
    }
}

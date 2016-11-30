/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.11.2016 
 * Aufgabe: Praktikum 3
 */

package aufgabenblatt03;

import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * Visualization of a Track and or Train and or Waiting counter (part of View)
 * 
 * TODO: Potentially split this up into at least two subclasses?
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 28.11.2016
 */
public class TrackVisualisation extends Canvas {

    /*
     * constans for drawing
     */
    private static final double TRAIN_PERCENTAGE_X = 0.8;
    private static final double TRAIN_PERCENTAGE_Y = 0.85;

    /*
     * constants for behaviour
     */
    public static final int NO_DRIVER = -1;
    public static final int NO_TRACK_NUMBER = -1;

    /**
     * AnimationTimer is used for updating (redrawing trains)
     */
    private AnimationTimer timer;

    /*
     * fields for behaviour
     */
    private int number;
    private int waitForExitCounter;
    private int driverID;

    public TrackVisualisation(int number) {
        this.number = number;
        driverID = NO_DRIVER;
        waitForExitCounter = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        };
        timer.start();
    }

    public void setDriverID(int newId) {
        driverID = newId;
    }
    
    public int getDriverID() {
        return driverID;
    }

    /**
     * increments the counter that displays how many drivers are waiting to
     * depart a train from this platform
     */
    public void incrementExitCounter() {
        waitForExitCounter++;
    }

    /**
     * removes the train and decrements the waiting counter
     */
    public void trainLeft() {
        driverID = NO_DRIVER;
        waitForExitCounter--;
        if (waitForExitCounter < 0) {
            waitForExitCounter = 0;
        }
    }

    /**
     * draws the track, train and waiting counter - depending on their values
     */
    private void draw() {
        GraphicsContext g = this.getGraphicsContext2D();

        double width = getWidth();
        double height = getHeight();

        // clear
        g.setFill(Color.BLACK);
        g.clearRect(0, 0, width, height);

        if (number != NO_TRACK_NUMBER) { // draw track (+ number)
            g.fillRect(0, height / 2 - 2, width, 4);

            g.setTextAlign(TextAlignment.LEFT);
            g.setTextBaseline(VPos.BOTTOM);
            g.fillText(number + "", 2, height / 2);
        }
        if (waitForExitCounter > 0) { // draw exit counter
            g.setTextAlign(TextAlignment.RIGHT);
            g.setTextBaseline(VPos.TOP);
            g.fillText("<< (" + waitForExitCounter + ")", width - 2, 0);
        }
        if (driverID != NO_DRIVER) { // draw train
            double marginX = (1 - TRAIN_PERCENTAGE_X) * width;
            double marginY = (1 - TRAIN_PERCENTAGE_Y) * height;
            g.fillRoundRect(marginX / 2, marginY / 2, width - marginX,
                    height - marginY, 10, 10);
            g.setFill(Color.WHITE);
            g.fillRoundRect(marginX / 2 + 5, marginY / 2 + 5,
                    width - marginX - 10, height - marginY - 10, 10, 10);
            g.setFill(Color.BLACK);

            g.setTextAlign(TextAlignment.CENTER);
            g.setTextBaseline(VPos.CENTER);
            g.fillText("Driver #" + driverID, width / 2, height / 2);
        }
    }
}

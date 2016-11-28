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
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 28.11.2016
 */
public class TrackVisualisation extends Canvas {

    public static final int NO_DRIVER = -1;

    AnimationTimer timer;

    private int number;
    private boolean drawTrack;
    private int driverID;

    public TrackVisualisation(int number, boolean drawTrack) {
        this.number = number;
        this.drawTrack = drawTrack;
        driverID = NO_DRIVER;        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        };
        timer.start();
    }

    public void setDriverID(int newId){
        driverID = newId;
    }
    
    private void draw() {
        GraphicsContext g = this.getGraphicsContext2D();

        double width = getWidth();
        double height = getHeight();
        
        g.setFill(Color.BLACK);
        g.clearRect(0, 0, width, height);
        
        if(drawTrack){
            g.fillRect(0, height / 2 - 2, width, 4);
            g.fillText(number + "", 5, height / 2 - 8);
        }
        if(driverID != -1){
            g.fillRoundRect(30, 10, width - 60, height - 20, 10, 10);
            g.setFill(Color.WHITE);
            g.fillRoundRect(35, 15, width - 70, height - 30, 10, 10);
            g.setFill(Color.BLACK);
            
            g.setTextAlign(TextAlignment.CENTER);
            g.setTextBaseline(VPos.CENTER);
            
            g.fillText("Driver #" + driverID, width / 2, height / 2);
        }
    }
}

/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 27.11.2016 
 * Aufgabe: Praktikum 3
 */

package aufgabenblatt03;

import javafx.stage.Stage;

/**
 * Application for Aufgabe 3
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 27.11.2016
 */
public class Application extends javafx.application.Application {

    /**
     * entry point
     * 
     * @param args
     *            command line args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new GUI(primaryStage);
    }

}

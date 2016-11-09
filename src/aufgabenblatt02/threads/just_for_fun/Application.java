/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */

package aufgabenblatt02.threads.just_for_fun;

/**
 *
 *
 * @author Moritz Höwer
 * @version 1.0 - 09.11.2016
 */
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Airport eddh = new Airport("Hamburg", new Runway("RWY 33"), new Runway("RWY 15"));
        
        eddh.simulate();
    }

}

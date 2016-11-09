/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */
package aufgabenblatt02.threads;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Airport a = new Airport("HAM");
		a.run();
	}

}

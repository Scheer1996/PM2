/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 20.10.2016
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabenblatt02.lambdas;

@FunctionalInterface
public interface DoubleDoubleToDouble {

	public double calculate(double d1, double d2);
}

/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */
package aufgabenblatt02.lambdas;

/**
 * Functional Interface to demonstrate the use of Lambdas
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 09.11.2016
 */
@FunctionalInterface
public interface DoubleDoubleToDouble {
    
	/**
	 * Combines the two double values into a resulting double
	 * 
	 * @param d1 the first argument
	 * @param d2 the second argument
	 * @return the result of combining d1 and d2
	 */
	public double evaluate(double d1, double d2);
}

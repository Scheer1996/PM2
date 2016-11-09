/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 20.10.2016
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabenblatt02.lambdas;

public class Calculator {

	public enum BasicOperator {
		PLUS , MINUS, DIVIDE, MULTIPLY
	}
	
	private DoubleDoubleToDouble addition = (d1, d2) -> { return d1 + d2;};

	private DoubleDoubleToDouble subtraction = (d1, d2) -> { return d1 - d2;};

	private DoubleDoubleToDouble divison = (d1, d2) -> { return d1 / d2;};

	private DoubleDoubleToDouble multiplikation = (d1, d2) -> { return d1 * d2;};
	

	
	public double calculate(BasicOperator op, double d1, double d2) {
		
		
		return addition.calculate(d1, d2);
	}
}

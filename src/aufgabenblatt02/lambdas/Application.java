/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 20.10.2016
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabenblatt02.lambdas;

import aufgabenblatt02.lambdas.Calculator.BasicOperator;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calculator c = new Calculator();
		
		double val = c.calculate(BasicOperator.PLUS, 12, 11);
		
		System.out.println(val);
		
	}

}

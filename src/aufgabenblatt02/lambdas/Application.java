/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */
package aufgabenblatt02.lambdas;

import static aufgabenblatt02.lambdas.Calculator.Operation.*;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calculator c = new Calculator();
		
		System.out.println("10 + 5 = " + c.calculate(ADD, 10, 5));
		System.out.println("10 - 5 = " + c.calculate(SUBTRACT, 10, 5));
		System.out.println("10 * 5 = " + c.calculate(MULTIPLY, 10, 5));
		System.out.println("10 / 5 = " + c.calculate(DIVIDE, 10, 5));
		
		System.out.println();
		
		DoubleDoubleToDouble multiply = (d1, d2) -> d1 * d2;
		DoubleDoubleToDouble root = (a, b) -> -b / a;
		
		System.out.println("3 * 4 = " + multiply.evaluate(3, 4));
		System.out.println("NS von y = 3x - 4 --> x0 = " + root.evaluate(3, -4));
		
	}

}

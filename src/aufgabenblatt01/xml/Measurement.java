/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.09.2016 
 * Aufgabe: Aufgabenblatt 1 Aufgabe 2
 */
package aufgabenblatt01.xml;

import java.time.LocalDateTime;

public class Measurement {

	private double value;
	private LocalDateTime timestamp;
	
	
	public Measurement(double value, LocalDateTime timestamp) {
		this.value = value;
		this.timestamp = timestamp;
	}
	
	
	public double getValue() {
		return value;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public String toString() {
		return "\n Value: "+ value + " Timestamp: " + timestamp; 
	}
}

/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 20.10.2016
 * Aufgabe: Aufgabenblatt 2
 */
package aufgabenblatt02.threads;

public class Plane {

	private enum Status {
		IN_FLIGHT, ON_APPROACH, LANDED
	} 
	
	/**
	 * Airport where the Plane is lading
	 */
	private Airport airport;
	
	/**
	 * Id of the Plane
	 */
	private String id;
	
	/**
	 * Flight Duration of the Plane 
	 */
	private int flightDuration;
	
	/**
	 * Status of the Plan
	 */
	private Status status;
	
	/**
	 * Current Simulation time
	 */
	private int time;
	
	
	
}

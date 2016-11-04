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
	 * Start time of the plane
	 */
	private int startTime;
	
	/**
	 * Current Simulation time
	 */
	private int time;
	
	
	public Plane(String id, int flightDuration, Airport airport, int startTime) {
		this.id = id;
		this.flightDuration = flightDuration;
		this.airport = airport;
		this.startTime = startTime;
		this.status = Status.IN_FLIGHT;
	}
	
	public void run() {
		if(flightDuration + startTime <= time) {
			status = Status.ON_APPROACH;
			airport.land(this);
		}
	}
	
	public boolean isLanded() {
		if(status == Status.LANDED) {
			return true;
		}
		return false;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void landed() {
		status = Status.LANDED;
	}
	
	
	@Override
	public String toString() {
		return id + " (" + this.status + ", FlightDuration left: " + (flightDuration + startTime - time) + ", Airport: " + airport.getName() + ")";
		
	}
}

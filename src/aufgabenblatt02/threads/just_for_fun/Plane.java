/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */

package aufgabenblatt02.threads.just_for_fun;

import static aufgabenblatt02.threads.just_for_fun.FlightState.*;

/**
 * Representation of a Plane
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 09.11.2016
 */
public class Plane extends Thread {

	/**
	 * the name of the airline
	 */
	private final String airline;

	/**
	 * the flight number
	 */
	private final int flightNumber;

	/**
	 * the destination
	 */
	private final Airport destination;

	/**
	 * the duration of the flight in time ticks (1s = 2 ticks)
	 */
	private final int flightDuration;

	/**
	 * time in ticks when the plane started (was constructed)
	 */
	private final int departureTime;

	/**
	 * current time in the simulation
	 */
	private int currentTime;

	/**
	 * the runway to land on
	 */
	private Runway rwy;

	/**
	 * the current state of the plane
	 */
	private FlightState flightState;

	public Plane(String airline, int flightNumber, Airport destination,
			int flightDuration, int departureTime) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.flightDuration = flightDuration;
		this.departureTime = departureTime;
		currentTime = departureTime;
		flightState = CRUISING;
		rwy = null;

		this.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (flightState != LANDED && !isInterrupted()) {
			if (currentTime - departureTime > flightDuration) {
				// ask airport if we can land
				destination.requestLanding(this);

				try {
					sleep(1500);
				} catch (InterruptedException e) {
					// do nothing
				}

				landed();

				// tell airport we landed
				destination.leftRunway(this);
			}
		}
	}

	/**
	 * tell the plane to enter a hold, waiting for a landing slot
	 */
	public void hold() {
		flightState = HOLDING;
	}

	/**
	 * tell the plane it is allowed to land, and give it the runway it can land
	 * on
	 * 
	 * @param rwy
	 *            the Runway to land at
	 */
	public void land(Runway rwy) {
		flightState = LANDING;
		this.rwy = rwy;
	}

	/**
	 * confiugre landing
	 */
	private void landed() {
		flightState = LANDED;
		rwy = null;
		System.out.println(this);
	}

	/**
	 * Update simulation time
	 * 
	 * @param newTime
	 *            the new Simulation time
	 */
	public void update(int newTime) {
		currentTime = newTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#toString()
	 */
	@Override
	public String toString() {
		switch (flightState) {
			case CRUISING :
				return airline + " " + flightNumber + " is " + flightState
						+ " enroute to " + destination + " - ETA: "
						+ (departureTime + flightDuration);
			case HOLDING :
				return airline + " " + flightNumber + " is " + flightState
						+ " at " + destination;
			case LANDING :
				return airline + " " + flightNumber + " is " + flightState
						+ " on " + rwy;
			case LANDED :
				return airline + " " + flightNumber + " has " + flightState
						+ " at " + destination
						+ ". Thank you for flying with us today.";
			default :
				return airline + " " + flightNumber + " is " + flightState;
		}

	}

	/**
	 * Checks whether the plane has landed
	 * 
	 * @return true if the plane has landed
	 */
	public boolean hasLanded() {
		return flightState == LANDED;
	}

}

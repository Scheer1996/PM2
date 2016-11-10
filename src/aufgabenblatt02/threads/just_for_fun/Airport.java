/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */

package aufgabenblatt02.threads.just_for_fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Simulation of an Airport
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 09.11.2016
 */
public class Airport {

	/**
	 * maximum time in Simulator
	 */
	private static final int SIM_TIME_MAX = 60;

	/**
	 * delay between time ticks (in ms)
	 */
	private static final int TIME_TICK_DELAY = 500;

	/**
	 * maximum number of planes
	 */
	private static final int MAX_PLANES = 10;

	/**
	 * highest possible flightnumber
	 */
	private static final int MAX_FLIGHTNUMBER = 10000;

	/**
	 * maximum duration of a flight
	 */
	private static final int MAX_FLIGHT_DURATION = 10;

	/**
	 * some airlines so output is more interesting
	 */
	private static final String[] AIRLINES = {"Lufthansa", "Air Berlin",
			"Emirates", "Ryanair"};

	/**
	 * the List of runways
	 */
	private final List<Runway> runways;

	/**
	 * the name of the airport
	 */
	private final String name;

	/**
	 * the list of active planes
	 */
	private List<Plane> planes;

	/**
	 * RNG for creating new planes
	 */
	private Random random;

	/**
	 * current time in simulation
	 */
	private int time;

	public Airport(String name, Runway... rwys) {
		this.name = name;
		runways = Arrays.asList(rwys);
		planes = new ArrayList<>();
		random = new Random(System.currentTimeMillis());
		time = 0;
	}

	/**
	 * main loop - runs the simulation
	 */
	public void simulate() {
		// create planes
		for (int i = 0; i < MAX_PLANES; i++) {
			createNewPlane();
		}

		while (time < SIM_TIME_MAX) {
			// print status
			System.out
					.println("===============================================");
			System.out.println("                 Time: " + time);
			System.out
					.println("===============================================");
			planes.forEach(System.out::println);

			try {
				Thread.sleep(TIME_TICK_DELAY);
			} catch (InterruptedException e) {
				// do nothing
			}
			time++;

			Iterator<Plane> it = planes.iterator();
			while (it.hasNext()) {
				Plane p = it.next();
				p.update(time);
				if (p.hasLanded()) {
					it.remove();
				}
			}

			// create new planes
			for (int i = planes.size(); i < MAX_PLANES; i++) {
				createNewPlane();
			}
		}

		planes.forEach(Thread::interrupt);
	}

	/**
	 * create a new random plane
	 */
	private void createNewPlane() {
		String airline = AIRLINES[random.nextInt(AIRLINES.length)];
		int flightNbr = random.nextInt(MAX_FLIGHTNUMBER);
		int flightDuration = random.nextInt(MAX_FLIGHT_DURATION);
		planes.add(new Plane(airline, flightNbr, this, flightDuration, time));
	}

	/**
	 * [Monitor Method] Request landing
	 * 
	 * @param p
	 *            the plane that requested the landing
	 */
	public synchronized void requestLanding(Plane p) {
		Optional<Runway> rwy;
		while (true) {
			rwy = runways.stream().filter(Runway::isAvailiable).findAny();
			if (rwy.isPresent()) {
				break;
			} else {
				try {
					p.hold();
					this.wait();
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}
		rwy.get().assignTo(p);
		p.land(rwy.get());
	}

	/**
	 * [Monitor Method] Return the shared ressource (runway) so it can be used
	 * by others
	 * 
	 * @param p
	 *            the plane that has landed
	 */
	public synchronized void leftRunway(Plane p) {
		runways.stream().filter(r -> r.isAssignedTo(p)).findAny().get()
				.freeRunway();
		this.notifyAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}

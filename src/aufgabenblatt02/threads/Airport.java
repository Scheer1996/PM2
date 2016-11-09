/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */
package aufgabenblatt02.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Airport {

	private String name;
	
	/**
	 * The number of plane
	 */
	private int planeCount;
	
	
	/**
	 * List of Airplanes
	 */
	private List<Plane> planes = new ArrayList<Plane>();
	
	private int time = 0;
	
	
	public Airport(String name) {
		this.name = name;
	}
	
	public void run() {
		Plane p1 = createPlane(this, 2000);
		Plane p2 = createPlane(this, 2000);
		addPlane(p1);
		addPlane(p2);
		while(true) {
			try {
				Thread.sleep(500);
				time += 500;
			} catch (InterruptedException e) {
			}
			System.out.println("Time: " + time);
			if(planes.size() > 0) {
				for (Plane plane : planes) {
					plane.setTime(time);
					System.out.println(plane);
					plane.run();
					
				}
			}
			
			System.out.println("------------------------------------------------------------------------");
		}
	}
	
	public synchronized void land (Plane plane) {
		try {
			Thread.sleep(1500);
			time += 1500;
		} catch (InterruptedException e) {
		}
		plane.landed();
		System.out.println("-> Landed: " + plane);
	}
	
	private Plane createPlane(Airport airport, int flightDuration) {
		int flightNr = (int) (10000 * Math.random());
 		Plane plane = new Plane("Plane " + flightNr, flightDuration, airport, airport.getTime());
		System.out.println("-> New: " + plane);
		return plane;
	}
	
	
	public void addPlane(Plane plane) {
		planes.add(plane);
	}
	
	public int getTime() {
		return time;
	}
	
	public String getName() {
		return name;
	}
}

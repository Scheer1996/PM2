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
import java.util.ArrayList;

public class Sensor {

	String id;
	ArrayList<Measurement> measurements = new ArrayList<Measurement>();
	
	public Sensor(String id) {
		this.id = id;
	}
	
	/**
	 * adds new Measurement with current Time to the Sensor measurements
	 * @param value 
	 */
	public void addNewMeasurement(double value) {
		Measurement measurement = new Measurement(value, LocalDateTime.now());
		measurements.add(measurement);
	}

	/**
	 * adds a Measurement Element which already exists to the Sensor measurements
	 * @param m
	 */
	public void addMeasurementElement(Measurement m) {
		measurements.add(m);
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 */
	
	public String toString() {
		String s = "Sensor: " + id + "\n";
		s += measurements.toString();
		return s;
	}
}

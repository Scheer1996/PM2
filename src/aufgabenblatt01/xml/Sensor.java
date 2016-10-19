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
import java.util.List;

import aufgabenblatt01.wiederholung.Student;

/**
 * Representation of a sensor
 *
 * @author Philip Scheer
 * @version 1.1 - 16.10.2016
 */
public class Sensor {

    /**
     * the sensors ID
     */
    private final String id;

    /**
     * a list of measurements taken by this sensor
     */
    private ArrayList<Measurement> measurements;

    public Sensor(String id) {
        this.id = id;
        measurements = new ArrayList<Measurement>();
    }

    /**
     * Adds new measurement with current time to the sensors measurements
     * 
     * @param value
     *            the value to add
     */
    public void addNewMeasurement(double value) {
        Measurement measurement = new Measurement(value, LocalDateTime.now());
        measurements.add(measurement);
    }

    /**
     * Adds a measurement which already exists to the sensors measurements
     * 
     * @param m
     *            the measurement to add
     */
    public void addMeasurement(Measurement m) {
        measurements.add(m);
    }

    /**
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return the measurments
     */
    public List<Measurement> getMeasurements() {
        return measurements;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sensor)) {
            return false;
        }

        Sensor s2 = (Sensor) obj;
        if(!this.getId().equals(s2.getId())) {
        	return false;
        }
        
        //check if there are a equal number of Measurements
		if(s2.getMeasurements().size() != this.getMeasurements().size()) {
			return false;
		}
		
		if (this.getMeasurements().size() == 0) {

			return true;
		} else {
			for (int i = 0; i > this.getMeasurements().size(); i++) {
				// if measurment objects are not equal return false
				if (!this.getMeasurements().get(i).equals(s2.getMeasurements().get(i))) {
					return false;
				}
			}
		}
        
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "Sensor [id=" + id + ", measurements=" + (measurements != null
                ? measurements.subList(0, Math.min(measurements.size(), maxLen))
                : null) + "]";
    }
}

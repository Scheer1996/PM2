/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.09.2016 
 * Aufgabe: Praktikum 1
 */
package aufgabenblatt01.xml;

import java.time.LocalDateTime;

/**
 * Represents a measurement taken from a sensor
 *
 *
 * @author Philip Scheer
 * @version 1.1 - 16.10.2016
 */
public class Measurement {

    /**
     * the value that was recorded
     */
    private final double value;

    /**
     * the time and date of the recording
     */
    private final LocalDateTime timestamp;

    public Measurement(double value, LocalDateTime timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Measurement [value=" + value + ", timestamp=" + timestamp + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Measurement)) {
            return false;
        }

        Measurement m2 = (Measurement) obj;
        if (this.getValue() == m2.getValue()
                && this.getTimestamp().equals(m2.getTimestamp())) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (int) (timestamp.hashCode() * value);
    }
}

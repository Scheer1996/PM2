/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 16.10.2016 
 * Aufgabe: Aufgabenblatt X
 */

package aufgabenblatt01.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import aufgabenblatt01.xml.Measurement;
import aufgabenblatt01.xml.Sensor;

/**
 * JUnit test for {@link Sensor}
 *
 * @author Moritz Höwer
 * @version 1.0 - 16.10.2016
 */
public class TestSensor {

    /**
     * Test method for
     * {@link aufgabenblatt01.xml.Sensor#addNewMeasurement(double)}.
     */
    @Test
    public void testAddNewMeasurement() {
        final double VALUE = 22.4;
        Sensor sensor = new Sensor("Test");

        assertThat("Sensor should not have any Measurements",
                sensor.getMeasurements().isEmpty(), is(true));

        sensor.addNewMeasurement(VALUE);

        assertThat("Number of Measurements is wrong",
                sensor.getMeasurements().size(), is(1));
        Measurement measurment = sensor.getMeasurements().get(0);
        assertThat("Measurment has wrong value", measurment.getValue(),
                equalTo(VALUE));
        assertThat("Measurment has wrong timestamp",
                measurment.getTimestamp().compareTo(LocalDateTime.now()),
                either(equalTo(-1)).or(equalTo(0)));
    }

}

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
import aufgabenblatt01.xml.XmlApplication;

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
    
    /**
     * Test method for reading and writing Sensor Elements in XML File
     */
    @Test
    public void testReadWriteFunction() {
    	//without Measurements
    	Sensor s1 = new Sensor("Testsensor");
    	XmlApplication.writeSensor(s1, "testsensor.xml");
    	Sensor s2 = XmlApplication.readSensor("testsensor.xml");

        assertThat("s1 should equal itself", s1, equalTo(s2));
        
        
        //with measurements
    	Sensor s3 = new Sensor("Testsensor");
    	Measurement m1 = new Measurement(20, LocalDateTime.now());
    	Measurement m2 = new Measurement(25, LocalDateTime.now());
    	Measurement m3 = new Measurement(30, LocalDateTime.now());
    	s3.addMeasurement(m1);
    	s3.addMeasurement(m2);
    	s3.addMeasurement(m3);
    	
    	XmlApplication.writeSensor(s3, "testsensor.xml");
    	Sensor s4 = XmlApplication.readSensor("testsensor.xml");
    	
        assertThat("s3 should equal itself", s3, equalTo(s4));

        
        //with measurements and NOT equals
    	Sensor s5 = new Sensor("Testsensor");
    	Measurement m4 = new Measurement(19, LocalDateTime.now());
    	s5.addMeasurement(m4);
    	XmlApplication.writeSensor(s1, "testsensor.xml");
    	Sensor s6 = XmlApplication.readSensor("testsensor.xml");

        assertThat("s5 should not be equal to s6", s5, not(equalTo(s6)));
    	
    }

}

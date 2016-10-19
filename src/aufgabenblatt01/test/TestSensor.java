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
     * Test method for
     * {@link aufgabenblatt01.xml.Sensor#addMeasurement(Measurement)}.
     */
    @Test
    public void testAddMeasurement() {
        final double VALUE = 22.4;
        final LocalDateTime TIME = LocalDateTime.now();
        Sensor sensor = new Sensor("Test");

        Measurement m = new Measurement(VALUE, TIME);
        
        sensor.addMeasurement(m);

        assertThat("Number of Measurements is wrong",
                sensor.getMeasurements().size(), is(1));
        Measurement measurment = sensor.getMeasurements().get(0);
        assertThat("Measurment has wrong value", measurment.getValue(),
                equalTo(VALUE));
        assertThat("Measurment has wrong timestamp", measurment.getTimestamp(),
                equalTo(TIME));
    }
       
    /**
     * Test method for
     * {@link aufgabenblatt01.xml.Sensor#equals(Object)}.
     */
    @Test
    public void testSensorEquals() {
        final double VALUE = 22.4;
        final LocalDateTime TIME = LocalDateTime.now();
        final String SENSOR_ID = "Testsensor";
        final String SENSOR_ID_WRONG = "Testsensor Wrong";
        
    	Sensor s1 = new Sensor(SENSOR_ID);
    	Sensor s2 = new Sensor(SENSOR_ID);		// same as above
    	Sensor s3 = new Sensor(SENSOR_ID);		// without Measurement
    	Sensor s4 = new Sensor(SENSOR_ID_WRONG);// with wrong ID
    	Measurement m1 = new Measurement(VALUE, TIME);
    	s1.addMeasurement(m1);
    	s2.addMeasurement(m1);
    	s4.addMeasurement(m1);
    	
        assertThat("s1 should be equal to s2", s1, equalTo(s2));
        assertThat("s1 should not be equal to s3", s1, not(equalTo(s3)));
        assertThat("s1 should not be equal to s4", s1, not(equalTo(s4)));
    }
    
    /**
     * Test method for
     * {@link aufgabenblatt01.xml.Measurement#equals(Object)}.
     */
    @Test
    public void testMeasurmentEquals() {
        final double VALUE = 22.4;
        final LocalDateTime TIME = LocalDateTime.now();
        final double OTHER_VALUE = 30;
        final LocalDateTime OTHER_TIME = LocalDateTime.MAX;
        
    	Measurement m1 = new Measurement(VALUE, TIME);
    	Measurement m2 = new Measurement(VALUE, TIME);			// same as m1
    	Measurement m3 = new Measurement(OTHER_VALUE, TIME);	// other value
    	Measurement m4 = new Measurement(VALUE, OTHER_TIME);	// other timestamop
    	
    	
        assertThat("m1 should be equal to m2", m1, equalTo(m2));
        assertThat("m1 should be equal to m3", m1, not(equalTo(m3)));
        assertThat("m1 should be equal to m4", m1, not(equalTo(m4)));
    }
    
    /**
     * Test method for reading and writing Sensor Elements in XML File
     */
    @Test
    public void testReadWrite() {
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

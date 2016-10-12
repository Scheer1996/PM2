package aufgabenblatt01.xml;

import java.time.LocalDateTime;

public class XmlApplication {

	public static void main(String[] args) {
		
		Sensor s = new Sensor("TestSensor");
		Measurement m = new Measurement(20.20, LocalDateTime.now());
		s.addMeasurementElement(m);
		s.addNewMeasurement(20.20);
		s.addNewMeasurement(10.00);
		System.out.println(s.toString());
		
		
		try {
			Sensor sensor = getSensor();
			//System.out.println(sensor.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e);
		}
	}
	
	
	
	public static Sensor getSensor() {
		XmlSensorParser sensorParser = new XmlSensorParser("/Users/Philip/Development/Studium/PM2/bin/aufgabenblatt01/xml/sensor.xml");
		return sensorParser.parseSensor();
	}
}

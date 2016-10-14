package aufgabenblatt01.xml;

import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class XmlApplication {
	
	private static final String XML_FILE = "sensor.xml";

	/**
	 * Main Method. Nothing important for testing
	 */
	public static void main(String[] args) {
		
		Sensor s = new Sensor("TestSensor");
		Measurement m = new Measurement(20.20, LocalDateTime.now());
		s.addMeasurementElement(m);
		s.addNewMeasurement(20.20);
		s.addNewMeasurement(10.00);
		System.out.println(s.toString());
		

		Sensor s2 = getSensor(XML_FILE); //read out sensor which was written in the xml file
		System.out.println(s2.toString());
		
		
		writeSensor(s2, XML_FILE); //write above generated sensor to xml file

	}
	
	
	/**
	 * gets the Sensor Data which is placed in the XML File @ XML_PATH
	 * @return Sensor
	 */
	private static Sensor getSensor(String filename) {
		String filepath = XmlApplication.class.getResource(filename).getFile();
		XmlSensorParser sensorParser = new XmlSensorParser(filepath);
		//XmlSensorParser sensorParser = new XmlSensorParser();
		return sensorParser.parseSensor();
	}
	
	/**
	 * writes the Sensor s to the XML File @ XML_PATH
	 * @param s Sensor
	 */
	private static void writeSensor(Sensor s, String filename) {
		String filepath = XmlApplication.class.getResource(filename).getFile();
		XmlSensorWriter sensorWriter;
		try {
			sensorWriter = new XmlSensorWriter(filepath);
			sensorWriter.writeSensor(s);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

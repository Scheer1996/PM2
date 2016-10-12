/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: <Datum>
 * Aufgabe: Aufgabenblatt 1 Aufgabe 1
 */
package aufgabenblatt01.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class XmlSensorParser {
	
	private Document document;
	
	public XmlSensorParser(String filename) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Error while reading XML File");
		}
	}
	
	/**
	 * Parse the Sensor Element and create a new Sensor Object
	 * @return Sensor Object
	 */
	public Sensor parseSensor() {
		Element e = document.getDocumentElement();
		// found Sensor Element in XML
		System.out.println("Test 1");
		if(e != null && e.getNodeName().equals("Sensor")) {
			
			System.out.println(e.getAttribute("id"));
			
			 //creates new Sensor with id as the name
			Sensor sensor = new Sensor(e.getAttribute("id"));
			return sensor;
		} else {
			return null;
		}
	}
	
	/**
	 * Parse the Measurement Elements and return them as Measurement Object
	 * @param e
	 */
	private void parseMeasurements(Element e) {
		
	}
}

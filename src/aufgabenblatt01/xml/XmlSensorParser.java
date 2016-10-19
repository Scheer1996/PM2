/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.09.2016 
 * Aufgabe: Praktikum 1
 */
package aufgabenblatt01.xml;

import java.io.File;
import java.time.LocalDateTime;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Utility class to generate a sensor from an XML File
 *
 * @author Philip Scheer
 * @version 1.0 - 19.10.2016
 */
public class XmlSensorParser {

    /**
     * the document
     */
    private Document document;

    public XmlSensorParser(String string) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File(string));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error while reading XML File");
        }
    }

    /**
     * Parse the Sensor Element and create a new Sensor Object
     * 
     * @return Sensor Object
     */
    public Sensor parseSensor() {
        Element e = document.getDocumentElement();
        // found Sensor Element in XML
        if (e != null && e.getNodeName().equals("sensor")) {
            // creates new Sensor with id as the name
            Sensor sensor = new Sensor(e.getAttribute("id"));

            for (int i = 0; i < e.getChildNodes().getLength(); i++) {
                Node childNode = e.getChildNodes().item(i);
                if (childNode instanceof Element) {
                    Element childElement = (Element) childNode;
                    switch (childNode.getNodeName()) {
                        case "measurement" :
                            Measurement m = parseMeasurement(childElement);
                            sensor.addMeasurement(m);
                            break;
                        case "information" :
                            // maybe a new Type for extra Information from the
                            // Sensor
                            break;

                        default :
                            System.out.println(
                                    "No case for " + childNode.getNodeName());
                    }
                }
            }

            return sensor;
        } else {
            return null;
        }
    }

    /**
     * Parse the Measurement Element and returns as Measurement Object
     * 
     * @param e
     *            the element to parse
     * @return the measurement that was stored in the element
     */
    private Measurement parseMeasurement(Element e) {
        if (e != null && e.getNodeName().equals("measurement")) {
            NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
            Number value;

            try {
                // get measurement Value
                value = format.parse(e.getAttribute("value"));

                // get timeStamp
                LocalDateTime timeStamp = LocalDateTime
                        .parse(e.getAttribute("timestamp"));

                Measurement m = new Measurement(value.doubleValue(), timeStamp);
                return m;
            } catch (ParseException e1) {
                System.err.println(
                        "Value was not a Number with Germany formation (comma)");
                return null;
            }
        } else {
            return null;
        }
    }
}

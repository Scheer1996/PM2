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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
/**
 * Application that combines reading and writing of Sensor data to XML files
 *
 * @author Philip Scheer, Moritz Höwer
 * @version 1.0 - 19.10.2016
 */
public class XmlApplication {
    /**
     * the name of the XML file
     */
    private static final String XML_FILE = "sensor.xml";

    /**
     * program entry point
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {

        Sensor s = new Sensor("TestSensor");
        Measurement m = new Measurement(20.20, LocalDateTime.now());
        s.addMeasurement(m);
        s.addNewMeasurement(20.20);
        s.addNewMeasurement(10.00);
        System.out.println(s.toString());

        Sensor s2 = readSensor(XML_FILE); // read out sensor which was written
                                          // in the xml file
        System.out.println(s2.toString());

        writeSensor(s2, XML_FILE); // write above generated sensor to xml file

    }

    /**
     * gets the Sensor Data which is placed in the XML File @ XML_PATH
     * 
     * @param filename
     *            the name of the file to parse
     * @return the sensor that was stored in the file
     */
    public static Sensor readSensor(String filename) {
        String filepath = System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "aufgabenblatt01" + File.separator
                + "xml" + File.separator + filename;

        XmlSensorParser sensorParser = new XmlSensorParser(filepath);
        return sensorParser.parseSensor();
    }

    /**
     * Writes a Sensor to a File
     * 
     * @param s
     *            the sensor to write
     * @param filename
     *            the name of the file to write to
     */
    public static void writeSensor(Sensor s, String filename) {
        String filepath = System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "aufgabenblatt01" + File.separator
                + "xml" + File.separator + filename;

        XmlSensorWriter sensorWriter;
        try {
            sensorWriter = new XmlSensorWriter(filepath);
            sensorWriter.writeSensor(s);
        } catch (ParserConfigurationException e) {
            System.err.println(e);
        } catch (TransformerException e) {
            System.err.println(e);
        }

    }
}

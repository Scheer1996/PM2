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

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Utility class to write a Sensor to an XML File
 *
 * @author Philip Scheer
 * @version 1.0 - 19.10.2016
 */
public class XmlSensorWriter {

    /**
     * the document
     */
    private Document document;

    /**
     * the filePath to write to
     */
    private String filePath;

    /**
     * Initializes the DocumentBuilder for the SensorWriter
     * 
     * @param filePath
     *            the filePath to write to
     * @throws ParserConfigurationException
     *             if creation of Document fails
     */
    public XmlSensorWriter(String filePath)
            throws ParserConfigurationException {
        this.filePath = filePath;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();

    }

    /**
     * Creates a XML Document out of the Sensor s with all measurements
     * 
     * @param s
     *            the sensor to write
     * @throws TransformerException if transformation fails
     */
    public void writeSensor(Sensor s) throws TransformerException {
        Element rootElement = document.createElement("sensor");
        rootElement.setAttribute("id", s.getId());

        for (Measurement m : s.getMeasurements()) {
            Element measurmentElement = document.createElement("measurement"); // create
                                                                               // new
                                                                               // measurment
            measurmentElement.setAttribute("timestamp",
                    m.getTimestamp().toString()); // add TimeStamp
            String valueString = Double.toString(m.getValue()); // format value
            measurmentElement.setAttribute("value",
                    valueString.replace(".", ",")); // add value and format with
                                                    // comma
            rootElement.appendChild(measurmentElement); // append measurement
        }
        document.appendChild(rootElement);

        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

}

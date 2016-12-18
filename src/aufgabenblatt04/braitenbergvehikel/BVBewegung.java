/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.12.2016 
 * Aufgabe: Praktikum 4
 */

package aufgabenblatt04.braitenbergvehikel;

/**
 * Berechnet aus Sensoren und Motorfunktionen die Ansteuerung der beiden
 * Motoren.
 * 
 * Von Interface zu Abstrakter Klasse geändert, um gemeinsame Implementierungen
 * hoch zu ziehen.
 * 
 * @author Philipp Jenke, Moritz Höwer, Philip Scheer
 * @version 2.0 - 18.12.2016
 */
public abstract class BVBewegung {

    /**
     * Berechnet den Ansteuerungswert für den linken Motor aus [0,1] aus den
     * Sensorwerten.
     */
    public abstract double berechneMotorAnsteuerungLinks(double sensorWertLinks,
            double sensorWertRechts);

    /**
     * Berechnet den Ansteuerungswert für den rechten Motor aus [0,1] aus den
     * Sensorwerten.
     */
    public abstract double berechneMotorAnsteuerungRechts(
            double sensorWertLinks, double sensorWertRechts);

    /**
     * Liefert eine eindeutige ID.
     */
    public abstract String getId();

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof BVBewegung) {
            return getId().equals(((BVBewegung) other).getId());
        }
        return false;
    }
}

package aufgabenblatt04.braitenbergvehikel;

/**
 * Berechnet aus Sensoren und Motorfunktionen die Ansteuerung der beiden
 * Motoren.
 * 
 * @author Philipp Jenke
 */
public interface BVBewegung {

  /**
   * Berechnet den Ansteuerungswert für den linken Motor aus [0,1] aus den
   * Sensorwerten.
   */
  public double berechneMotorAnsteuerungLinks(double sensorWertLinks,
      double sensorWertRechts);

  /**
   * Berechnet den Ansteuerungswert für den rechten Motor aus [0,1] aus den
   * Sensorwerten.
   */
  public double berechneMotorAnsteuerungRechts(double sensorWertLinks,
      double sensorWertRechts);

  /**
   * Liefert eine eindeutige ID.
   */
  public String getId();
}

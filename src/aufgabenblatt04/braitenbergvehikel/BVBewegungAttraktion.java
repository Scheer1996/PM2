package aufgabenblatt04.braitenbergvehikel;

/**
 * Attraktion hin zum Signal.
 * 
 * @author Philipp Jenke
 */
public class BVBewegungAttraktion extends BVBewegung {
  public static final String ID = "ATTRAKTION";

  @Override
  public double berechneMotorAnsteuerungLinks(double sensorWertLinks,
      double sensorWertRechts) {
    return sensorWertRechts * sensorWertRechts;
  }

  @Override
  public double berechneMotorAnsteuerungRechts(double sensorWertLinks,
      double sensorWertRechts) {
    return sensorWertLinks * sensorWertLinks;
  }

  @Override
  public String getId() {
    return ID;
  }
  
  
}

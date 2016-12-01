package aufgabenblatt04.braitenbergvehikel;

/**
 * Abstossung von dem Signal.
 * 
 * @author Philipp Jenke
 */
public class BVBewegungAbstossung implements BVBewegung {

  public static final String ID = "ABSTOSSUNG";

  @Override
  public double berechneMotorAnsteuerungLinks(double sensorWertLinks,
      double sensorWertRechts) {
    return sensorWertLinks;
  }

  @Override
  public double berechneMotorAnsteuerungRechts(double sensorWertLinks,
      double sensorWertRechts) {
    return sensorWertRechts;
  }

  @Override
  public String getId() {
    return ID;
  }
}

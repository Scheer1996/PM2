package aufgabenblatt04.braitenbergvehikel;

import java.util.Observable;

/**
 * Ein Braitenberg-Vehikel "fühlt" zwei Sensorwerte und steuert darauf basierend
 * zwei Motoren an.
 * 
 * @author Philipp Jenke
 */
public class BraitenbergVehikel extends Observable {

  /**
   * Richtungs-Enum für Motoren und Sensoren.
   * 
   * @author Philipp Jenke
   *
   */
  public static enum Richtung {
    LINKS, RECHTS
  };

  /**
   * Sensorwerte, liegen in [0,1]
   */
  private double[] sensorWert = { 0, 0 };

  /**
   * Position des Vehikels im Weltkoordinatensystem.
   */
  private Vektor2 position = new Vektor2(0, 0);

  /**
   * Länge (und Breite) des quadratischen Vehikels.
   */
  private double seitenlaenge = 50;

  /**
   * Radius der Räder.
   */
  private double radRadius = 10;

  /**
   * Orientierung des Vehikels (in diese Richtung zeigen die Sensoren.
   */
  private Vektor2 orientierung = new Vektor2(0, 1);

  /**
   * Aktuelles Bewegungsverhalten.
   */
  private BVBewegung bewegung;

  /**
   * Maximale Motorgeschwindigkeit im Umdrehungen pro Sekunde.
   */
  private double maxMotorUmdrehungenProSek = 0.25;

  /**
   * Name des Vehikels zur Identifikation.
   */
  private final String name;

  public BraitenbergVehikel(String name, BVBewegung bewegung, Vektor2 position,
      Vektor2 orientierung) {
    this.name = name;
    this.bewegung = bewegung;
    this.position = position;
    this.orientierung = orientierung;
  }

  public BraitenbergVehikel(String name, BVBewegung bewegung) {
    this(name, bewegung, new Vektor2(0, 0), new Vektor2(0, 1));
  }

  /**
   * Berechnet die Bewegung des Vehikels aus den aktuellen Sensorwerten.
   */
  public void bewege() {
    double umdrehungenLinks = bewegung.berechneMotorAnsteuerungLinks(
        sensorWert[Richtung.LINKS.ordinal()],
        sensorWert[Richtung.RECHTS.ordinal()]) * getMaxUmdrehungenProSek();
    double umdrehungenRechts = bewegung.berechneMotorAnsteuerungRechts(
        sensorWert[Richtung.LINKS.ordinal()],
        sensorWert[Richtung.RECHTS.ordinal()]) * getMaxUmdrehungenProSek();
    double motorBewegungLinks =
        umdrehungenLinks * Math.PI * getRadRadius() * 2.0;
    double motorBewegungRechts =
        umdrehungenRechts * Math.PI * getRadRadius() * 2.0;
    bewege(motorBewegungLinks, motorBewegungRechts);
  }

  /**
   * Setzt den Sensorwert des angegebenen Sensors. Die Werte müssen aus [0,1]
   * kommen.
   */
  public void setSensorwert(Richtung richtung, double wert) {
    assert (wert >= 0 && wert <= 1);
    sensorWert[richtung.ordinal()] = wert;
  }

  /**
   * Rotation der Orientierung in Grad, 0 Grad = (0,1), aufsteigend gegen den
   * Uhrzeigersinn.
   */
  public double getRotationGradGegenUhrzeigersinn() {
    double cosWinkel = new Vektor2(0, 1).skalarProdukt(orientierung);
    double winkel = Math.acos(cosWinkel) * 180.0 / Math.PI;
    if (orientierung.skalarProdukt(new Vektor2(1, 0)) > 0) {
      winkel = -winkel;
    }
    return winkel;
  }

  /**
   * Rotation der Orientierung in Grad, 0 Grad = (0,1), aufsteigend im
   * Uhrzeigersinn.
   */
  public double getRotationGradImUhrzeigersinn() {
    return -getRotationGradGegenUhrzeigersinn();
  }

  /**
   * Bewegt das Vehikel basierend auf der Laufdistanz der beiden Räder.
   */
  protected void bewege(double streckeLinks, double streckeRechts) {
    if (Math.abs(streckeLinks - streckeRechts) < 1e-5) {
      position = position.addiere(orientierung.skaliere(streckeLinks));
    } else {
      Vektor2 rotationszentrum = null;
      double winkelBogenmass = 0;
      if (streckeLinks <= streckeRechts) {
        double x =
            (-seitenlaenge * streckeLinks) / (streckeLinks - streckeRechts);
        winkelBogenmass = streckeRechts / (x + seitenlaenge);
        rotationszentrum =
            position.subtrahiere(orientierung.skaliere(seitenlaenge / 2.0))
                .addiere(getLinksVektor().skaliere(seitenlaenge / 2.0 + x));
      } else {
        double x =
            (seitenlaenge * streckeRechts) / (streckeLinks - streckeRechts);
        winkelBogenmass = -streckeLinks / (x + seitenlaenge);
        rotationszentrum =
            position.subtrahiere(orientierung.skaliere(seitenlaenge / 2.0))
                .addiere(getRechtsVektor().skaliere(seitenlaenge / 2.0 + x));
      }
      position = position.subtrahiere(rotationszentrum);
      position = position.rotiere(winkelBogenmass);
      position = position.addiere(rotationszentrum);
      orientierung = orientierung.rotiere(winkelBogenmass);
      orientierung.normieren();
    }
  }

  /**
   * Liefert die Position eines Sensors.
   */
  public Vektor2 getSensorPosition(Richtung richtung) {
    if (richtung == Richtung.LINKS) {
      return position.addiere(orientierung.skaliere(seitenlaenge / 2.0))
          .addiere(getLinksVektor().skaliere(seitenlaenge / 2.0));
    } else {
      return position.addiere(orientierung.skaliere(seitenlaenge / 2.0))
          .addiere(getRechtsVektor().skaliere(seitenlaenge / 2.0));
    }
  }

  /**
   * Liefert einen Vektor, nach rechts zur Fahrtrichtung zeigt.
   */
  private Vektor2 getRechtsVektor() {
    return orientierung.rotiere(-Math.PI / 2.0);
  }

  /**
   * Liefert einen Vektor, nach rechts zur Fahrtrichtung zeigt.
   */
  private Vektor2 getLinksVektor() {
    return orientierung.rotiere(Math.PI / 2.0);
  }

  public double getSeitenlaenge() {
    return seitenlaenge;
  }

  public double getRadRadius() {
    return radRadius;
  }

  public void setPosition(Vektor2 position) {
    this.position = position;
  }

  public void setOrientierung(Vektor2 orientierung) {
    this.orientierung = orientierung;
  }

  public double getMaxUmdrehungenProSek() {
    return maxMotorUmdrehungenProSek;
  }

  public BVBewegung getBewegung() {
    return bewegung;
  }

  public void setBewegung(BVBewegung bewegung) {
    this.bewegung = bewegung;
    setChanged();
    notifyObservers();
  }

  public String getName() {
    return name;
  }

  public Vektor2 getPosition() {
    return position;
  }

  public Vektor2 getOrientierung() {
    return orientierung;
  }

  public double getSensorWert(Richtung richtung) {
    return sensorWert[richtung.ordinal()];
  }

  @Override
  public String toString() {
    return "Braitenberg-Vehikel " + getName() + " (p: " + position + ", o: "
        + orientierung + ")";
  }
}

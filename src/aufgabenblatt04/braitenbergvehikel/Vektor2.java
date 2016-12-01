package aufgabenblatt04.braitenbergvehikel;

/**
 * Repräsentiert einen Vektor in 2D.
 * 
 * @author Philipp Jenke
 */
public class Vektor2 {

  /**
   * Koordinaten des Vektors
   */
  private double koordinaten[] = { 0, 0 };

  public Vektor2(double x, double y) {
    koordinaten[0] = x;
    koordinaten[1] = y;
  }

  public Vektor2() {
    this(0, 0);
  }

  public Vektor2(Vektor2 v) {
    this(v.x(), v.y());
  }

  /**
   * Skalarprodukt zweier Vektoren.
   */
  public double skalarProdukt(Vektor2 v) {
    return x() * v.x() + y() * v.y();
  }

  /**
   * Rotiere den Vektor um den angegebenen Winkel (Rotation). Liefert das
   * Ergebnis der Rotation zurück.
   */
  public Vektor2 rotiere(double winkelBogenmass) {
    double x = Math.cos(winkelBogenmass) * koordinaten[0]
        - Math.sin(winkelBogenmass) * koordinaten[1];
    double y = Math.sin(winkelBogenmass) * koordinaten[0]
        + Math.cos(winkelBogenmass) * koordinaten[1];
    return new Vektor2(x, y);
  }

  /**
   * Skaliert den Vektor, liefert Ergebnis zurück.
   */
  public Vektor2 skaliere(double s) {
    return new Vektor2(x() * s, y() * s);
  }

  /**
   * Subtrahiert zweiten Vektor, liefert Ergebnis zurück.
   */
  public Vektor2 subtrahiere(Vektor2 v) {
    return new Vektor2(x() - v.x(), y() - v.y());
  }

  /**
   * Addiert zweiten Vektor, liefert Ergebnis zurück.
   */
  public Vektor2 addiere(Vektor2 v) {
    return new Vektor2(x() + v.x(), y() + v.y());
  }

  /**
   * Vektor normieren (Länge 1).
   */
  public void normieren() {
    double norm = getNorm();
    koordinaten[0] /= norm;
    koordinaten[1] /= norm;
  }

  /**
   * Liefert die Norm (Länge) des Vektors.
   */
  public double getNorm() {
    return Math.sqrt(x() * x() + y() * y());
  }

  public double x() {
    return koordinaten[0];
  }

  public double y() {
    return koordinaten[1];
  }

  @Override
  public String toString() {
    return String.format("(%.2f, %.2f)", x(), y());
  }
}

/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.12.2016 
 * Aufgabe: Praktikum 4
 */

package aufgabenblatt04.braitenbergvehikel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import aufgabenblatt04.braitenbergvehikel.BraitenbergVehikel.Richtung;

/**
 * Simulation von Braitenberg-Vehikeln.
 * 
 * Changes in v2.0: Made class Observable.
 * 
 * @author Philipp Jenke, Moritz Höwer, Philip Scheer
 * @version 2.0 - 18.12.2016
 */
public class BVSimulation extends Observable {

  /**
   * Position des Signals.
   */
  private Vektor2 signal = new Vektor2(150, 200);

  /**
   * Liste der zu simulierenden Vehikel
   */
  private List<BraitenbergVehikel> vehikel =
      new ArrayList<BraitenbergVehikel>();

  public BVSimulation() {
  }

  /**
   * Führt einen Simulationsschritt für alle Vehikel durch.
   */
  public void simulationsSchritt() {
    for (BraitenbergVehikel vehikel : this.vehikel) {
      // Berechne Sensorstärke
      vehikel.setSensorwert(Richtung.LINKS,
          getSignalstaerke(vehikel.getSensorPosition(Richtung.LINKS),
              vehikel.getOrientierung()));
      vehikel.setSensorwert(Richtung.RECHTS,
          getSignalstaerke(vehikel.getSensorPosition(Richtung.RECHTS),
              vehikel.getOrientierung()));

      // Bewege vehikel
      vehikel.bewege();
    }
    setChanged();
    notifyObservers();
  }

  /**
   * Berechnet die Signalstärke für einen Sensor durch die Lichtquelle.
   */
  private double getSignalstaerke(Vektor2 sensorPosition,
      Vektor2 orientierung) {
    Vektor2 d = signal.subtrahiere(sensorPosition);
    double entfernung = d.getNorm();
    d = d.skaliere(1.0 / entfernung);
    double cosWinkel = d.skalarProdukt(orientierung);
    if (cosWinkel < 0) {
      // Vehikel sieht vom Sensor weg.
      return 0;
    }

    // Winkel-basierte Signalstärke
    return cosWinkel;
  }

  public void vehikelHinzufuegen(BraitenbergVehikel vehikel) {
    this.vehikel.add(vehikel);
  }

  public int getAnzahlVehike() {
    return vehikel.size();
  }

  public BraitenbergVehikel getVehikel(int index) {
    return vehikel.get(index);
  }

  public Vektor2 getSignal() {
    return signal;
  }

  public void setSignal(double x, double y) {
    signal = new Vektor2(x, y);
  }  
}

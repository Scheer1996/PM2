/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.12.2016 
 * Aufgabe: Praktikum 4
 */

package aufgabenblatt04;

import javafx.application.Application;
import javafx.stage.Stage;
import aufgabenblatt04.braitenbergvehikel.BVBewegungAbstossung;
import aufgabenblatt04.braitenbergvehikel.BVBewegungAttraktion;
import aufgabenblatt04.braitenbergvehikel.BVSimulation;
import aufgabenblatt04.braitenbergvehikel.BraitenbergVehikel;
import aufgabenblatt04.braitenbergvehikel.Vektor2;
import aufgabenblatt04.view.GUI;

/**
 * JavaFX Anwendung zur Darstellung und Interaktion mit einer
 * Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke, Moritz Höwer, Philip Scheer
 * @version 2.0 - 18.12.2016
 */
public class BVAnwendung extends Application {

    /**
     * the simulation controlled by this controller
     */
    private BVSimulation simulation;

    /**
     * background thread for continous simulation
     */
    private SimulationBGThread bgThread;

    public BVAnwendung() {
        simulation = null;
        bgThread = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) {
        // Simulation zusammenstellen
        new GUI(this).showAndWait();
    }

    /**
     * returns the controlled simulation or generates a new one.
     * 
     * @return the {@link BVSimulation} controlled by this controller
     */
    public BVSimulation getSimulation() {
        if (simulation == null) {
            simulation = erzeugeSimulationsszene();
        }
        return simulation;
    }

    /**
     * Erzeugt eine Simulationsszene zum Testen.
     */
    public BVSimulation erzeugeSimulationsszene() {
        BraitenbergVehikel vehikel1 = new BraitenbergVehikel("Susi",
                new BVBewegungAttraktion());
        BraitenbergVehikel vehikel2 = new BraitenbergVehikel("Mike",
                new BVBewegungAbstossung(), new Vektor2(-100, 100),
                new Vektor2(1, 0));
        BVSimulation sim = new BVSimulation();
        sim.vehikelHinzufuegen(vehikel1);
        sim.vehikelHinzufuegen(vehikel2);
        return sim;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * creates and starts a new background thread to continuously run
     * simulation.
     */
    public void startBGThread() {
        if(bgThread == null){
            bgThread = new SimulationBGThread(getSimulation());
            bgThread.start();
        }
    }

    /**
     * stops (interrupts) the running background thread
     */
    public void stopBGThread() {
        if(bgThread != null){
            bgThread.interrupt();
            bgThread = null;
        }
    }

    /**
     * Member class to represent daemon background thread for continous
     * simulation.
     *
     * @author Moritz Höwer, Philip Scheer
     * @version 1.0 - 18.12.2016
     */
    private class SimulationBGThread extends Thread {

        private static final int BG_SIM_WAIT = 200;
        
        private BVSimulation sim;

        public SimulationBGThread(BVSimulation sim) {
            this.sim = sim;
            this.setDaemon(true);
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                sim.simulationsSchritt();
                try {
                    sleep(BG_SIM_WAIT);
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
    }
}

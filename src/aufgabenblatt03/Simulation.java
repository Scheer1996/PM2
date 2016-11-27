/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 27.11.2016 
 * Aufgabe: Praktikum 3
 */

package aufgabenblatt03;

import java.util.Random;

import aufgabenblatt03.TrainDriver.Task;

/**
 * The simulation (controller)
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 27.11.2016
 */
public class Simulation implements Runnable {

    private static final int SLEEP_DURATION = 500;

    /**
     * the Station that this simulation is based on
     */
    private Station station;

    /**
     * RNG for making simulation interesting
     */
    private Random random;

    /**
     * the number of platforms
     */
    private int numberOfPlatforms;

    public Simulation(int numberOfPlatforms) {
        this.numberOfPlatforms = numberOfPlatforms;
        station = new Station(numberOfPlatforms);
        random = new Random();

        station.addObserver((obs, arg) -> {
            if (obs instanceof Station) {
                TrainEvent te = (TrainEvent) arg;
                System.out.format("Driver %d is %s on Platform %d\n",
                        te.getId(), te.getAction().toString(),
                        te.getPlatform());
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (true) {
            Task task = random.nextBoolean() ? Task.ARRIVE : Task.DEPART;
            int platform = random.nextInt(numberOfPlatforms);
            new TrainDriver(station, task, platform);
            try {
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

}

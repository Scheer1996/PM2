/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 27.11.2016 
 * Aufgabe: Praktikum 3
 */

package aufgabenblatt03;

/**
 * A TrainDriver that performs some operation with the trains (part of model)
 * 
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 27.11.2016
 */
public class TrainDriver {
    /**
     * the different possible tasks
     */
    public enum Task {
        ARRIVE, DEPART
    }

    private static int instanceCounter = 0;

    private int id;

    public TrainDriver(Station station, Task task, int platform) {
        id = instanceCounter;
        instanceCounter++;

        Runnable action;
        switch (task) {
            case ARRIVE :
                action = () -> {
                    station.arriveOn(platform, id);
                    System.out
                            .println("[" + id + "] has arrived on " + platform);
                };
                break;
            case DEPART :
                action = () -> {
                    station.departFrom(platform, id);
                    System.out.println("[" + id + "] has left " + platform);
                };
                break;
            default :
                action = () -> System.err.println("How could this happen?");
        }

        new Thread(action).start();
    }

}

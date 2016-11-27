/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 27.11.2016 
 * Aufgabe: Praktikum X
 */

package aufgabenblatt03;

/**
 * Wraps the Data Associated with a TrainEvent
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 27.11.2016
 */
public class TrainEvent {
    public enum Action{
        ARRIVE, DEPART, WAIT
    }
    
    private final int id;
    private final int platform;
    private final Action action;
    
    public TrainEvent(int id, int platform, Action action) {
        this.id = id;
        this.platform = platform;
        this.action = action;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the platform
     */
    public int getPlatform() {
        return platform;
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }
}

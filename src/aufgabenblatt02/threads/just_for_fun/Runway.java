/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */

package aufgabenblatt02.threads.just_for_fun;

/**
 *
 *
 * @author Moritz Höwer
 * @version 1.0 - 09.11.2016
 */
public class Runway {

    private final String name;
    private boolean blocked;
    private Plane plane;

    public Runway(String name) {
        this.name = name;
        blocked = false;
        plane = null;
    }

    public boolean isAvailiable() {
        return !blocked;
    }

    public void block(Plane plane) {
        blocked = true;
        this.plane = plane;
    }

    public boolean isBlockedBy(Plane plane) {
        return blocked && this.plane == plane;
    }

    public void unblock() {
        plane = null;
        blocked = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }
}

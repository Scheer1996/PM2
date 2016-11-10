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
 * Representation of a Runway that can be assigned to planes
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 09.11.2016
 */
public class Runway {

	/**
	 * the name of the Runway
	 */
	private final String name;

	/**
	 * the plane that is assigned to this runway
	 */
	private Plane plane;

	public Runway(String name) {
		this.name = name;
		plane = null;
	}

	/**
	 * whether this runway is availiable => no plane is assigned
	 * 
	 * @return true if no plane is blocking it
	 */
	public boolean isAvailiable() {
		return plane == null;
	}

	/**
	 * Assign the runway to a plane
	 * 
	 * @param plane the plane to assgin
	 */
	public void assignTo(Plane plane) {
		this.plane = plane;
	}

	/**
	 * Check whether the runway is assigned to the specific plane
	 * 
	 * @param plane the plane to check
	 * @return true if plane is assigned to this runway
	 */
	public boolean isAssignedTo(Plane plane) {
		return this.plane == plane;
	}

	/**
	 * free up the runway so it can be used by other planes
	 */
	public void freeRunway() {
		plane = null;
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

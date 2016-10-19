/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.09.2016 
 * Aufgabe: Praktikum 1
 */
package aufgabenblatt01.wiederholung;

/**
 * Application for Aufgabe 2
 *
 * @author Philip Scheer, Moritz Höwer
 * @version 2.0 - 19.10.2016
 */
public class Application {

    /**
     * program entry point
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        Student moritz = new Student("Moritz", "Höwer", 2258108);
        Student philip = new Student("Philip", "Scheer", 2297475);

        System.out.println(moritz + "\n" + philip);
        System.out.println("moritz equals philip: " + moritz.equals(philip));
        System.out.println(
                "moritz compares to philip: " + moritz.compareTo(philip));
        System.out.println("moritz compares name to philip: "
                + Student.NAME_COMPARATOR.compare(moritz, philip));
    }

}

/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.09.2016 
 * Aufgabe: Aufgabenblatt 1 Aufgabe 1
 */
package aufgabenblatt01.wiederholung;

public class Application {

    public static void main(String[] args) {

        Student s1 = new Student("Hans", "Peter", 20);
        Student s2 = new Student("Günther", "Maurer", 22);

        System.out.println(s1.equals(s2));
    }

}

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
 * Representation of an Examination Credit
 *
 *
 * @author Philip Scheer, Moritz Höwer
 * @version 2.0 - 16.10.2016
 */
public class ExaminationCredit {
    /**
     * the name of the exam
     */
    private final String exam;

    /**
     * the grade that was achieved in the exam
     */
    private final int points;

    public ExaminationCredit(String exam, int points) {
        this.exam = exam;
        this.points = points;
    }

    /**
     * @return the exam
     */
    public String getExam() {
        return exam;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

}

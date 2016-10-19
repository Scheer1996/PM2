/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.09.2016 
 * Aufgabe: Praktikum 1
 */
package aufgabenblatt01.wiederholung;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Simple representation of a HAW Student with name, exam results and
 * matriculation number
 *
 * @author Philip Scheer, Moritz Höwer
 * @version 2.0 - 16.10.2016
 */
public class Student implements Comparable<Student> {
    /**
     * a Comparator that compares students by name
     */
    public static final Comparator<Student> NAME_COMPARATOR = Comparator
            .comparing(Student::getFullName);

    /**
     * the first name
     */
    private String firstName;

    /**
     * the last name
     */
    private String lastName;

    /**
     * the matriculation number
     */
    private int matriculationNumber;

    /**
     * list of examination credits
     */
    private List<ExaminationCredit> examinationCredits;

    public Student(String firstName, String lastName, int matriculationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.matriculationNumber = matriculationNumber;
        examinationCredits = new ArrayList<>();
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the matriculationNumber
     */
    public int getMatriculationNumber() {
        return matriculationNumber;
    }

    /**
     * @return the examinationCredits
     */
    public List<ExaminationCredit> getExaminationCredits() {
        return examinationCredits;
    }
    
    /**
     * Adds a credit to this students examination credits
     * 
     * @param credit the credit to add
     */
    public void addExaminationCredit(ExaminationCredit credit){
        examinationCredits.add(credit);
    }

    /**
     * Helper for the Name Comparator
     * 
     * @return "lastName, firstName"
     */
    private String getFullName() {
        return lastName + ", " + firstName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) obj;
        return (matriculationNumber == otherStudent.matriculationNumber);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return matriculationNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Student otherStudent) {
        return new Integer(getMatriculationNumber())
                .compareTo(otherStudent.getMatriculationNumber());
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return getFullName() + " (" + getMatriculationNumber() + ")";
    }
}

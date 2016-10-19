/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 5.10.2016
 * Aufgabe: Praktikum 1
 */
package aufgabenblatt01.test;

import static org.junit.Assert.*;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import aufgabenblatt01.wiederholung.ExaminationCredit;
import aufgabenblatt01.wiederholung.Student;

/**
 * JUnit test for the non trivial functionality of Student
 * 
 * @author Philip Scheer, Moritz Höwer
 * @version 2.0 - 16.10.16
 */
public class TestStudent {

    /**
     * Test Method for {@link Student#addExaminationCredit(ExaminationCredit)}
     */
    @Test
    public void testAddExaminationCredit() {
        Student student = new Student("Max", "Mustermann", 123456);
        ExaminationCredit c1 = new ExaminationCredit("Exam1", 15);
        ExaminationCredit c2 = new ExaminationCredit("Exam2", 13);

        assertThat("Student shouldn't have any ExaminationCredits",
                student.getExaminationCredits().isEmpty(), is(true));

        student.addExaminationCredit(c1);
        assertThat("Wrong number of ExaminationCredits",
                student.getExaminationCredits().size(), is(1));
        assertThat("Doesn't have correct ExaminationCredit",
                student.getExaminationCredits(), hasItem(c1));
        
        student.addExaminationCredit(c2);
        assertThat("Wrong number of ExaminationCredits",
                student.getExaminationCredits().size(), is(2));
        assertThat("Doesn't have correct ExaminationCredit",
                student.getExaminationCredits(), hasItems(c1, c2));
    }

    /**
     * Test Method for {@link Student#equals(Object)}
     */
    @Test
    public void testEqualsObject() {
        Student s1 = new Student("Max", "Mustermann", 123456);
        Student s2 = new Student("Maria", "Musterfrau", 654321);
        Student s3 = new Student("Fake Max", "Mustermann", 123456);

        assertThat("s1 should not equal s2", s1, not(equalTo(s2)));
        assertThat("s1 should not equal null", s1, not(equalTo(nullValue())));
        assertThat("s1 should equal s3", s1, equalTo(s3));
        assertThat("s1 should equal itself", s1, equalTo(s1));
    }

    /**
     * Test Method for {@link Student#compareTo(Student)}
     */
    @Test
    public void testCompareToStudent() {
        Student s1 = new Student("Max", "Mustermann", 123456);
        Student s2 = new Student("Maria", "Musterfrau", 654321);
        Student s3 = new Student("Fake Max", "Mustermann", 123456);

        assertThat("s1 should be smaller than s2", s1.compareTo(s2), is(-1));
        assertThat("s2 should be bigger than s1", s2.compareTo(s1), is(1));
        assertThat("s1 should be equal to s3", s1.compareTo(s3), is(0));
        assertThat("s1 should be equal to itself", s1.compareTo(s1), is(0));
        try {
            s1.compareTo(null);
            fail("s1 should throw exception when compared to null");
        } catch (NullPointerException e) {
            // this is the expected behavior
        }
    }

    /**
     * Test Method for {@link Student#NAME_COMPARATOR}
     */
    @Test
    public void testComparatorName() {
        Student s1 = new Student("Max", "Mustermann", 123456);
        Student s2 = new Student("Maria", "Musterfrau", 654321);
        Student s3 = new Student("Max", "Mustermann", 455123);

        Comparator<Student> comp = Student.NAME_COMPARATOR;
        assertThat("s1 should be bigger than s2", comp.compare(s1, s2) > 0,
                is(true));
        assertThat("s2 should be smaller than s1", comp.compare(s2, s1) < 0,
                is(true));
        assertThat("s1 should be equal to s3", comp.compare(s1, s3), is(0));
        assertThat("s1 should be equal to itself", comp.compare(s1, s1), is(0));
    }

}

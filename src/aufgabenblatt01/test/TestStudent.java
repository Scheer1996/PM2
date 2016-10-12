/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 * 	        Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 5.10.2016
 * Aufgabe: Aufgabenblatt 1 Aufgabe 1
 */
package aufgabenblatt01.test;

import static org.junit.Assert.*;

import org.junit.Test;

import aufgabenblatt01.wiederholung.Student;

/**
 * JUnit Test for the functionality of Student
 */
public class TestStudent {

	/**
	 * Test method for getters
	 */
	@Test
	public void testStudentGetter() {
		Student s = new Student("Max", "Mustermann", 123456);
		
		assertEquals("Wrong value", "Max", s.getFirstName());
		assertEquals("Wrong value", "Mustermann", s.getLastName());
		assertEquals("Wrong value", 123456, s.getMatriculationNumber());
	}
	
	/**
	 * Test Method for equals
	 */
	@Test
	public void testStudentEquals() {
		Student s1 = new Student("Max", "Mustermann", 123456);
		Student s2 = new Student("Maria", "Musterfrau", 654321);
		Student s3 = new Student("Fake Max", "Mustermann", 123456);
		
		assertEquals("Equals() not working properly", false, s1.equals(s2));
		assertEquals("Equals() not working properly", true, s1.equals(s3));
	}
	
	
}

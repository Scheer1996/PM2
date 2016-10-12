package aufgabenblatt01.wiederholung;

import java.util.Comparator;

/**
 * Comparator for Student
 */
public class StudentComparator implements Comparator<Student> {
	
	@Override
	public int compare(Student s1, Student s2) {
		return s1.compareTo(s2);
	}
}

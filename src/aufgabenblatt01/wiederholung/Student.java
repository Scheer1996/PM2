package aufgabenblatt01.wiederholung;

import java.util.LinkedList;
import java.util.List;

public class Student implements Comparable<Student> {

	private String firstName, lastName;
	private int matriculationNumber;
	private List<ExaminationCredit> examinationCredits = new LinkedList<ExaminationCredit>();
	
	public Student(String firstName, String lastName, int matriculationNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.matriculationNumber = matriculationNumber;
	}
	
	/**
	 * Getter
	 */
	public int getMatriculationNumber() {
		return matriculationNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Student)) {
			return false;
		}
		
		Student otherStudent = (Student) obj;
		return (matriculationNumber == otherStudent.matriculationNumber);
	}
	
	@Override
	public int hashCode() {
		return matriculationNumber;
	}

	@Override
	public int compareTo(Student otherStudent) {
		if(getMatriculationNumber() > otherStudent.getMatriculationNumber()) {
			return 1;
		} else if(getMatriculationNumber() < otherStudent.getMatriculationNumber()) {
			return -1;
		} else {
			return 0;
		}
	}
}

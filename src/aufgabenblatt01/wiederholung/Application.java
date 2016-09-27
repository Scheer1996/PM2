package aufgabenblatt01.wiederholung;

public class Application {

	public static void main(String[] args) {
		
		
		
		Student s1 = new Student("Hans", "Peter", 20);
		Student s2 = new Student("Günther", "Maurer", 22);

		System.out.println(s1.equals(s2));
	}

}

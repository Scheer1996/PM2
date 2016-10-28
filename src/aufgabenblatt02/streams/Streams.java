package aufgabenblatt02.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

	
	public static void main(String[] args) {
		List<String> stringArray = Arrays.asList("Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" );
		
		
		Stream<String> wordsStream = stringArray.stream();
		
		// removes Null Elements
		wordsStream = wordsStream.filter(obj -> Objects.nonNull(obj));
		
		// removes Spaces before an after Strings
		wordsStream = wordsStream.map(String::trim);
		
		// all to Upper Case
		wordsStream = wordsStream.map(String::toUpperCase);

		// umlaute ersetzen
		wordsStream = wordsStream.map(string -> string.replace("Ä", "AE"))
								.map(string -> string.replace("Ö", "OE"))
								.map(string -> string.replace("Ü", "UE"))
								.map(string -> string.replace("ß", "SS"));
		
		// shorts the String to 8 Chars
		//wordsStream = wordsStream.map(string -> string.substring(8, string.length()));
		
		
		List<String> newStringArray = wordsStream.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(newStringArray);
		
		
		
		
		/**
		 * Kürzeres Variante
		 */
		Stream<String> wordsStreamFast = stringArray.stream()
						.filter(obj -> Objects.nonNull(obj))
						.map(String::trim)
						.map(String::toUpperCase)
						.map(string -> string.replace("Ä", "AE"))
						.map(string -> string.replace("Ö", "OE"))
						.map(string -> string.replace("Ü", "UE"))
						.map(string -> string.replace("ß", "SS"));
		
		// shorts the String to 8 Chars
		//wordsStream = wordsStream.map(string -> string.substring(8, string.length()));
		
		
		List<String> newStringArrayFast = wordsStream.collect(Collectors.toCollection(ArrayList::new));
		
		
	}
	
}

/*
 * Praktikum PM2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Praktikum 2
 */
package aufgabenblatt02.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demo class to showcase that streams are cool
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 09.11.2016
 */
public class Streams {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Eingabe ", "Äußeres ", null,
                "Strassen-Feger", " ein Haus", "abc", "Ölrückstoßdämpfer");

        Stream<String> wordsStream = stringList.stream();

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
        wordsStream = wordsStream.map(
                string -> string.substring(0, Math.min(8, string.length())));

        List<String> newStringArray = wordsStream
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(newStringArray);

        /**
         * Kürzeres Variante
         */
        Stream<String> wordsStreamShorter = stringList.stream()
                .filter(obj -> Objects.nonNull(obj)).map(String::trim)
                .map(String::toUpperCase)
                .map(string -> string.replace("Ä", "AE").replace("Ö", "OE")
                        .replace("Ü", "UE").replace("ß", "SS"))
                .map(string -> string.substring(0,
                        Math.min(8, string.length())));

        List<String> newStringListShorter = wordsStreamShorter
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(newStringListShorter);

    }

}

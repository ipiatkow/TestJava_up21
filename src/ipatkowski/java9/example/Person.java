package ipatkowski.java9.example;

import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;

//------- JAVA 16 --------
// Records
public record Person(String imie, String nazwisko, int wiek) {
    private static String UNKNOWN_NAME = "Unknown";
    public static Person unknownPerson(String imie) {
        return new Person(imie, UNKNOWN_NAME, 0);
    }

    public void innaMetoda(String tekst) {
        System.out.println(tekst);
    }
}

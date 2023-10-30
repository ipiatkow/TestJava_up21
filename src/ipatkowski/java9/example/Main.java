package ipatkowski.java9.example;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main implements Java9TestInterface{
    public static void main(String[] args) {

        //------- JAVA 9 --------

        //1. Factory methods w kolekcjach
        System.out.println("-------------------Factory methods w kolekcjach-----------------");
        List<String> listOfString = List.of("Janek", "Kasia", "Basia");
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        Set<? extends Serializable> setOfObjects = Set.of(1, 2, "Piotr", 7);
        Map<? extends Serializable, String> stringMap = Map.of(1, "Janek", 2, "Piotrek", "dodatkowy", "Krzysiek");
        Map<Integer, String> integerStringMap = Map.of(1, "Ala", 2, "Basia", 3, "Kasia");
        System.out.println(listOfString);
        System.out.println(integers);
        System.out.println(setOfObjects);
        System.out.println(stringMap);
        System.out.println(integerStringMap);

        //2. Metody prywatne w interfejsach
        System.out.println("-------------------Metody prywatne w interfejsach-----------------");
        Main exampleObjectJava9 = new Main();
        exampleObjectJava9.method1();

        //3. Ulepszenie klasy Optional
        System.out.println("-------------------Ulepszenie klasy Optional-----------------");
        Optional<String> stringOpt = Optional.ofNullable("Ala ma kota");

        stringOpt.ifPresent(System.out::println);
        Optional<String> stringOptNull = Optional.of("To jest Ala i kot");
        stringOptNull.ifPresentOrElse(Main::presentAlaAndCat,() -> System.out.println("Nie ma wartości"));

        Optional<String> alaI2Koty = Optional.of("To jest Ala i 2 koty");
        Optional<String> emptyOptional = Optional.empty();
        Optional<String> nullOptional = Optional.ofNullable(null);

        String stringOptNull2 = alaI2Koty.orElse("Ani kota ani Ali");
        String stringOptNull3 = emptyOptional.orElse("Ani kota ani Ali");
        String stringOptNull4 = emptyOptional.orElseGet(Main::returnText);
        System.out.println(stringOptNull2);
        System.out.println(stringOptNull3);
        System.out.println(stringOptNull4);

        Optional<String> firstOptional = Optional.of("Pierwszy optional");
        Optional<String> secondOptional = Optional.of("Drugi optional");
        firstOptional = Optional.ofNullable(null);

        System.out.println((firstOptional.or(()->secondOptional)).get());

        Optional<String> thirdOptional = secondOptional.filter( s -> s.contains("Drugi"));
        System.out.println("W trzecim jest : " + thirdOptional.get());

        Person janek = new Person("Jan", "Kowalski", 44);

        Optional<Person> janekOptional = Optional.of(janek);
        Optional<String> janekImie = janekOptional.map(Person::getImie);
        Optional<String> janekNazwisko = janekOptional.flatMap(person -> Optional.of(person.getNazwisko()));
        System.out.println(janekImie.get());
        System.out.println(janekNazwisko.get());

        //System.out.println(firstOptional.orElseThrow());
        //System.out.println(firstOptional.orElseThrow(()-> new RuntimeException("Ale jaja")));



        //String stringOptNull4 = emptyOptional.orElseThrow(() -> new RuntimeException("Brak Ali i kotów"));

        //4. Ulepszenia stream api
        System.out.println("-------------------Ulepszenia stream api-----------------");
        IntStream.of(1,2,3,4,5,6,7).takeWhile(i->i<5).forEach(System.out::print);
        System.out.println();
        Stream.of(1,2,3,4,5,6,7).dropWhile(i->i<4).forEach(System.out::print);
        System.out.println();
        Stream.iterate(4, i->i<120, i->3*i).forEach(System.out::print);
        Stream.iterate("a", i->!i.equals("aaaaaaaa"), i->i+"a").forEach(System.out::println);


        //------- JAVA 10 --------
        // Nowe metody fabryczne dla kolekcji List.copyOf() Set.copyOf() Map.copyOf()

        List<String> imiona = List.of("Ala", "Kasia", "Piotr", "Ala");
        List<String> nowaListaImiona = List.copyOf(imiona);
        Set<String> setImiona = Set.copyOf(imiona);
        System.out.println("Lista imiona " + nowaListaImiona);
        System.out.println("Set imiona " + setImiona);

        // Nowe metody dla Collectors → toUnmodifiableList/Set/Map

        Collector<Object, ?, List<Object>> objectListCollector = Collectors.toUnmodifiableList();
        List<Integer> collect = IntStream.range(1, 10).boxed().collect(Collectors.toUnmodifiableList());
        System.out.println("UnmodifiableList : " + collect);

        Stream.of(1,7,1,2,6,7,2,1).collect(Collectors.toUnmodifiableSet()).forEach(System.out::print);

        //------- JAVA 11 --------

    }

    @Override
    public void method1() {
        defaultMethod();
    }

    private static void presentAlaAndCat(String text) {
        System.out.println(text);
    }

    private static String returnText() {
        return "Ala i kot mają się dobrze w metodzie";
    }
}

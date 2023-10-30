package ipatkowski.java9.example;

public interface Java9TestInterface {
    private void privateMethod() {
        System.out.println("Prywatna metoda z interface");
    }

    default void defaultMethod() {
        System.out.println("Difoltowa metoda z interface");
        privateMethod();
    }

    void method1();
}

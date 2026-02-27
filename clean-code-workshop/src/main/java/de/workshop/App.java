package de.workshop;

public class App {
    public static String getGreeting() {
        return "Hallo Welt";
    }

    public static void main(String[] args) {
        ParkOMatApp parkOMat = new ParkOMatApp();
        parkOMat.run();
    }

}

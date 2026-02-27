package de.workshop;

import java.util.Scanner;

import de.workshop.parkomat.Input;
import de.workshop.parkomat.Output;
import de.workshop.parkomat.ParkOMatApp;
import de.workshop.parkomat.Parser;
import de.workshop.parkomat.Time;

public class App {
    public static String getGreeting() {
        return "Hallo Welt";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Output consoleOutput = new Output();
        Input consoleInput = new Input(scanner, consoleOutput);
        Parser stringParser = new Parser(consoleOutput);
        Time time = new Time();
        ParkOMatApp parkOMat = new ParkOMatApp(consoleInput, consoleOutput, stringParser, time);
        parkOMat.run();
        scanner.close();
    }

}

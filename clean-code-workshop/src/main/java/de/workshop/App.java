package de.workshop;

import java.util.Scanner;

import de.workshop.parkomat.Clock;
import de.workshop.parkomat.ConsoleInput;
import de.workshop.parkomat.Output;
import de.workshop.parkomat.ConsoleOutput;
import de.workshop.parkomat.Input;
import de.workshop.parkomat.ParkOMatApp;
import de.workshop.parkomat.Parser;
import de.workshop.parkomat.ConsoleParser;
import de.workshop.parkomat.SystemClock;

public class App {
    public static String getGreeting() {
        return "Hallo Welt";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Output consoleOutput = new ConsoleOutput();
        Input consoleInput = new ConsoleInput(scanner, consoleOutput);
        Parser consoleParser = new ConsoleParser(consoleOutput);
        Clock clock = new SystemClock();
        ParkOMatApp parkOMat = new ParkOMatApp(consoleInput, consoleOutput, consoleParser, clock);
        parkOMat.run();
        scanner.close();
    }

}

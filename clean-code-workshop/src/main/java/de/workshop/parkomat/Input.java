package de.workshop.parkomat;

import java.util.Scanner;

public class Input {

    private Scanner scanner;
    private Output output;

    public Input(Scanner scanner, Output output) {
        this.scanner = scanner;
        this.output = output;
    }

    public String readCommandLine() {
        output.printCommandPrompt();

        if (!scanner.hasNextLine()) {
            return null;
        }

        String line = scanner.nextLine();
        if (line == null || line.trim().isEmpty()) {
            return "";
        }

        return line.trim();
    }

}

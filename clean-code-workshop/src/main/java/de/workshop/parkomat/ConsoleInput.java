package de.workshop.parkomat;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner;
    private Output output;

    public ConsoleInput(Scanner scanner, Output output) {
        this.scanner = scanner;
        this.output = output;
    }

    @Override
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

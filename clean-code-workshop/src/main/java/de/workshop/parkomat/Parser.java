package de.workshop.parkomat;

import java.util.Locale;

public class Parser {

    private Output output;

    public Parser(Output output) {
        this.output = output;
    }

    public String extractCommand(String[] parts) {
        return parts[0].toUpperCase(Locale.ROOT);
    }

    public String extractPlate(String[] parts) {
        if (parts.length < 2) {
            output.printNoPlateError();
            return null;
        }
        return parts[1];
    }

    public String extractTicketId(String[] parts) {
        if (parts.length < 2) {
            output.printNoIdError();
            return null;
        }
        return parts[1];
    }

}

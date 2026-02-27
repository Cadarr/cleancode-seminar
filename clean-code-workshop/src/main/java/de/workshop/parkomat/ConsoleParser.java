package de.workshop.parkomat;

import java.util.Locale;

public class ConsoleParser implements Parser {

    private Output output;

    public ConsoleParser(Output output) {
        this.output = output;
    }

    @Override
    public String extractCommand(String[] parts) {
        return parts[0].toUpperCase(Locale.ROOT);
    }

    @Override
    public String extractPlate(String[] parts) {
        if (parts.length < 2) {
            output.printNoPlateError();
            return null;
        }
        return parts[1];
    }

    @Override
    public String extractTicketId(String[] parts) {
        if (parts.length < 2) {
            output.printNoIdError();
            return null;
        }
        return parts[1];
    }

}

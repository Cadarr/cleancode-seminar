package de.workshop.parkomat;

import java.util.HashMap;
import java.util.Map;

public class ParkOMatApp {

    private Input input;
    private Output output;
    private Parser parser;

    private Time time;

    private Map<String, ParkingTicket> ticketsById = new HashMap<>();
    private ParkingReport report = new ParkingReport();
    private UseCases useCases = new UseCases(time, ticketsById, output);

    public ParkOMatApp(Input input, Output output, Parser parser, Time time) {
        this.input = input;
        this.output = output;
        this.parser = parser;
        this.time = time;
    }

    public void run() {
        output.printWelcomeMessage();

        while (true) {
            String line = input.readCommandLine();
            if (line == null) {
                break;
            }

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.trim().split("\\s+");
            String command = parser.extractCommand(parts);

            if (command.equals("QUIT") || command.equals("ENDE")) {
                output.printQuitMessage();
                break;
            } else if (command.equals("HELP")) {
                output.printHelpMessage();
            } else if (command.equals("ENTER")) {
                String plate = parser.extractPlate(parts);
                if (plate == null) {
                    continue;
                }

                useCases.registerEntry(report, plate);
            } else if (command.equals("EXIT")) {
                String ticketId = parser.extractTicketId(parts);

                if (ticketId == null) {
                    continue;
                }

                if (!ticketsById.containsKey(ticketId)) {
                    output.printNoTicketError();
                    continue;
                }

                ParkingTicket ticket = ticketsById.get(ticketId);

                if (ticket.isClosed()) {
                    output.printTicketAlreadyClosedError(ticket);
                    continue;
                }

                useCases.registerExit(report, ticketId, ticket);
            } else if (command.equals("REPORT")) {
                output.printReport(report);
            } else {
                output.printUnknownCommandError();
            }
        }

    }

}

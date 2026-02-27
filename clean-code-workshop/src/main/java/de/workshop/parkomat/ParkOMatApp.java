package de.workshop.parkomat;

import java.util.HashMap;
import java.util.Map;

public class ParkOMatApp {

    private Input input;
    private Output output;
    private Parser parser;

    private Map<String, ParkingTicket> ticketsById = new HashMap<>();
    private ParkingReport report = new ParkingReport();
    private UseCases useCases;

    private Map<String, CommandHandler> handlers = new HashMap<>();
    private CommandHandler unknownHandler = parts -> {
        output.printUnknownCommandError();
        return LoopAction.CONTINUE;
    };

    enum LoopAction {
        CONTINUE,
        QUIT
    }

    public interface CommandHandler {
        LoopAction handle(String[] parts);
    }

    public ParkOMatApp(Input input, Output output, Parser parser, Clock clock) {
        this.input = input;
        this.output = output;
        this.parser = parser;
        useCases = new UseCases(clock, ticketsById, output);
        setupCommandHandlers();
    }

    private void setupCommandHandlers() {
        handlers.put("HELP", parts -> {
            output.printHelpMessage();
            return LoopAction.CONTINUE;
        });

        handlers.put("REPORT", parts -> {
            output.printReport(report);
            return LoopAction.CONTINUE;
        });

        handlers.put("QUIT", parts -> {
            output.printQuitMessage();
            return LoopAction.QUIT;
        });

        handlers.put("ENDE", handlers.get("QUIT"));

        handlers.put("ENTER", parts -> {
            String plate = parser.extractPlate(parts);
            if (plate == null)
                return LoopAction.CONTINUE;

            useCases.registerEntry(report, plate);
            return LoopAction.CONTINUE;
        });

        handlers.put("EXIT", parts -> {
            String ticketId = parser.extractTicketId(parts);
            if (ticketId == null)
                return LoopAction.CONTINUE;

            if (!ticketsById.containsKey(ticketId)) {
                output.printNoTicketError();
                return LoopAction.CONTINUE;
            }

            ParkingTicket ticket = ticketsById.get(ticketId);

            if (ticket.isClosed()) {
                output.printTicketAlreadyClosedError(ticket);
                return LoopAction.CONTINUE;
            }

            useCases.registerExit(report, ticketId, ticket);
            return LoopAction.CONTINUE;
        });
    }

    public void run() {
        output.printWelcomeMessage();

        String line;
        while ((line = input.readCommandLine()) != null) {

            if (line.isEmpty())
                continue;

            String[] parts = line.split("\\s+");
            String command = parser.extractCommand(parts);

            CommandHandler handler = handlers.getOrDefault(command, unknownHandler);

            LoopAction action = handler.handle(parts);

            if (action == LoopAction.QUIT) {
                break;
            }
        }
    }

}

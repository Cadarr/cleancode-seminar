package de.workshop;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ParkOMatApp {
    public class ParkingTicket {
        private String plate;
        private LocalDateTime enterTime;
        private boolean closed;
        private int fee;

        public ParkingTicket(String plate, LocalDateTime enterTime) {
            this.plate = plate;
            this.enterTime = enterTime;
        }

        public String getPlate() {
            return plate;
        }

        public LocalDateTime getEnterTime() {
            return enterTime;
        }

        public boolean isClosed() {
            return closed;
        }

        public void closeTicket() {
            this.closed = true;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }
    }

    public class ParkingReport {
        private int totalTickets = 0;
        private int finishedTickets = 0;
        private int revenue = 0;

        public int getTotalTickets() {
            return totalTickets;
        }

        public void recordTicketCreation() {
            this.totalTickets++;
        }

        public int getFinishedTickets() {
            return finishedTickets;
        }

        public void recordClosedTicket() {
            this.finishedTickets++;
        }

        public void recordRevenue(int amount) {
            this.revenue = this.revenue + amount;
        }

        public int getRevenue() {
            return revenue;
        }
    }

    private static final long MINUTES_PER_HOUR = 60;

    private static final int BASE_HOURS_INCLUDED = 1;
    private static final int BASE_FEE = 2;
    private static final int HOURLY_FEE_AFTER_BASE = 3;

    private static final int MAX_DAILY_FEE = 20;

    private Scanner scanner = new Scanner(System.in);
    private Map<String, ParkingTicket> ticketsById = new HashMap<>();
    private ParkingReport report = new ParkingReport();

    public void run() {
        printWelcomeMessage();

        while (true) {
            String line = readCommandLine();
            if (line == null) {
                break;
            }

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.trim().split("\\s+");
            String command = extractCommand(parts);

            if (command.equals("QUIT") || command.equals("ENDE")) {
                printQuitMessage();
                break;
            } else if (command.equals("HELP")) {
                printHelpMessage();
            } else if (command.equals("ENTER")) {
                String plate = extractPlate(parts);
                if (plate == null) {
                    continue;
                }

                registerEntry(report, plate);
            } else if (command.equals("EXIT")) {
                String ticketId = extractTicketId(parts);

                if (ticketId == null) {
                    continue;
                }

                if (!ticketsById.containsKey(ticketId)) {
                    printNoTicketError();
                    continue;
                }

                ParkingTicket ticket = ticketsById.get(ticketId);

                if (ticket.isClosed()) {
                    printTicketAlreadyClosedError(ticket);
                    continue;
                }

                registerExit(report, ticketId, ticket);
            } else if (command.equals("REPORT")) {
                printReport(report);
            } else {
                printUnknownCommandError();
            }
        }

        scanner.close();
    }

    // Parser
    private String extractCommand(String[] parts) {
        return parts[0].toUpperCase(Locale.ROOT);
    }

    private String extractPlate(String[] parts) {
        if (parts.length < 2) {
            printNoPlateError();
            return null;
        }
        return parts[1];
    }

    private String extractTicketId(String[] parts) {
        if (parts.length < 2) {
            printNoIdError();
            return null;
        }
        return parts[1];
    }

    // Input

    private String readCommandLine() {
        printCommandPrompt();

        if (!scanner.hasNextLine()) {
            return null;
        }

        String line = scanner.nextLine();
        if (line == null || line.trim().isEmpty()) {
            return "";
        }

        return line.trim();
    }

    // Output
    private void printCommandPrompt() {
        System.out.print("> ");
    }

    private void printWelcomeMessage() {
        System.out
                .println("Park-O-Mat gestartet. Befehle: ENTER <plate>, EXIT <ticketId>, REPORT, HELP, QUIT/ENDE");
    }

    private void printQuitMessage() {
        System.out.println("Programm beendet.");
    }

    private void printHelpMessage() {
        System.out.println("ENTER <plate>  - Neues Ticket erzeugen");
        System.out.println("EXIT <ticketId> - Ticket abrechnen");
        System.out.println("REPORT         - Statistik anzeigen");
        System.out.println("QUIT oder ENDE - Programm beenden");
    }

    private void printNoPlateError() {
        System.out.println("Fehler: Bitte Kennzeichen angeben. Beispiel: ENTER B-AB123");
    }

    private void printEnterMessage(String ticketId, LocalDateTime now) {
        System.out.println("Ticket erstellt: " + ticketId);
        System.out.println("Einfahrt: " + now);
    }

    private void printNoIdError() {
        System.out.println("Fehler: Bitte Ticket-ID angeben. Beispiel: EXIT <ticketId>");
    }

    private void printNoTicketError() {
        System.out.println("Fehler: Ticket nicht gefunden.");
    }

    private void printTicketAlreadyClosedError(ParkingTicket ticket) {
        System.out.println("Fehler: Ticket ist bereits abgeschlossen.");
        System.out.println("Bereits bezahlt: " + ticket.getFee() + " €");
    }

    private void printExitMessage(String ticketId, ParkingTicket ticket, long minutes, int fee) {
        System.out.println("Ticket: " + ticketId);
        System.out.println("Kennzeichen: " + ticket.plate);
        System.out.println("Parkdauer: " + minutes + " Minuten");
        System.out.println("Gebühr: " + fee + " €");
    }

    private void printReport(ParkingReport report) {
        System.out.println("----- REPORT -----");
        System.out.println("Ausgegebene Tickets: " + report.getTotalTickets());
        System.out.println("Abgeschlossene Parkvorgaenge: " + report.getFinishedTickets());
        System.out.println("Gesamtumsatz: " + report.getRevenue() + " €");
        System.out.println("------------------");
    }

    private void printUnknownCommandError() {
        System.out.println("Unbekannter Befehl. Mit HELP bekommst du Hilfe.");
    }

    // Businesslogik

    private void registerExit(ParkingReport report, String ticketId, ParkingTicket ticket) {
        LocalDateTime enterTime = ticket.getEnterTime();
        LocalDateTime exitTime = now();

        long minutes = Duration.between(enterTime, exitTime).toMinutes();
        if (minutes < 0) {
            minutes = 0;
        }

        int startedHours = (int) Math.ceil(minutes / MINUTES_PER_HOUR);

        int fee;
        if (startedHours <= BASE_HOURS_INCLUDED) {
            fee = BASE_FEE;
        } else {
            fee = BASE_FEE + (startedHours - BASE_HOURS_INCLUDED) * HOURLY_FEE_AFTER_BASE;
        }

        if (fee > MAX_DAILY_FEE) {
            fee = MAX_DAILY_FEE;
        }

        ticket.setFee(fee);
        ticket.closeTicket();

        report.recordClosedTicket();
        report.recordRevenue(fee);

        printExitMessage(ticketId, ticket, minutes, fee);
    }

    private void registerEntry(ParkingReport report, String plate) {
        String ticketId = UUID.randomUUID().toString();
        LocalDateTime now = now();

        ParkingTicket newTicket = new ParkingTicket(plate, now);
        ticketsById.put(ticketId, newTicket);

        report.recordTicketCreation();

        printEnterMessage(ticketId, now);
    }

    // Side effects
    protected LocalDateTime now() {
        return LocalDateTime.now();
    }

}

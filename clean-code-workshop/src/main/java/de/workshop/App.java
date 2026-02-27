package de.workshop;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static String getGreeting() {
        return "Hallo Welt";
    }

    public class Ticket {
        private String plate;
        private LocalDateTime enterTime;
        private boolean closed;
        private int price;

        public Ticket(String plate, LocalDateTime enterTime) {
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public class Report {
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

    private Scanner scanner = new Scanner(System.in);
    private Map<String, Ticket> tickets = new HashMap<>();

    public void runParkOMat() {

        Report report = new Report();

        printWelcomeMessage();

        while (true) {
            String line = readNextCommandLine();
            if (line == null) {
                break;
            }

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.trim().split("\\s+");
            String command = parts[0].toUpperCase(Locale.ROOT);

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

                if (!tickets.containsKey(ticketId)) {
                    printNoTicketError();
                    continue;
                }

                Ticket ticket = tickets.get(ticketId);

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

    private String readNextCommandLine() {
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

    private void printTicketAlreadyClosedError(Ticket ticket) {
        System.out.println("Fehler: Ticket ist bereits abgeschlossen.");
        System.out.println("Bereits bezahlt: " + ticket.getPrice() + " €");
    }

    private void printExitMessage(String ticketId, Ticket ticket, long minutes, int price) {
        System.out.println("Ticket: " + ticketId);
        System.out.println("Kennzeichen: " + ticket.plate);
        System.out.println("Parkdauer: " + minutes + " Minuten");
        System.out.println("Preis: " + price + " €");
    }

    private void printReport(Report report) {
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

    private void registerExit(Report report, String ticketId, Ticket ticket) {
        LocalDateTime enterTime = ticket.getEnterTime();
        LocalDateTime exitTime = LocalDateTime.now();

        long minutes = Duration.between(enterTime, exitTime).toMinutes();
        if (minutes < 0) {
            minutes = 0;
        }

        int startedHours = (int) Math.ceil(minutes / 60.0);

        int price;
        if (startedHours <= 1) {
            // bis 60 Minuten nur Grundgebühr
            price = 2;
        } else {
            price = 2 + (startedHours - 1) * 3;
        }

        if (price > 20) {
            price = 20;
        }

        ticket.setPrice(price);
        ticket.closeTicket();

        report.recordClosedTicket();
        report.recordRevenue(price);

        printExitMessage(ticketId, ticket, minutes, price);
    }

    private void registerEntry(Report report, String plate) {
        String ticketId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        Ticket newTicket = new Ticket(plate, now);
        tickets.put(ticketId, newTicket);

        report.recordTicketCreation();

        printEnterMessage(ticketId, now);
    }

    public static void main(String[] args) {
        App app = new App();
        app.runParkOMat();
    }

}

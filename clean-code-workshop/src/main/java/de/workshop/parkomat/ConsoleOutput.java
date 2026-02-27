package de.workshop.parkomat;

import java.time.LocalDateTime;

public class ConsoleOutput implements Output {

    @Override
    public void printCommandPrompt() {
        System.out.print("> ");
    }

    @Override
    public void printWelcomeMessage() {
        System.out
                .println("Park-O-Mat gestartet. Befehle: ENTER <plate>, EXIT <ticketId>, REPORT, HELP, QUIT/ENDE");
    }

    @Override
    public void printQuitMessage() {
        System.out.println("Programm beendet.");
    }

    @Override
    public void printHelpMessage() {
        System.out.println("ENTER <plate>  - Neues Ticket erzeugen");
        System.out.println("EXIT <ticketId> - Ticket abrechnen");
        System.out.println("REPORT         - Statistik anzeigen");
        System.out.println("QUIT oder ENDE - Programm beenden");
    }

    @Override
    public void printNoPlateError() {
        System.out.println("Fehler: Bitte Kennzeichen angeben. Beispiel: ENTER B-AB123");
    }

    @Override
    public void printEnterMessage(String ticketId, LocalDateTime now) {
        System.out.println("Ticket erstellt: " + ticketId);
        System.out.println("Einfahrt: " + now);
    }

    @Override
    public void printNoIdError() {
        System.out.println("Fehler: Bitte Ticket-ID angeben. Beispiel: EXIT <ticketId>");
    }

    @Override
    public void printNoTicketError() {
        System.out.println("Fehler: Ticket nicht gefunden.");
    }

    @Override
    public void printTicketAlreadyClosedError(ParkingTicket ticket) {
        System.out.println("Fehler: Ticket ist bereits abgeschlossen.");
        System.out.println("Bereits bezahlt: " + ticket.getFee() + " €");
    }

    @Override
    public void printExitMessage(String ticketId, ParkingTicket ticket, long minutes, int fee) {
        System.out.println("Ticket: " + ticketId);
        System.out.println("Kennzeichen: " + ticket.getPlate());
        System.out.println("Parkdauer: " + minutes + " Minuten");
        System.out.println("Gebühr: " + fee + " €");
    }

    @Override
    public void printReport(ParkingReport report) {
        System.out.println("----- REPORT -----");
        System.out.println("Ausgegebene Tickets: " + report.getTotalTickets());
        System.out.println("Abgeschlossene Parkvorgaenge: " + report.getFinishedTickets());
        System.out.println("Gesamtumsatz: " + report.getRevenue() + " €");
        System.out.println("------------------");
    }

    @Override
    public void printUnknownCommandError() {
        System.out.println("Unbekannter Befehl. Mit HELP bekommst du Hilfe.");
    }
}

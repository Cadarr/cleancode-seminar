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


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> plateByTicket = new HashMap<>();
        Map<String, LocalDateTime> enterTimeByTicket = new HashMap<>();
        Map<String, Boolean> closedByTicket = new HashMap<>();
        Map<String, Integer> priceByTicket = new HashMap<>();

        int totalTickets = 0;
        int finishedTickets = 0;
        double revenue = 0;

        System.out.println("Park-O-Mat gestartet. Befehle: ENTER <plate>, EXIT <ticketId>, REPORT, HELP, QUIT/ENDE");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine();
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.trim().split("\\s+");
            String command = parts[0].toUpperCase(Locale.ROOT);

            if (command.equals("QUIT") || command.equals("ENDE")) {
                System.out.println("Programm beendet.");
                break;
            } else if (command.equals("HELP")) {
                System.out.println("ENTER <plate>  - Neues Ticket erzeugen");
                System.out.println("EXIT <ticketId> - Ticket abrechnen");
                System.out.println("REPORT         - Statistik anzeigen");
                System.out.println("QUIT oder ENDE - Programm beenden");
            } else if (command.equals("ENTER")) {
                if (parts.length < 2) {
                    System.out.println("Fehler: Bitte Kennzeichen angeben. Beispiel: ENTER B-AB123");
                    continue;
                }

                String plate = parts[1];
                String ticketId = UUID.randomUUID().toString();
                LocalDateTime now = LocalDateTime.now();

                plateByTicket.put(ticketId, plate);
                enterTimeByTicket.put(ticketId, now);
                closedByTicket.put(ticketId, false);

                totalTickets++;

                System.out.println("Ticket erstellt: " + ticketId);
                System.out.println("Einfahrt: " + now);
            } else if (command.equals("EXIT")) {
                if (parts.length < 2) {
                    System.out.println("Fehler: Bitte Ticket-ID angeben. Beispiel: EXIT <ticketId>");
                    continue;
                }

                String ticketId = parts[1];
                if (!enterTimeByTicket.containsKey(ticketId)) {
                    System.out.println("Fehler: Ticket nicht gefunden.");
                    continue;
                }

                if (closedByTicket.get(ticketId)) {
                    System.out.println("Fehler: Ticket ist bereits abgeschlossen.");
                    System.out.println("Bereits bezahlt: " + priceByTicket.get(ticketId) + " €");
                    continue;
                }

                LocalDateTime enterTime = enterTimeByTicket.get(ticketId);
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

                closedByTicket.put(ticketId, true);
                priceByTicket.put(ticketId, price);
                finishedTickets++;
                revenue += price;

                System.out.println("Ticket: " + ticketId);
                System.out.println("Kennzeichen: " + plateByTicket.get(ticketId));
                System.out.println("Parkdauer: " + minutes + " Minuten");
                System.out.println("Preis: " + price + " €");
            } else if (command.equals("REPORT")) {
                System.out.println("----- REPORT -----");
                System.out.println("Ausgegebene Tickets: " + totalTickets);
                System.out.println("Abgeschlossene Parkvorgaenge: " + finishedTickets);
                System.out.println("Gesamtumsatz: " + String.format(Locale.GERMANY, "%.2f", revenue) + " €");
                System.out.println("------------------");
            } else {
                System.out.println("Unbekannter Befehl. Mit HELP bekommst du Hilfe.");
            }
        }

        scanner.close();
    }
}

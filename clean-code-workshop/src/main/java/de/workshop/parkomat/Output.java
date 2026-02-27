package de.workshop.parkomat;

import java.time.LocalDateTime;

public interface Output {

    void printCommandPrompt();

    void printWelcomeMessage();

    void printQuitMessage();

    void printHelpMessage();

    void printNoPlateError();

    void printEnterMessage(String ticketId, LocalDateTime now);

    void printNoIdError();

    void printNoTicketError();

    void printTicketAlreadyClosedError(ParkingTicket ticket);

    void printExitMessage(String ticketId, ParkingTicket ticket, long minutes, int fee);

    void printReport(ParkingReport report);

    void printUnknownCommandError();

}
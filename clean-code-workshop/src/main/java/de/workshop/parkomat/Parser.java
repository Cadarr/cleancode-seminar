package de.workshop.parkomat;

public interface Parser {

    String extractCommand(String[] parts);

    String extractPlate(String[] parts);

    String extractTicketId(String[] parts);

}
package de.workshop.parkomat;

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

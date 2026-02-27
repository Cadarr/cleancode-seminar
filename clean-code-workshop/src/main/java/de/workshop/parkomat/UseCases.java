package de.workshop.parkomat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class UseCases {
    private static final long MINUTES_PER_HOUR = 60;

    private static final int BASE_HOURS_INCLUDED = 1;
    private static final int BASE_FEE = 2;
    private static final int HOURLY_FEE_AFTER_BASE = 3;

    private static final int MAX_DAILY_FEE = 20;

    private Clock clock;
    private Map<String, ParkingTicket> ticketsById;
    private Output output;

    public UseCases(Clock clock, Map<String, ParkingTicket> ticketsById, Output output) {
        this.clock = clock;
        this.ticketsById = ticketsById;
        this.output = output;
    }

    public void registerEntry(ParkingReport report, String plate) {
        String ticketId = UUID.randomUUID().toString();
        LocalDateTime now = clock.now();

        ParkingTicket newTicket = new ParkingTicket(plate, now);
        ticketsById.put(ticketId, newTicket);

        report.recordTicketCreation();

        output.printEnterMessage(ticketId, now);
    }

    public void registerExit(ParkingReport report, String ticketId, ParkingTicket ticket) {
        LocalDateTime enterTime = ticket.getEnterTime();
        LocalDateTime exitTime = clock.now();

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

        output.printExitMessage(ticketId, ticket, minutes, fee);
    }

}

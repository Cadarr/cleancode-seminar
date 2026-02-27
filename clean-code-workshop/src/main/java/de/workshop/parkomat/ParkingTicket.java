package de.workshop.parkomat;

import java.time.LocalDateTime;

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

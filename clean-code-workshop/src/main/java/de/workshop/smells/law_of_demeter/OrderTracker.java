package de.workshop.smells.law_of_demeter;

public class OrderTracker {

    public String findPostalCode(Order order) {
        return order.getCustomer().getAddress().getPostalCode();
    }
}

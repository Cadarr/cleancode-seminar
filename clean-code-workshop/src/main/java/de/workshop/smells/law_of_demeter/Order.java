package de.workshop.smells.law_of_demeter;

public class Order {

    private final Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}

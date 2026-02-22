package de.workshop.smells.law_of_demeter;

public class Customer {

    private final Address address;

    public Customer(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}

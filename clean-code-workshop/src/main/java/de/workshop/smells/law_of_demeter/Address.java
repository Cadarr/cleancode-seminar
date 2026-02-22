package de.workshop.smells.law_of_demeter;

public class Address {

    private final String postalCode;

    public Address(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }
}

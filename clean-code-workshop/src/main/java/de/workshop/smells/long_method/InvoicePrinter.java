package de.workshop.smells.long_method;

import java.util.List;

public class InvoicePrinter {

    public String printInvoice(List<Double> positions) {
        double sum = 0;
        for (Double position : positions) {
            sum += position;
        }
        double tax = sum * 0.19;
        double gross = sum + tax;
        String header = "*** RECHNUNG ***\n";
        String body = "Netto: " + sum + "\nSteuer: " + tax + "\nBrutto: " + gross + "\n";
        String footer = "Danke fuer Ihren Einkauf.";
        return header + body + footer;
    }
}

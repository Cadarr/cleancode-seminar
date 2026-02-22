package de.workshop.smells.domain_mismatch;

public class PixelPainter {

    public String maleRechnung(double basisPreis, double steuer) {
        return "Rechnungssumme: " + (basisPreis + steuer);
    }
}

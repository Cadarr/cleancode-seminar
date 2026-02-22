package de.workshop.smells.switch_on_business_types;

public class PricingService {

    public double calculatePrice(CustomerType type, double basePrice) {
        switch (type) {
            case STANDARD:
                return basePrice;
            case PREMIUM:
                return basePrice * 0.9;
            case VIP:
                return basePrice * 0.8;
            default:
                return basePrice;
        }
    }
}

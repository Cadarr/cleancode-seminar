package de.workshop.smells.magic_numbers;

public class DiscountCalculator {

    public double calculate(double basketValue, int loyaltyYears) {
        if (basketValue > 250 && loyaltyYears > 3) {
            return basketValue * 0.87 + 4.99;
        }
        if (basketValue > 100) {
            return basketValue * 0.93 + 7.49;
        }
        return basketValue + 9.99;
    }
}

package org.example.shoppingcart;

public class CartCalculator {

    // Private constructor to prevent instantiation (SonarQube requirement)
    private CartCalculator() {
        throw new IllegalStateException("Utility class");
    }

    public static double calculateTotal(double[] prices, int[] quantities) {
        double total = 0;

        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * quantities[i];
        }

        return total;
    }
}

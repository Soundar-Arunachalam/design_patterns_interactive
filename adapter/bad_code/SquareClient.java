/**
 * THIRD-PARTY LIBRARY — Simulates the Square SDK
 *
 * Pretend this is code from Square's official Java SDK.
 * You DO NOT own this code. You CANNOT change it.
 *
 * Notice the differences from PayPal and Stripe:
 *   - Method is called "makePayment", not "sendPayment" or "charge"
 *   - Parameters are in REVERSE order: currency first, amount second
 *   - Returns a boolean success flag instead of void
 */
public class SquareClient {

    public boolean makePayment(String currency, double total) {
        System.out.println("  [Square SDK] ✓ Payment processed: " + String.format("%.2f", total) + " " + currency);
        return true;
    }

    public boolean makeRefund(String currency, double total) {
        System.out.println("  [Square SDK] ✓ Refund processed: " + String.format("%.2f", total) + " " + currency);
        return true;
    }
}

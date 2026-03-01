/**
 * THIRD-PARTY LIBRARY — Simulates the Square SDK
 * (Same as bad_code version — you still can't change it.)
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

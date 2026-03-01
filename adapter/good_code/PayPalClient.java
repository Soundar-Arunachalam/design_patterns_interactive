/**
 * THIRD-PARTY LIBRARY — Simulates the PayPal SDK
 * (Same as bad_code version — you still can't change it.)
 */
public class PayPalClient {

    public void sendPayment(double amount, String currency) {
        System.out.println("  [PayPal SDK] ✓ Payment sent: " + currency + " " + String.format("%.2f", amount));
    }

    public void sendRefund(double amount, String currency) {
        System.out.println("  [PayPal SDK] ✓ Refund sent: " + currency + " " + String.format("%.2f", amount));
    }
}

/**
 * THIRD-PARTY LIBRARY — Simulates the PayPal SDK
 *
 * Pretend this is code from PayPal's official Java SDK.
 * You DO NOT own this code. You CANNOT change it.
 * You must work with the method names and signatures PayPal gives you.
 */
public class PayPalClient {

    public void sendPayment(double amount, String currency) {
        System.out.println("  [PayPal SDK] ✓ Payment sent: " + currency + " " + String.format("%.2f", amount));
    }

    public void sendRefund(double amount, String currency) {
        System.out.println("  [PayPal SDK] ✓ Refund sent: " + currency + " " + String.format("%.2f", amount));
    }
}

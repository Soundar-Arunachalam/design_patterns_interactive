/**
 * THIRD-PARTY LIBRARY — Simulates the Stripe SDK
 *
 * Pretend this is code from Stripe's official Java SDK.
 * You DO NOT own this code. You CANNOT change it.
 *
 * Notice the differences from PayPal:
 *   - Method is called "charge", not "sendPayment"
 *   - Amount is in CENTS (integer), not dollars (double)
 *   - Currency is called "currencyCode" instead of "currency"
 */
public class StripeClient {

    public void charge(int amountInCents, String currencyCode) {
        System.out.println("  [Stripe SDK] ✓ Charged: " + amountInCents + " cents (" + currencyCode + ")");
    }

    public void issueRefund(int amountInCents, String currencyCode) {
        System.out.println("  [Stripe SDK] ✓ Refunded: " + amountInCents + " cents (" + currencyCode + ")");
    }
}

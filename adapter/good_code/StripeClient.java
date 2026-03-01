/**
 * THIRD-PARTY LIBRARY — Simulates the Stripe SDK
 * (Same as bad_code version — you still can't change it.)
 */
public class StripeClient {

    public void charge(int amountInCents, String currencyCode) {
        System.out.println("  [Stripe SDK] ✓ Charged: " + amountInCents + " cents (" + currencyCode + ")");
    }

    public void issueRefund(int amountInCents, String currencyCode) {
        System.out.println("  [Stripe SDK] ✓ Refunded: " + amountInCents + " cents (" + currencyCode + ")");
    }
}

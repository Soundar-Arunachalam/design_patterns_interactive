/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║        ✅ ADAPTER PATTERN — Stripe Adapter                   ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * WHAT IT DOES:
 *   Translates our app's clean PaymentProcessor calls into
 *   Stripe SDK's specific method calls — including the dollars-to-cents
 *   conversion that Stripe requires.
 *
 *   processPayment(75.50)  →  stripeClient.charge(7550, "USD")
 *   refundPayment(75.50)   →  stripeClient.issueRefund(7550, "USD")
 *
 * KEY INSIGHT:
 *   The cents conversion used to be copy-pasted in EVERY method of
 *   PaymentService (bad_code). Now it lives ONCE, right here.
 *   If Stripe changes its API (e.g., millicents), you update ONE file.
 */
public class StripeAdapter implements PaymentProcessor {

    private final StripeClient stripeClient;

    public StripeAdapter(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public void processPayment(double amount) {
        // The adapter handles all Stripe-specific quirks — not the service!
        int amountInCents = (int) (amount * 100);
        stripeClient.charge(amountInCents, "USD");
    }

    @Override
    public void refundPayment(double amount) {
        int amountInCents = (int) (amount * 100);
        stripeClient.issueRefund(amountInCents, "USD");
    }
}

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║        ✅ ADAPTER PATTERN — Apple Pay Adapter                ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * Remember the boss saying "Add Apple Pay by Friday"?
 * Here's what it looks like with the Adapter Pattern.
 *
 * STEPS TAKEN TO ADD APPLE PAY:
 *   1. ✅ Created ApplePayClient.java  (or used the real Apple Pay SDK)
 *   2. ✅ Created THIS adapter (ApplePayAdapter.java)
 *   3. ✅ That's it.
 *
 * WHAT DID NOT CHANGE:
 *   ✅ PaymentProcessor.java  — untouched
 *   ✅ PaymentService.java     — untouched
 *   ✅ PayPalAdapter.java      — untouched
 *   ✅ StripeAdapter.java      — untouched
 *   ✅ SquareAdapter.java      — untouched
 *
 * This is the Open/Closed Principle in action:
 *   "Open for extension, closed for modification."
 *
 * WHAT IT DOES:
 *   Apple Pay uses a completely different API:
 *     - One method (processTransaction) for both payments and refunds
 *     - Amount is a String, not a number
 *     - Uses "PAYMENT" / "REFUND" as transaction type strings
 *
 *   processPayment(99.0)  →  applePayClient.processTransaction("PAYMENT", "99.00", "USD")
 *   refundPayment(99.0)   →  applePayClient.processTransaction("REFUND",  "99.00", "USD")
 */
public class ApplePayAdapter implements PaymentProcessor {

    private final ApplePayClient applePayClient;

    public ApplePayAdapter(ApplePayClient applePayClient) {
        this.applePayClient = applePayClient;
    }

    @Override
    public void processPayment(double amount) {
        // Apple Pay needs a formatted String, not a double
        String formattedAmount = String.format("%.2f", amount);
        applePayClient.processTransaction("PAYMENT", formattedAmount, "USD");
    }

    @Override
    public void refundPayment(double amount) {
        String formattedAmount = String.format("%.2f", amount);
        applePayClient.processTransaction("REFUND", formattedAmount, "USD");
    }
}

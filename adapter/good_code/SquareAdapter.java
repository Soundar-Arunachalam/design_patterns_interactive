/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║        ✅ ADAPTER PATTERN — Square Adapter                   ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * WHAT IT DOES:
 *   Translates our app's clean PaymentProcessor calls into
 *   Square SDK's specific method calls — handling the reversed parameter
 *   order and the boolean success flag that Square returns.
 *
 *   processPayment(30.0)  →  squareClient.makePayment("USD", 30.0)  [reversed!]
 *   refundPayment(30.0)   →  squareClient.makeRefund("USD", 30.0)   [reversed!]
 *
 * KEY INSIGHT:
 *   Square's quirky API (reversed params, boolean return) is now fully
 *   encapsulated here. PaymentService sees none of it.
 *   If Square releases a new SDK version with breaking changes,
 *   you update this adapter — NOTHING ELSE changes.
 */
public class SquareAdapter implements PaymentProcessor {

    private final SquareClient squareClient;

    public SquareAdapter(SquareClient squareClient) {
        this.squareClient = squareClient;
    }

    @Override
    public void processPayment(double amount) {
        // Square reverses the args: currency goes first, amount second
        boolean success = squareClient.makePayment("USD", amount);
        if (!success) {
            // Convert Square's boolean failure into a proper exception
            // so our app can handle it consistently
            throw new RuntimeException("Square payment failed for amount: " + amount);
        }
    }

    @Override
    public void refundPayment(double amount) {
        boolean success = squareClient.makeRefund("USD", amount);
        if (!success) {
            throw new RuntimeException("Square refund failed for amount: " + amount);
        }
    }
}

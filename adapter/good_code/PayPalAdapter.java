/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║        ✅ ADAPTER PATTERN — PayPal Adapter                   ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * ROLE IN THE PATTERN:
 *   This is the "Adapter". It wraps PayPalClient (the "Adaptee") and
 *   implements PaymentProcessor (the "Target" interface).
 *
 * WHAT IT DOES:
 *   Translates our app's clean PaymentProcessor calls into
 *   PayPal SDK's specific method calls.
 *
 *   processPayment(50.0)  →  payPalClient.sendPayment(50.0, "USD")
 *   refundPayment(50.0)   →  payPalClient.sendRefund(50.0, "USD")
 *
 * ALL PayPal-specific knowledge lives HERE — in one place.
 * PaymentService doesn't need to know any of this.
 */
public class PayPalAdapter implements PaymentProcessor {

    private final PayPalClient payPalClient;

    // The real PayPalClient is injected — easy to replace with a mock in tests
    public PayPalAdapter(PayPalClient payPalClient) {
        this.payPalClient = payPalClient;
    }

    @Override
    public void processPayment(double amount) {
        // Delegate to PayPal's actual method name
        payPalClient.sendPayment(amount, "USD");
    }

    @Override
    public void refundPayment(double amount) {
        payPalClient.sendRefund(amount, "USD");
    }
}

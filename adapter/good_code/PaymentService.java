/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║    ✅ CLEAN CODE — PaymentService (using Adapter Pattern)    ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * Compare this side-by-side with bad_code/PaymentService.java.
 *
 * WHAT'S GONE:
 *   ✅ No if-else chains
 *   ✅ No magic strings ("paypal", "stripe", "square")
 *   ✅ No knowledge of Stripe's cents format
 *   ✅ No knowledge of Square's reversed parameters
 *   ✅ No duplicated conversion logic
 *   ✅ No direct imports of any third-party SDK class
 *
 * HOW IT WORKS:
 *   PaymentService only talks to the PaymentProcessor interface.
 *   It doesn't know — and doesn't care — whether the underlying
 *   provider is PayPal, Stripe, Square, Apple Pay, or anything else.
 *
 * ADDING A NEW PROVIDER:
 *   1. Create a new XxxAdapter implements PaymentProcessor
 *   2. Inject it here
 *   3. This file NEVER changes. ✅
 *
 * BONUS — This also gives you easy unit testing:
 *   In a test, inject a MockPaymentProcessor that records calls.
 *   No real API calls. No network. No money moved. ✅
 */
public class PaymentService {

    private final PaymentProcessor paymentProcessor;

    // The specific processor is INJECTED from outside (Dependency Injection).
    // PaymentService has no idea which provider is behind the interface.
    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void processPayment(double amount) {
        System.out.println("  → Initiating payment of $" + String.format("%.2f", amount));
        paymentProcessor.processPayment(amount);
    }

    public void refundPayment(double amount) {
        System.out.println("  → Initiating refund of $" + String.format("%.2f", amount));
        paymentProcessor.refundPayment(amount);
    }
}

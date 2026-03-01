/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║        ✅ CLEAN CODE — Entry Point                           ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * Run this from the good_code/ folder:
 *   javac *.java
 *   java Main
 *
 * HOW THE ADAPTER PATTERN IS ASSEMBLED HERE:
 *
 *   The "wiring" happens at this top level (also called the composition root).
 *   We choose WHICH adapter to use and inject it into PaymentService.
 *   PaymentService itself never knows what's behind the interface.
 *
 *   ┌─────────────────────────────────────────────────────────┐
 *   │                     PaymentService                       │
 *   │                  (only knows about)                      │
 *   │                   PaymentProcessor                       │
 *   │                  ┌──────┬──────┐                        │
 *   │            implements   │      │                        │
 *   │           ┌─────────────┘      └──────────────┐        │
 *   │     PayPalAdapter        StripeAdapter    ...  │        │
 *   │           │                    │               │        │
 *   │     (wraps)              (wraps + converts)    │        │
 *   │     PayPalClient         StripeClient           │       │
 *   └─────────────────────────────────────────────────────────┘
 */
public class Main {

    public static void main(String[] args) {

        // ── Wiring: create adapters by wrapping the third-party clients ──
        PaymentProcessor payPalProcessor  = new PayPalAdapter(new PayPalClient());
        PaymentProcessor stripeProcessor  = new StripeAdapter(new StripeClient());
        PaymentProcessor squareProcessor  = new SquareAdapter(new SquareClient());
        PaymentProcessor applePayProcessor = new ApplePayAdapter(new ApplePayClient());

        // ── Inject the adapter into PaymentService ──
        // PaymentService has NO idea it's talking to PayPal, Stripe, etc.
        PaymentService payPalService   = new PaymentService(payPalProcessor);
        PaymentService stripeService   = new PaymentService(stripeProcessor);
        PaymentService squareService   = new PaymentService(squareProcessor);
        PaymentService applePayService = new PaymentService(applePayProcessor);

        System.out.println("================================================");
        System.out.println("             Processing Payments                 ");
        System.out.println("================================================");
        payPalService.processPayment(50.00);
        stripeService.processPayment(75.50);
        squareService.processPayment(30.00);
        applePayService.processPayment(99.00);   // ← Apple Pay: zero changes to PaymentService!

        System.out.println();
        System.out.println("================================================");
        System.out.println("             Processing Refunds                  ");
        System.out.println("================================================");
        payPalService.refundPayment(50.00);
        stripeService.refundPayment(75.50);
        squareService.refundPayment(30.00);
        applePayService.refundPayment(99.00);

        System.out.println();
        System.out.println("Adding Apple Pay required:");
        System.out.println("  ✅ ApplePayClient.java    (or the real SDK)");
        System.out.println("  ✅ ApplePayAdapter.java   (new adapter)");
        System.out.println("  ❌ PaymentService.java    (NOT touched)");
        System.out.println("  ❌ PayPalAdapter.java     (NOT touched)");
        System.out.println("  ❌ StripeAdapter.java     (NOT touched)");
        System.out.println("  ❌ SquareAdapter.java     (NOT touched)");
        System.out.println("  ❌ PaymentProcessor.java  (NOT touched)");
    }
}

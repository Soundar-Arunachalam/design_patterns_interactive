/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║        ✅ ADAPTER PATTERN — Target Interface                  ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * This is the interface YOUR application defines.
 * It represents the "ideal" payment API that your app wants to work with —
 * clean, simple, and consistent, regardless of the underlying provider.
 *
 * KEY IDEA:
 *   Your application code (PaymentService) will ONLY ever depend on
 *   this interface. It will never import PayPalClient, StripeClient,
 *   SquareClient, or anything from a third-party SDK directly.
 *
 *   Third-party SDKs speak different "languages".
 *   This interface is the common language your app understands.
 *   Adapters translate from the third-party language to this language.
 *
 * ANALOGY:
 *   Think of a universal power adapter for your laptop charger.
 *   The wall socket in Japan has a different shape than one in Europe.
 *   Your laptop doesn't change — the adapter does the translation.
 *   This interface is what your laptop's charging port looks like.
 */
public interface PaymentProcessor {

    void processPayment(double amount);

    void refundPayment(double amount);
}

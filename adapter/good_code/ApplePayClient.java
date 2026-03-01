/**
 * THIRD-PARTY LIBRARY — Simulates the Apple Pay SDK
 *
 * Pretend Apple released this SDK after your system was already built.
 * Notice Apple Pay uses a completely different API style:
 *   - processTransaction() instead of sendPayment() or charge()
 *   - Amount is a String (e.g., "50.00") not a number
 *   - Uses a transactionType enum-style string: "PAYMENT" or "REFUND"
 *
 * This is the BRAND NEW provider the boss asked you to add by Friday.
 */
public class ApplePayClient {

    public String processTransaction(String transactionType, String amount, String currency) {
        System.out.println("  [Apple Pay SDK] ✓ " + transactionType + " of "
                + currency + " " + amount + " — Transaction ID: TXN-" + System.currentTimeMillis() % 100000);
        return "TXN-" + System.currentTimeMillis() % 100000;
    }
}

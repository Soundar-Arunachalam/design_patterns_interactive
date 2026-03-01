/**
 * ❌ BAD CODE — Entry point
 *
 * Run this from the bad_code/ folder:
 *   javac *.java
 *   java Main
 *
 * While reading this, ask yourself:
 *   - What happens if I mistype "stripee" instead of "stripe"?
 *   - What happens when I need to add Apple Pay?
 *   - How do I write a unit test without hitting real payment APIs?
 */
public class Main {

    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        System.out.println("==============================");
        System.out.println("      Processing Payments     ");
        System.out.println("==============================");
        // Magic strings. One typo = runtime crash, not a compiler error.
        service.processPayment("paypal", 50.00);
        service.processPayment("stripe", 75.50);
        service.processPayment("square", 30.00);

        System.out.println();
        System.out.println("==============================");
        System.out.println("      Processing Refunds      ");
        System.out.println("==============================");
        service.refundPayment("paypal", 50.00);
        service.refundPayment("stripe", 75.50);
        service.refundPayment("square", 30.00);

        System.out.println();
        System.out.println("Now imagine the boss says: 'Add Apple Pay by Friday.'");
        System.out.println("You open PaymentService.java and start adding more else-if blocks...");
        System.out.println("😩 There has to be a better way.");
    }
}

public class PaymentService {
    // Yet another Logger instance — same problem
    private Logger logger = new Logger("PaymentService");

    public void processPayment(double amount) {
        logger.log("Payment processed: $" + amount);
    }
}

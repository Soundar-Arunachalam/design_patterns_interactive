public class PaymentService {
    private Logger logger = Logger.getInstance();

    public void processPayment(double amount) {
        logger.log("Payment processed: $" + amount);
    }
}

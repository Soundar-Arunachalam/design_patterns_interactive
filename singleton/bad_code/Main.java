// Problem: every class creates its own Logger.
// Each instantiation does setup work (open file handle, read config, etc.)
// No shared state — you can't change the log level in one place and have it affect all loggers.
package singleton.bad_code;
public class Main {
    public static void main(String[] args) {
        OrderService orders = new OrderService();
        PaymentService payments = new PaymentService();

        orders.placeOrder("Laptop");
        payments.processPayment(999.99);

        // Even here — a third Logger instance
        Logger logger = new Logger("history");
        logger.log("App started");

        System.out.println("\nHow many Logger instances exist? 3. Should be 1.");
    }
}

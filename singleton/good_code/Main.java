public class Main {
    public static void main(String[] args) {
        OrderService orders = new OrderService();
        PaymentService payments = new PaymentService();

        orders.placeOrder("Laptop");
        payments.processPayment(999.99);

        // Prove it's the same instance
        Logger a = Logger.getInstance();
        Logger b = Logger.getInstance();
        System.out.println("\nSame instance? " + (a == b)); // true
    }
}

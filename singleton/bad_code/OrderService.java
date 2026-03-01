public class OrderService {
    // Every service creates its own Logger — wasteful
    private Logger logger = new Logger("OrderService");

    public void placeOrder(String item) {
        logger.log("Order placed: " + item);
    }
}

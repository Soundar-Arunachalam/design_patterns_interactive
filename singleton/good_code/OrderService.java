public class OrderService {
    private Logger logger = Logger.getInstance();

    public void placeOrder(String item) {
        logger.log("Order placed: " + item);
    }
}

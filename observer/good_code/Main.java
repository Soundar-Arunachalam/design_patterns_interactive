public class Main {
    public static void main(String[] args) {
        StockMarket market = new StockMarket("AAPL");
        market.subscribe(new MobileAlert());
        market.subscribe(new EmailAlert());
        // Adding DashboardAlert? Just: market.subscribe(new DashboardAlert())
        // StockMarket.java is NOT touched.

        market.setPrice(175.50);
        market.setPrice(180.00);

        // You can also unsubscribe at runtime — impossible with the bad code approach.
    }
}

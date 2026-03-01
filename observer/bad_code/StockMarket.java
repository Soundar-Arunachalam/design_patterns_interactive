// Problem: StockMarket is hard-coded to specific alert classes.
// Adding a new subscriber (e.g. DashboardAlert) means modifying StockMarket.
public class StockMarket {
    private String stockName;
    private double price;

    // Hard-wired to concrete classes — tight coupling
    private MobileAlert mobileAlert = new MobileAlert();
    private EmailAlert emailAlert   = new EmailAlert();

    public StockMarket(String stockName) { this.stockName = stockName; }

    public void setPrice(double price) {
        this.price = price;
        System.out.println("\n[Market] " + stockName + " price updated to $" + price);
        // Must manually call every subscriber — add a new one → edit this method
        mobileAlert.notify(stockName, price);
        emailAlert.notify(stockName, price);
    }
}

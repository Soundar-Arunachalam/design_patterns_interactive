import java.util.ArrayList;
import java.util.List;

// Subject — knows nothing about concrete observers
public class StockMarket {
    private String stockName;
    private List<StockObserver> observers = new ArrayList<>();

    public StockMarket(String stockName) { this.stockName = stockName; }

    public void subscribe(StockObserver o)   { observers.add(o); }
    public void unsubscribe(StockObserver o) { observers.remove(o); }

    public void setPrice(double price) {
        System.out.println("\n[Market] " + stockName + " price updated to $" + price);
        for (StockObserver o : observers) o.onPriceChange(stockName, price);
    }
}

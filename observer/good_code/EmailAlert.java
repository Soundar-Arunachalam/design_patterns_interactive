public class EmailAlert implements StockObserver {
    public void onPriceChange(String stock, double price) {
        System.out.println("  [Email] Alert sent: " + stock + " is now $" + price);
    }
}

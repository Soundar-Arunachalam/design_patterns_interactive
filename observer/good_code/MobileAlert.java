public class MobileAlert implements StockObserver {
    public void onPriceChange(String stock, double price) {
        System.out.println("  [Mobile] Push alert: " + stock + " is now $" + price);
    }
}

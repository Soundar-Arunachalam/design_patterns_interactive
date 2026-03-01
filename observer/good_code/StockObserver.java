public interface StockObserver {
    void onPriceChange(String stock, double price);
}

public class EmailAlert {
    public void notify(String stock, double price) {
        System.out.println("  [Email] Alert sent: " + stock + " is now $" + price);
    }
}

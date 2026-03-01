public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(2);

        machine.insertCoin();
        machine.pressButton();
        machine.dispense();

        machine.insertCoin();
        machine.insertCoin(); // already has coin
        machine.pressButton();
        machine.dispense();   // now empty

        machine.insertCoin(); // empty state handles it
    }
}

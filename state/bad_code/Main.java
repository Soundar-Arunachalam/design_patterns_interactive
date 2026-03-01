public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.insertCoin();
        machine.pressButton();
        machine.dispense();
        machine.insertCoin();
        machine.insertCoin(); // already has coin
        machine.pressButton();
        machine.pressButton(); // already dispensing
    }
}

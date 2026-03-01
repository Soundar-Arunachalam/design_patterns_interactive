public class HasCoinState implements VendingState {
    public void insertCoin(VendingMachine m)  { System.out.println("Coin already inserted."); }
    public void pressButton(VendingMachine m) { System.out.println("Button pressed."); m.setState(new DispensingState()); }
    public void dispense(VendingMachine m)    { System.out.println("Press the button first."); }
}

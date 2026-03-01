public class IdleState implements VendingState {
    public void insertCoin(VendingMachine m)  { System.out.println("Coin inserted."); m.setState(new HasCoinState()); }
    public void pressButton(VendingMachine m) { System.out.println("Insert a coin first."); }
    public void dispense(VendingMachine m)    { System.out.println("No coin inserted."); }
}

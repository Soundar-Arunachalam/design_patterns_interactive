public class EmptyState implements VendingState {
    public void insertCoin(VendingMachine m)  { System.out.println("Machine is empty. Refund given."); }
    public void pressButton(VendingMachine m) { System.out.println("Machine is empty."); }
    public void dispense(VendingMachine m)    { System.out.println("Nothing to dispense."); }
}

public class DispensingState implements VendingState {
    public void insertCoin(VendingMachine m)  { System.out.println("Wait, dispensing..."); }
    public void pressButton(VendingMachine m) { System.out.println("Already dispensing."); }
    public void dispense(VendingMachine m) {
        m.decreaseItem();
        System.out.println("Item dispensed! Remaining: " + m.getItemCount());
        m.setState(m.getItemCount() > 0 ? new IdleState() : new EmptyState());
    }
}

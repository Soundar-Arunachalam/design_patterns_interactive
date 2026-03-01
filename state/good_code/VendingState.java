// Each state knows how to handle each action — state-specific logic is encapsulated
public interface VendingState {
    void insertCoin(VendingMachine machine);
    void pressButton(VendingMachine machine);
    void dispense(VendingMachine machine);
}

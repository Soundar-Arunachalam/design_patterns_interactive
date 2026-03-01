public class VendingMachine {
    private VendingState state;
    private int itemCount;

    public VendingMachine(int items) {
        this.itemCount = items;
        this.state = new IdleState();
    }

    public void insertCoin()  { state.insertCoin(this);  }
    public void pressButton() { state.pressButton(this); }
    public void dispense()    { state.dispense(this);    }

    public void setState(VendingState state) { this.state = state; }
    public void decreaseItem()              { itemCount--; }
    public int getItemCount()               { return itemCount; }
}

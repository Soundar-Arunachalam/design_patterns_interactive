// Problem: every action checks the current state with if-else.
// Adding a new state (e.g. "maintenance") means editing every single method.
public class VendingMachine {
    private String state = "idle"; // magic string — typos compile fine
    private int itemCount = 3;

    public void insertCoin() {
        if (state.equals("idle"))       { state = "has_coin"; System.out.println("Coin inserted."); }
        else if (state.equals("has_coin")) System.out.println("Coin already inserted.");
        else if (state.equals("dispensing")) System.out.println("Wait, dispensing...");
    }

    public void pressButton() {
        if (state.equals("idle"))          System.out.println("Insert a coin first.");
        else if (state.equals("has_coin")) { state = "dispensing"; System.out.println("Button pressed."); }
        else if (state.equals("dispensing")) System.out.println("Already dispensing.");
    }

    public void dispense() {
        if (state.equals("dispensing") && itemCount > 0) {
            itemCount--;
            state = itemCount > 0 ? "idle" : "empty";
            System.out.println("Item dispensed! Remaining: " + itemCount);
        } else {
            System.out.println("Cannot dispense.");
        }
    }
}

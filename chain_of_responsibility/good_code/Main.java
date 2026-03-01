public class Main {
    public static void main(String[] args) {
        SupportHandler l1 = new L1Support();
        // Build the chain: L1 → L2 → Manager
        l1.setNext(new L2Support()).setNext(new ManagerSupport());

        l1.handle("Password reset", 1);
        l1.handle("App crash on checkout", 3);
        l1.handle("Data breach suspected", 5);
        l1.handle("Company-wide outage", 6); // no handler — reports unresolved

        // Adding a CEO handler? Create CeoSupport, append to chain. Nothing else changes.
    }
}

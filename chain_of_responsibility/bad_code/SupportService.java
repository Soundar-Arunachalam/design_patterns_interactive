// Problem: one class decides everything. Adding a new level means editing this method.
// The priority thresholds and responses are all tangled together.
public class SupportService {

    public void handleTicket(String issue, int priority) {
        if (priority <= 1) {
            System.out.println("[L1 Support] Handling: " + issue);
        } else if (priority <= 3) {
            System.out.println("[L2 Support] Handling: " + issue);
        } else if (priority <= 5) {
            System.out.println("[Manager] Handling: " + issue);
        } else {
            System.out.println("[CEO] Handling: " + issue);
            // What if we add a VP level between Manager and CEO?
            // This method grows forever.
        }
    }
}

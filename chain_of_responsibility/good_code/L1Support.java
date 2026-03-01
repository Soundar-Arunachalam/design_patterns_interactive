public class L1Support extends SupportHandler {
    public void handle(String issue, int priority) {
        if (priority <= 1) System.out.println("[L1 Support] Handling: " + issue);
        else passToNext(issue, priority);
    }
}

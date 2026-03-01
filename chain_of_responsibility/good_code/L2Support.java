public class L2Support extends SupportHandler {
    public void handle(String issue, int priority) {
        if (priority <= 3) System.out.println("[L2 Support] Handling: " + issue);
        else passToNext(issue, priority);
    }
}

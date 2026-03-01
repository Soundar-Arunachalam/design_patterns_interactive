public class ManagerSupport extends SupportHandler {
    public void handle(String issue, int priority) {
        if (priority <= 5) System.out.println("[Manager] Handling: " + issue);
        else passToNext(issue, priority);
    }
}

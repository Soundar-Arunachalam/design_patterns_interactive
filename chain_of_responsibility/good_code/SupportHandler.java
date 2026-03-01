// Each handler decides: handle it, or pass it to the next in the chain
public abstract class SupportHandler {
    protected SupportHandler next;

    public SupportHandler setNext(SupportHandler next) {
        this.next = next;
        return next; // allows chaining: a.setNext(b).setNext(c)
    }

    public abstract void handle(String issue, int priority);

    protected void passToNext(String issue, int priority) {
        if (next != null) next.handle(issue, priority);
        else System.out.println("[Unresolved] No handler for priority " + priority);
    }
}

public class Main {
    public static void main(String[] args) {
        // Simple case — fine
        new EmailMessage("alice@example.com", "Hi", "Hello Alice!").send();

        // Want isHtml=true, priority=2, but no cc or bcc?
        // Forced to pass null, null just to reach the boolean.
        new EmailMessage("bob@example.com", "Report", "See attached",
                null, null, true, 2).send();  // which null is cc? which is bcc?

        // What if someone accidentally swaps isHtml and priority?
        // new EmailMessage(..., null, null, 2, true) — compiles, wrong behavior.
    }
}

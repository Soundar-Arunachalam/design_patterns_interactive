public class Main {
    public static void main(String[] args) {
        // Each field is named — no guessing, no nulls, no wrong ordering
        new EmailMessage.Builder("alice@example.com", "Hi", "Hello Alice!")
                .build()
                .send();

        // Skip cc/bcc entirely — just set what you need
        new EmailMessage.Builder("bob@example.com", "Report", "See attached")
                .isHtml(true)
                .priority(2)
                .build()
                .send();

        new EmailMessage.Builder("team@example.com", "Meeting", "Tomorrow 10am")
                .cc("manager@example.com")
                .bcc("hr@example.com")
                .priority(3)
                .build()
                .send();
    }
}

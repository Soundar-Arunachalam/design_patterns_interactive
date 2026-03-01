// Telescoping constructors — one for every combination of optional fields.
// Callers have to count arguments and guess which null goes where.
public class EmailMessage {
    String to, subject, body, cc, bcc;
    boolean isHtml;
    int priority;

    public EmailMessage(String to, String subject, String body) {
        this(to, subject, body, null, null, false, 1);
    }

    public EmailMessage(String to, String subject, String body, String cc) {
        this(to, subject, body, cc, null, false, 1);
    }

    // What if I want isHtml=true but no cc/bcc? I must pass two nulls.
    public EmailMessage(String to, String subject, String body,
                        String cc, String bcc, boolean isHtml, int priority) {
        this.to = to; this.subject = subject; this.body = body;
        this.cc = cc; this.bcc = bcc;
        this.isHtml = isHtml; this.priority = priority;
    }

    public void send() {
        System.out.println("To: " + to + " | Subject: " + subject);
        if (cc != null)  System.out.println("CC: " + cc);
        if (bcc != null) System.out.println("BCC: " + bcc);
        System.out.println("HTML: " + isHtml + " | Priority: " + priority);
        System.out.println("Body: " + body);
        System.out.println("Sent!\n");
    }
}

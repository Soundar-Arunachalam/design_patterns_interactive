public class EmailMessage {
    private final String to, subject, body;
    private final String cc, bcc;
    private final boolean isHtml;
    private final int priority;

    private EmailMessage(Builder b) {
        this.to = b.to; this.subject = b.subject; this.body = b.body;
        this.cc = b.cc; this.bcc = b.bcc;
        this.isHtml = b.isHtml; this.priority = b.priority;
    }

    public void send() {
        System.out.println("To: " + to + " | Subject: " + subject);
        if (cc != null)  System.out.println("CC: " + cc);
        if (bcc != null) System.out.println("BCC: " + bcc);
        System.out.println("HTML: " + isHtml + " | Priority: " + priority);
        System.out.println("Body: " + body);
        System.out.println("Sent!\n");
    }

    public static class Builder {
        // required
        private final String to, subject, body;
        // optional — defaults provided here
        private String cc = null, bcc = null;
        private boolean isHtml = false;
        private int priority = 1;

        public Builder(String to, String subject, String body) {
            this.to = to; this.subject = subject; this.body = body;
        }

        public Builder cc(String cc)           { this.cc = cc;             return this; }
        public Builder bcc(String bcc)         { this.bcc = bcc;           return this; }
        public Builder isHtml(boolean isHtml)  { this.isHtml = isHtml;     return this; }
        public Builder priority(int priority)  { this.priority = priority; return this; }

        public EmailMessage build() { return new EmailMessage(this); }
    }
}

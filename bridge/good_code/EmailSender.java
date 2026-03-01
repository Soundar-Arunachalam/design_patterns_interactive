public class EmailSender implements MessageSender {
    public void send(String prefix, String message) {
        System.out.println("[Email] " + prefix + ": " + message);
    }
}

public class SmsSender implements MessageSender {
    public void send(String prefix, String message) {
        System.out.println("[SMS] " + prefix + ": " + message);
    }
}

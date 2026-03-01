public class SlackSender implements MessageSender {
    public void send(String prefix, String message) {
        System.out.println("[Slack] " + prefix + ": " + message);
    }
}

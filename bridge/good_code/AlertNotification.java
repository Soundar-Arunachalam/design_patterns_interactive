public class AlertNotification extends Notification {
    public AlertNotification(MessageSender sender) { super(sender); }

    public void send(String message) { sender.send("ALERT", message); }
}

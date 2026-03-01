public class ReminderNotification extends Notification {
    public ReminderNotification(MessageSender sender) { super(sender); }

    public void send(String message) { sender.send("REMINDER", message); }
}

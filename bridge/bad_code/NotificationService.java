// Problem: every combination of (notification type × channel) needs its own if-else block.
// Adding a new channel (e.g. Slack) means editing every method.
// Adding a new type (e.g. Digest) means adding a whole new method with the same channel checks.
public class NotificationService {

    public void sendAlert(String channel, String message) {
        if (channel.equals("email")) {
            System.out.println("[Email] ALERT: " + message);
        } else if (channel.equals("sms")) {
            System.out.println("[SMS] ALERT: " + message);
        } else if (channel.equals("push")) {
            System.out.println("[Push] ALERT: " + message);
        }
    }

    public void sendReminder(String channel, String message) {
        if (channel.equals("email")) {
            System.out.println("[Email] REMINDER: " + message);
        } else if (channel.equals("sms")) {
            System.out.println("[SMS] REMINDER: " + message);
        } else if (channel.equals("push")) {
            System.out.println("[Push] REMINDER: " + message);
        }
    }

    public void sendPromotion(String channel, String message) {
        if (channel.equals("email")) {
            System.out.println("[Email] PROMO: " + message);
        } else if (channel.equals("sms")) {
            System.out.println("[SMS] PROMO: " + message);
        } else if (channel.equals("push")) {
            System.out.println("[Push] PROMO: " + message);
        }
    }
}

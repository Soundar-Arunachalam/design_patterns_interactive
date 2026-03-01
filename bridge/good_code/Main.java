public class Main {
    public static void main(String[] args) {
        // Mix any notification type with any channel freely — no if-else
        new AlertNotification(new EmailSender()).send("Server is down!");
        new ReminderNotification(new SmsSender()).send("Meeting in 10 mins");
        new AlertNotification(new SlackSender()).send("Disk space critical!");

        // Adding a new channel? Create one new class (e.g. PushSender). Done.
        // Adding a new type? Create one new class (e.g. DigestNotification). Done.
        // N types × M channels = N + M classes, not N × M.
    }
}

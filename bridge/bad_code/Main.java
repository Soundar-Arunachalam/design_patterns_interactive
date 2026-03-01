public class Main {
    public static void main(String[] args) {
        NotificationService svc = new NotificationService();
        svc.sendAlert("email", "Server is down!");
        svc.sendReminder("sms", "Meeting in 10 mins");
        svc.sendPromotion("push", "50% off today!");

        System.out.println("\nBoss: 'Add Slack channel.'");
        System.out.println("You: *adds else-if in sendAlert, sendReminder, sendPromotion...*");
        System.out.println("Boss: 'Also add a Digest notification type.'");
        System.out.println("You: *adds a whole new method with the same 3 channel checks*");
    }
}

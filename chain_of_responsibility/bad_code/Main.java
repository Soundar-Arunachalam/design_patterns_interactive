public class Main {
    public static void main(String[] args) {
        SupportService support = new SupportService();
        support.handleTicket("Password reset", 1);
        support.handleTicket("App crash on checkout", 3);
        support.handleTicket("Data breach suspected", 5);
        support.handleTicket("Company-wide outage", 6);
    }
}

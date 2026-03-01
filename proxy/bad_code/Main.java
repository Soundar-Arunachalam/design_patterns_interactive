public class Main {
    public static void main(String[] args) {
        OrderController orders = new OrderController();
        ProfileController profiles = new ProfileController();

        orders.showOrder(1);
        orders.showOrder(1);   // hits cache in OrderController

        profiles.showProfile(1); // hits DB again — ProfileController has its own separate cache!

        System.out.println("\nCaching logic is duplicated across every controller.");
        System.out.println("Each controller has its own isolated cache — user 1 is fetched twice.");
    }
}

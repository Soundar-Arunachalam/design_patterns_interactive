public class Main {
    public static void main(String[] args) {
        DeliveryService service = new DeliveryService();
        System.out.println("Standard: $" + service.calculateCost("standard", 3.0));
        System.out.println("Express:  $" + service.calculateCost("express",  3.0));
        System.out.println("Drone:    $" + service.calculateCost("drone",    1.5));

        System.out.println("\nBoss: 'Add same-day delivery.'");
        System.out.println("You: *adds another else-if and hopes nothing breaks*");
    }
}

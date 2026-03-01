public class Main {
    public static void main(String[] args) {
        double weight = 3.0;

        System.out.println("Standard: $" + new DeliveryService(new StandardDelivery()).calculateCost(weight));
        System.out.println("Express:  $" + new DeliveryService(new ExpressDelivery()).calculateCost(weight));
        System.out.println("Drone:    $" + new DeliveryService(new DroneDelivery()).calculateCost(1.5));

        // Can also swap at runtime:
        DeliveryService service = new DeliveryService(new StandardDelivery());
        System.out.println("\nDefault: $" + service.calculateCost(weight));
        service.setStrategy(new ExpressDelivery());
        System.out.println("Upgraded to express: $" + service.calculateCost(weight));

        // Adding SameDayDelivery? One new class. DeliveryService.java untouched.
    }
}

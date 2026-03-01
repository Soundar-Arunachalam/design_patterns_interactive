public class DroneDelivery implements DeliveryStrategy {
    public double calculate(double weight) {
        if (weight >= 2.0) throw new IllegalArgumentException("Drone limit exceeded: " + weight + "kg");
        return 8.0;
    }
}

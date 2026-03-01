public class DeliveryService {
    private DeliveryStrategy strategy;

    public DeliveryService(DeliveryStrategy strategy) { this.strategy = strategy; }

    // Swap strategy at runtime if needed
    public void setStrategy(DeliveryStrategy strategy) { this.strategy = strategy; }

    public double calculateCost(double weight) { return strategy.calculate(weight); }
}

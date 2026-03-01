// Problem: adding a new delivery type means editing this class.
// The calculation logic and the decision logic are tangled together.
public class DeliveryService {

    public double calculateCost(String type, double weight) {
        if (type.equals("standard")) {
            return weight * 1.5;
        } else if (type.equals("express")) {
            return weight * 3.0 + 5.0;
        } else if (type.equals("drone")) {
            return weight < 2.0 ? 8.0 : -1; // drones have weight limit
        } else {
            throw new IllegalArgumentException("Unknown delivery type: " + type);
        }
    }
}

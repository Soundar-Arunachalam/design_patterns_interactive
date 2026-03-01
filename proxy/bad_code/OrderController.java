import java.util.HashMap;
import java.util.Map;

// Problem: every caller copy-pastes the same cache-check logic.
// If the cache strategy changes, every caller must be updated.
// Access control ("admin only") would also be scattered everywhere.
public class OrderController {
    private UserService userService = new UserService();
    private Map<Integer, String> cache = new HashMap<>(); // duplicated in every controller

    public void showOrder(int userId) {
        // cache check duplicated here
        if (!cache.containsKey(userId)) {
            cache.put(userId, userService.getUserById(userId));
        }
        System.out.println("Order for: " + cache.get(userId));
    }
}

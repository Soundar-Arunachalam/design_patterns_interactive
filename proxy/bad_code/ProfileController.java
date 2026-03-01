import java.util.HashMap;
import java.util.Map;

public class ProfileController {
    private UserService userService = new UserService();
    private Map<Integer, String> cache = new HashMap<>(); // same cache logic, copy-pasted again

    public void showProfile(int userId) {
        if (!cache.containsKey(userId)) {
            cache.put(userId, userService.getUserById(userId));
        }
        System.out.println("Profile: " + cache.get(userId));
    }
}

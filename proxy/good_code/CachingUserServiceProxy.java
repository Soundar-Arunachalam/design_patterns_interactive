import java.util.HashMap;
import java.util.Map;

// Proxy — same interface as RealUserService, but adds caching transparently.
// Callers never know if they're talking to the real service or the proxy.
public class CachingUserServiceProxy implements UserService {
    private final UserService realService;
    private final Map<Integer, String> cache = new HashMap<>();

    public CachingUserServiceProxy(UserService realService) {
        this.realService = realService;
    }

    public String getUserById(int id) {
        if (allowOnlyAdmins() && !isAdmin()) {
            throw new RuntimeException("Access denied: admin only");
        }
        if (!cache.containsKey(id)) {
            cache.put(id, realService.getUserById(id));
        } else {
            System.out.println("  [Cache] Returning cached user " + id);
        }
        return cache.get(id);
    }
}

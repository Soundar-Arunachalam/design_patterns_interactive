public class Main {
    public static void main(String[] args) {
        // Inject the proxy — controllers just use UserService interface, unaware of caching
        UserService userService = new CachingUserServiceProxy(new RealUserService());

        System.out.println("Order controller:");
        System.out.println(userService.getUserById(1)); // hits DB
        System.out.println(userService.getUserById(1)); // from cache

        System.out.println("\nProfile controller (same proxy, shared cache):");
        System.out.println(userService.getUserById(1)); // from cache — not fetched again
        System.out.println(userService.getUserById(2)); // hits DB

        // Cache logic lives in one place.
        // Want access control instead? Swap in an AccessControlProxy. Controllers don't change.
    }
}

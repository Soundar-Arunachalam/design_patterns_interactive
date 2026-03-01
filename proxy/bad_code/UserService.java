public class UserService {
    public String getUserById(int id) {
        System.out.println("  [DB] Fetching user " + id + " from database...");
        return "User-" + id;
    }
}

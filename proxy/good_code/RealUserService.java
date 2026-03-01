// Real service — just talks to the DB, no caching concern
public class RealUserService implements UserService {
    public String getUserById(int id) {
        System.out.println("  [DB] Fetching user " + id + " from database...");
        return "User-" + id;
    }
}

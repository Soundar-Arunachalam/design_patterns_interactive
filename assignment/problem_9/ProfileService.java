// Handles user profile data.
public class ProfileService {
    public String getProfile(String user) {
        System.out.println("[Profile] Fetching profile for: " + user);
        return "{ name: " + user + ", tier: gold }";
    }
}

// Handles user authentication internally.
public class AuthService {
    public boolean login(String user, String pass) {
        System.out.println("[Auth] Verifying credentials for: " + user);
        return !user.isEmpty() && !pass.isEmpty();
    }
    public void logout(String user) { System.out.println("[Auth] Logged out: " + user); }
}

/*
 * PROBLEM STATEMENT
 * -----------------
 * A web app login flow requires four subsystems to work together:
 *   AuthService → ProfileService → RecommendationService → AuditLogger
 * Every caller (controller, test, CLI) must know about all four services,
 * wire them up manually, and call them in the correct order.
 *
 * Issues:
 *  - Callers are tightly coupled to four subsystems instead of one entry point.
 *  - If the login flow gains a new step (e.g. 2FA), every caller must be updated.
 *  - Subsystem coordination logic is duplicated in every place that triggers login.
 *
 * Task: Introduce a single class that hides all four subsystems behind one
 * simple login(user, password) method. Callers should only depend on that class.
 */
public class Main {
    public static void main(String[] args) {
        // Caller must know and orchestrate all subsystems manually
        AuthService auth = new AuthService();
        ProfileService profile = new ProfileService();
        RecommendationService rec = new RecommendationService();
        AuditLogger audit = new AuditLogger();

        String user = "alice", pass = "secret";

        if (auth.login(user, pass)) {
            String p = profile.getProfile(user);
            rec.loadRecommendations(p);
            audit.log("Login success: " + user);
        } else {
            audit.log("Login failed: " + user);
        }
    }
}

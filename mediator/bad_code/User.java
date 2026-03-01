import java.util.List;

// Problem: every user holds a reference to every other user.
// Adding a new user means updating existing users' lists.
// Users are tightly coupled — can't exist independently.
public class User {
    public String name;
    private List<User> others; // each user must know all other users

    public User(String name, List<User> others) {
        this.name = name;
        this.others = others;
    }

    public void send(String message) {
        System.out.println("[" + name + "] sends: " + message);
        for (User u : others) {
            if (u != this) u.receive(name, message);
        }
    }

    public void receive(String from, String message) {
        System.out.println("  [" + name + "] received from " + from + ": " + message);
    }
}

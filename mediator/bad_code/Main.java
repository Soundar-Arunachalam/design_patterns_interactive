import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Must wire all users together manually before they can be used
        User alice = new User("Alice", null);
        User bob   = new User("Bob",   null);
        User carol = new User("Carol", null);

        alice = new User("Alice", Arrays.asList(bob, carol));
        bob   = new User("Bob",   Arrays.asList(alice, carol));
        carol = new User("Carol", Arrays.asList(alice, bob));

        alice.send("Hello everyone!");
        System.out.println("\nAdding a 4th user means re-wiring Alice, Bob, and Carol.");
    }
}

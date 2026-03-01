public class Main {
    public static void main(String[] args) {
        ChatRoom room = new ChatRoom();

        User alice = new User("Alice", room);
        User bob   = new User("Bob",   room);
        User carol = new User("Carol", room);

        room.addUser(alice);
        room.addUser(bob);
        room.addUser(carol);

        alice.send("Hello everyone!");
        System.out.println();
        bob.send("Hey Alice!");

        // Adding a 4th user? new User("Dave", room) + room.addUser(dave). Done.
        // Existing users are untouched.
    }
}

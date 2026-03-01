// User only knows the mediator — knows nothing about other users
public class User {
    public String name;
    private ChatMediator mediator;

    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void send(String message)               { mediator.sendMessage(message, this); }
    public void receive(String from, String msg)   { System.out.println("  [" + name + "] from " + from + ": " + msg); }
}

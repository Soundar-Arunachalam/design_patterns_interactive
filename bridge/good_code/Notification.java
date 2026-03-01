// Abstraction side of the bridge — what kind of notification it is
// Holds a reference to the implementation (sender) instead of inheriting it
public abstract class Notification {
    protected MessageSender sender;

    public Notification(MessageSender sender) {
        this.sender = sender;
    }

    public abstract void send(String message);
}

// Implementation side of the bridge — how a message is delivered
public interface MessageSender {
    void send(String prefix, String message);
}

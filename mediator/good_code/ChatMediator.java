// Mediator interface — the chat room is the single communication hub
public interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

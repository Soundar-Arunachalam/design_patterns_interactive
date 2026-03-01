import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user)  { users.add(user); }

    public void sendMessage(String message, User sender) {
        System.out.println("[" + sender.name + "] sends: " + message);
        for (User u : users) {
            if (u != sender) u.receive(sender.name, message);
        }
    }
}

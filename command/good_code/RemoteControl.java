import java.util.ArrayDeque;
import java.util.Deque;

// Invoker — doesn't know what commands do, just executes and tracks history
public class RemoteControl {
    private Deque<Command> history = new ArrayDeque<>();

    public void press(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            System.out.print("Undo -> ");
            history.pop().undo();
        }
    }
}

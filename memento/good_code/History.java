import java.util.ArrayDeque;
import java.util.Deque;

// Caretaker — stores mementos but never looks inside them
public class History {
    private Deque<EditorMemento> stack = new ArrayDeque<>();

    public void push(EditorMemento m) { stack.push(m); }
    public EditorMemento pop()        { return stack.isEmpty() ? null : stack.pop(); }
}

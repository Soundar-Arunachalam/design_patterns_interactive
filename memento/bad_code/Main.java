import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        // The caller is forced to manage the undo history — not the editor's job
        Deque<String> history = new ArrayDeque<>();

        history.push(editor.content);
        editor.type("Hello ");

        history.push(editor.content);
        editor.type("World");

        history.push(editor.content);
        editor.type("!!!");

        System.out.println("\n-- Undo --");
        history.pop(); // discard current
        editor.content = history.peek(); // reach into editor's internals
        System.out.println("After undo: " + editor.content);

        System.out.println("\nThe caller knows about 'content' — breaks if editor adds more state.");
    }
}

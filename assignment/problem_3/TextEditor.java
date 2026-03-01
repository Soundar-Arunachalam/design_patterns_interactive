import java.util.ArrayList;
import java.util.List;

// A simple text editor with basic operations.
// No history — undo is impossible.
public class TextEditor {
    private StringBuilder text = new StringBuilder();

    public void type(String s)   { text.append(s); }
    public void deleteLast(int n) {
        int start = Math.max(0, text.length() - n);
        text.delete(start, text.length());
    }
    public void bold(String s)   { text.append("**" + s + "**"); }

    public String getText() { return text.toString(); }
}

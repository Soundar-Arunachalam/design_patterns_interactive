import java.util.ArrayList;
import java.util.List;

// Problem: undo history stores raw strings — internal state is fully exposed.
// If TextEditor changes its state structure (e.g. adds font), the history list must change too.
// The caller (Main) manages undo — that's not its responsibility.
public class TextEditor {
    public String content = ""; // public — state is exposed for undo to work

    public void type(String text) {
        content += text;
        System.out.println("Content: " + content);
    }
}

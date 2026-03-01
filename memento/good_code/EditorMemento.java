// Memento — an opaque snapshot of editor state. Only TextEditor reads it.
public class EditorMemento {
    private final String content; // caller cannot access this directly

    EditorMemento(String content)  { this.content = content; }
    String getContent()            { return content; } // package-private on purpose
}

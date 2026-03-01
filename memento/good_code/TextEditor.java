// Originator — creates and restores mementos. State is private.
public class TextEditor {
    private String content = "";

    public void type(String text) {
        content += text;
        System.out.println("Content: " + content);
    }

    public EditorMemento save()                    { return new EditorMemento(content); }
    public void restore(EditorMemento m)           { content = m.getContent(); }
    public String getContent()                     { return content; }
}

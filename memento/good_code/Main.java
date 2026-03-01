public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        history.push(editor.save());
        editor.type("Hello ");

        history.push(editor.save());
        editor.type("World");

        history.push(editor.save());
        editor.type("!!!");

        System.out.println("\n-- Undo --");
        history.pop(); // discard current snapshot
        editor.restore(history.pop());
        System.out.println("After undo: " + editor.getContent());

        // Main never touches editor.content directly.
        // If TextEditor adds "font" field, only save()/restore() change — Main is unaffected.
    }
}

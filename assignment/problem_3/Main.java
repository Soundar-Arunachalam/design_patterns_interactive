/*
 * PROBLEM STATEMENT
 * -----------------
 * A simple text editor supports typing, deleting, and bolding text.
 * Users expect Ctrl+Z (undo) to revert the last action, but there is no
 * history — once an operation runs it cannot be reversed.
 *
 * Issues:
 *  - Operations are called directly; no record of what was done.
 *  - Implementing undo/redo would require duplicating inverse logic everywhere.
 *  - It's hard to queue or log operations for replay or audit purposes.
 *
 * Task: Refactor so every operation is encapsulated as an object with both
 * execute() and undo() methods, and a history stack enables multi-level undo.
 */
public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.type("Hello ");
        editor.type("World");
        System.out.println(editor.getText()); // Hello World

        editor.deleteLast(5);
        System.out.println(editor.getText()); // Hello

        // How do we undo the delete? There's no way.
        System.out.println("Cannot undo — history not tracked.");
    }
}

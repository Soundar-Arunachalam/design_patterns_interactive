package Solution;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.type("Hello ");
        editor.type("World");
        System.out.println(editor.getText()); // Hello World

        editor.deleteLast(5);
        System.out.println(editor.getText()); // Hello

        editor.undo();
        System.out.println(editor.getText()); // Hello World
    }
}

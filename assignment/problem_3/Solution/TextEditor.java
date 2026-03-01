package Solution;
import java.util.ArrayList;
import java.util.List;
public class TextEditor {
    private StringBuilder document;
    private List<Command> commandHistory;
    public TextEditor() {
        this.document = new StringBuilder();
        this.commandHistory = new ArrayList<>();    
    }

    public void type(String text) {
        TypeCommand command = new TypeCommand(document, text);
        command.execute();
        commandHistory.add(command);
    }

    public void bold(String text) {
        BoldCommand command = new BoldCommand(document, text);
        command.execute();
        commandHistory.add(command);
    }

    public void deleteLast(int length) {
        int startIndex = document.length() - length;
        if (startIndex >= 0) {
            DeleteLastCommand command = new DeleteLastCommand(document, startIndex);
            command.execute();
            commandHistory.add(command);    
        }
    }

    public String getText() {
        return document.toString();
    }
    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }
}

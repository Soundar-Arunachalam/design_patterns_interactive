package Solution;

public class TypeCommand implements Command {
    private String text;
    private StringBuilder document;

    public TypeCommand(StringBuilder document,String text) {
        this.text = text;
        this.document = document;
    }

    @Override
    public void execute() {
        document.append(text);
    }

    @Override
    public void undo() {
        int startIndex = document.length() - text.length();
        if (startIndex >= 0) {
            document.delete(startIndex, document.length());
        }
    }
    
}

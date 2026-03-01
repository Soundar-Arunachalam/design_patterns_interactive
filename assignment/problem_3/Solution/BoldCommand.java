package Solution;

public class BoldCommand implements Command {
    private StringBuilder document;
    private String text;

    public BoldCommand(StringBuilder document, String text) {
        this.document = document;
        this.text = text;
    }

    @Override
    public void execute() {
        document.append("**").append(text).append("**");
    }

    @Override
    public void undo() {
        
        int lengthToDelete = text.length() + 4; 
        int startIndex = document.length() - lengthToDelete;
        if (startIndex >= 0) {
            document.delete(startIndex, document.length());
        }
    }
    
}

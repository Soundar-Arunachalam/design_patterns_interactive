package Solution;

public class DeleteLastCommand implements Command {
    private StringBuilder document;
    private String deletedText;
    private int n;
    public DeleteLastCommand(StringBuilder document, int n) {
        this.document = document;
        this.n = n;
    }

    @Override
    public void execute() {
        if (document.length() > 0) {
            int start = Math.max(0, document.length() - n);
            deletedText = document.substring(start);
            document.delete(start, document.length());
        }
    }

    @Override
    public void undo() {
        if (deletedText != null) {
            document.append(deletedText);
        }
    }
    
}

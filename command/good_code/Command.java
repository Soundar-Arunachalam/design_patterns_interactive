// Commands are objects — they can be stored, queued, logged, or undone
public interface Command {
    void execute();
    void undo();
}

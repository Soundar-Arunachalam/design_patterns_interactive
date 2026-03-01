// Treat files and folders uniformly — the client never needs to check which is which
public interface FileSystemItem {
    void show(String indent);
    int fileCount();
}

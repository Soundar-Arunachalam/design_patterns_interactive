import java.util.ArrayList;
import java.util.List;

// Composite — contains other FileSystemItems (files or more folders)
public class Folder implements FileSystemItem {
    private String name;
    private List<FileSystemItem> children = new ArrayList<>();

    public Folder(String name) { this.name = name; }

    public void add(FileSystemItem item) { children.add(item); }

    public void show(String indent) {
        System.out.println(indent + "📁 " + name + "/");
        for (FileSystemItem child : children) {
            child.show(indent + "  "); // recursion works naturally
        }
    }

    public int fileCount() {
        int count = 0;
        for (FileSystemItem child : children) count += child.fileCount();
        return count;
    }
}

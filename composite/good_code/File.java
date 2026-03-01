// Leaf — no children
public class File implements FileSystemItem {
    private String name;

    public File(String name) { this.name = name; }

    public void show(String indent)  { System.out.println(indent + "📄 " + name); }
    public int fileCount()           { return 1; }
}

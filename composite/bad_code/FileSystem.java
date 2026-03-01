import java.util.ArrayList;
import java.util.List;

// Problem: files and folders are separate types with no common interface.
// To print or calculate size, the caller must check what type each item is.
// Adding depth (folders inside folders) turns this into a recursive nightmare.
public class FileSystem {
    List<String> files = new ArrayList<>();
    List<String> folders = new ArrayList<>();
    // Folders have contents, but we can't represent nested folders here cleanly

    public void addFile(String name) { files.add(name); }
    public void addFolder(String name) { folders.add(name); }

    public void showContents() {
        for (String folder : folders) {
            System.out.println("📁 " + folder + "/");
            // Can't recurse — there's no way to represent what's inside a folder
        }
        for (String file : files) {
            System.out.println("  📄 " + file);
        }
    }
}

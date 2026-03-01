public class Main {
    public static void main(String[] args) {
        FileSystem root = new FileSystem();
        root.addFolder("src");
        root.addFolder("test");
        root.addFile("README.md");
        root.addFile("pom.xml");

        root.showContents();

        System.out.println("\nHow do we show files inside 'src'?");
        System.out.println("How do we get total file count including subfolders?");
        System.out.println("This model can't do it — files and folders are treated differently.");
    }
}

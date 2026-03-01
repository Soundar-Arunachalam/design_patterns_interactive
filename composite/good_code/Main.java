public class Main {
    public static void main(String[] args) {
        Folder src = new Folder("src");
        src.add(new File("Main.java"));
        src.add(new File("PaymentService.java"));

        Folder test = new Folder("test");
        test.add(new File("PaymentServiceTest.java"));

        Folder root = new Folder("project");
        root.add(src);
        root.add(test);
        root.add(new File("README.md"));
        root.add(new File("pom.xml"));

        root.show("");
        System.out.println("\nTotal files: " + root.fileCount());
        // No instanceof checks. No separate handling for files vs folders.
    }
}

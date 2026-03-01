public class Main {
    public static void main(String[] args) {
        new Invoice("Acme Corp", 500.00).exportToPdf();
        new Receipt("Laptop",    999.99).exportToPdf();

        System.out.println("\nAdding JSON export means adding exportToJson()");
        System.out.println("to Invoice AND Receipt AND every other document class.");
    }
}

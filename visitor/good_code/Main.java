import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Document> docs = List.of(
                new Invoice("Acme Corp", 500.00),
                new Receipt("Laptop", 999.99),
                new Invoice("Globex", 1200.00)
        );

        System.out.println("-- PDF Export --");
        DocumentVisitor pdf = new PdfExportVisitor();
        for (Document d : docs) d.accept(pdf);

        System.out.println("\n-- XML Export --");
        DocumentVisitor xml = new XmlExportVisitor();
        for (Document d : docs) d.accept(xml);

        // Adding JSON export? One new class: JsonExportVisitor.
        // Invoice.java and Receipt.java are NOT touched.
    }
}

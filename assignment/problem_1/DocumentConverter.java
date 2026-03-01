// Converts a document to a given format.
// Adding a new format (e.g. EPUB) means editing this class.
public class DocumentConverter {
    public void convert(String content, String format) {
        if (format.equals("pdf")) {
            System.out.println("[PDF] Setting margins, embedding fonts...");
            System.out.println("[PDF] Exported: " + content);
        } else if (format.equals("html")) {
            System.out.println("[HTML] Wrapping in <html><body>...");
            System.out.println("[HTML] Exported: " + content);
        } else if (format.equals("markdown")) {
            System.out.println("[MD] Stripping formatting, adding # headers...");
            System.out.println("[MD] Exported: " + content);
        } else {
            System.out.println("Unknown format: " + format);
        }
    }
}

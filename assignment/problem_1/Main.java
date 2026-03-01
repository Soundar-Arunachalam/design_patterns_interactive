/*
 * PROBLEM STATEMENT
 * -----------------
 * A document management system exports documents in multiple formats:
 * PDF, HTML, and Markdown. A single class handles all formats with if-else.
 *
 * Issues:
 *  - Adding a new format (e.g. EPUB) requires modifying DocumentConverter.
 *  - Each format's logic is tangled together — hard to test in isolation.
 *  - The class grows every time a new format is introduced.
 *
 * Task: Refactor using a design pattern so each format is a separate class
 * and DocumentConverter never needs to change when new formats are added.
 */
public class Main {
    public static void main(String[] args) {
        DocumentConverter converter = new DocumentConverter();
        String content = "Quarterly Sales Report Q1 2026";

        converter.convert(content, "pdf");
        converter.convert(content, "html");
        converter.convert(content, "markdown");
    }
}

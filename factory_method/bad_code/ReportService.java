// Problem: creation logic and usage logic are tangled together.
// Adding XML or HTML format means editing this class.
public class ReportService {

    public void exportReport(String format, String data) {
        if (format.equals("pdf")) {
            System.out.println("Setting up PDF layout...");
            System.out.println("Rendering PDF: " + data);
            System.out.println("Saved: report.pdf");

        } else if (format.equals("excel")) {
            System.out.println("Setting up spreadsheet columns...");
            System.out.println("Writing rows: " + data);
            System.out.println("Saved: report.xlsx");

        } else if (format.equals("csv")) {
            System.out.println("Comma-separating values...");
            System.out.println("Writing: " + data);
            System.out.println("Saved: report.csv");

        } else {
            throw new IllegalArgumentException("Unknown format: " + format);
        }
    }
}

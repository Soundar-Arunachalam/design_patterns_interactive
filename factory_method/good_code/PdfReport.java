public class PdfReport implements Report {
    public void generate(String data) { System.out.println("Rendering PDF: " + data); }
    public void save()               { System.out.println("Saved: report.pdf"); }
}

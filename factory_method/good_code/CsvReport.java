public class CsvReport implements Report {
    public void generate(String data) { System.out.println("Comma-separating: " + data); }
    public void save()               { System.out.println("Saved: report.csv"); }
}

public class ExcelReport implements Report {
    public void generate(String data) { System.out.println("Writing Excel rows: " + data); }
    public void save()               { System.out.println("Saved: report.xlsx"); }
}

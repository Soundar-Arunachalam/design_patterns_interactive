// Creator — knows HOW to export a report, but not WHICH kind to create.
// Subclasses override createReport() to supply the concrete type.
public abstract class ReportExporter {

    abstract Report createReport(); // factory method

    public void exportReport(String data) {
        Report report = createReport();
        report.generate(data);
        report.save();
    }
}

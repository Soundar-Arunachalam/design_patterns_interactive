public class Main {
    public static void main(String[] args) {
        // Swap the exporter — exportReport() logic never changes
        ReportExporter exporter;

        exporter = new PdfReportExporter();
        exporter.exportReport("Q1 Sales Data");
        System.out.println();

        exporter = new ExcelReportExporter();
        exporter.exportReport("Q1 Sales Data");
        System.out.println();

        exporter = new CsvReportExporter();
        exporter.exportReport("Q1 Sales Data");
        System.out.println();

        // Adding HTML export?
        // 1. Create HtmlReport.java
        // 2. Create HtmlReportExporter.java
        // ReportExporter.java is NOT touched.
    }
}

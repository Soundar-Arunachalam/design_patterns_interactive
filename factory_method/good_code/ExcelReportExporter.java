public class ExcelReportExporter extends ReportExporter {
    Report createReport() { return new ExcelReport(); }
}

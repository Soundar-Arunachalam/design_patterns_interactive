public class Main {
    public static void main(String[] args) {
        ReportService service = new ReportService();
        service.exportReport("pdf", "Q1 Sales Data");
        System.out.println();
        service.exportReport("excel", "Q1 Sales Data");
        System.out.println();
        service.exportReport("csv", "Q1 Sales Data");
        System.out.println();
        System.out.println("Boss: 'We need HTML export by tomorrow.'");
        System.out.println("You: *opens ReportService.java and adds another else-if*");
    }
}

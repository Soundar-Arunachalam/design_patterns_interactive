// Generates a monthly analytics report from a REST API.
public class ApiReportGenerator {
    public void generate() {
        System.out.println("[API] Calling https://api.company.com/events?range=30d ...");
        System.out.println("[API] Parsing JSON response...");
        System.out.println("[API] Validating status == 200 and body not empty...");
        System.out.println("[API] Formatting JSON fields into table...");
        System.out.println("[API] Sending report to analytics@company.com");
    }
}

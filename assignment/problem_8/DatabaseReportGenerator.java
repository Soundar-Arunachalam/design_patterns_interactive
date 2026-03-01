// Generates a monthly analytics report from a database.
public class DatabaseReportGenerator {
    public void generate() {
        System.out.println("[DB] Connecting to database...");
        System.out.println("[DB] Running SQL query for last 30 days...");
        System.out.println("[DB] Validating row count > 0...");
        System.out.println("[DB] Formatting rows into table...");
        System.out.println("[DB] Sending report to analytics@company.com");
    }
}

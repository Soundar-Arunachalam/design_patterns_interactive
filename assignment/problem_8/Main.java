/*
 * PROBLEM STATEMENT
 * -----------------
 * An analytics platform generates monthly reports from two data sources:
 * a database and a REST API. Both follow the same pipeline:
 *   1. Fetch data   2. Validate   3. Format   4. Send by email
 * But each class duplicates the entire pipeline with only the fetch and format
 * steps differing.
 *
 * Issues:
 *  - The validate and send steps are copy-pasted in every generator class.
 *  - Adding a third source (e.g. CSV file) means duplicating the pipeline again.
 *  - A bug fix in the "send email" step must be applied in every class separately.
 *
 * Task: Refactor so the overall pipeline is defined once in a base class with
 * the common steps implemented there, and only the source-specific steps
 * are overridden by subclasses.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Database Report ===");
        new DatabaseReportGenerator().generate();

        System.out.println("\n=== API Report ===");
        new ApiReportGenerator().generate();
    }
}

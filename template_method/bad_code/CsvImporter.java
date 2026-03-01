// Problem: the overall pipeline (parse → validate → save → confirm) is duplicated in both importers.
// Change the pipeline order → update both classes.
// The shared skeleton is invisible — it's re-typed every time.
public class CsvImporter {
    public void importData(String file) {
        System.out.println("Parsing CSV: " + file);    // step 1 — varies
        System.out.println("Validating rows...");       // step 2 — same
        System.out.println("Saving to database...");    // step 3 — same
        System.out.println("Sending CSV import email"); // step 4 — varies
    }
}

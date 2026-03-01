// Same pipeline duplicated — validate and save steps are copy-pasted
public class JsonImporter {
    public void importData(String file) {
        System.out.println("Parsing JSON: " + file);    // step 1 — varies
        System.out.println("Validating rows...");        // duplicated!
        System.out.println("Saving to database...");     // duplicated!
        System.out.println("Posting JSON webhook");      // step 4 — varies
    }
}

public class Main {
    public static void main(String[] args) {
        new CsvImporter().importData("users.csv");
        System.out.println();
        new JsonImporter().importData("products.json");

        System.out.println("\n'Validating rows' and 'Saving to database' are copy-pasted.");
        System.out.println("Add XML importer → copy-paste again.");
    }
}

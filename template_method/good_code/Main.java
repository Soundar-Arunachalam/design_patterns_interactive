public class Main {
    public static void main(String[] args) {
        new CsvImporter().importData("users.csv");
        System.out.println();
        new JsonImporter().importData("products.json");

        // validate() and save() live in exactly one place — DataImporter.
        // Adding XmlImporter? Override parse() and notify() only.
    }
}

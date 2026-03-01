public class CsvImporter extends DataImporter {
    protected void parse(String file)  { System.out.println("Parsing CSV: " + file); }
    protected void notify()            { System.out.println("Sending CSV import email"); }
}

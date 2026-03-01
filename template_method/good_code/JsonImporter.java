public class JsonImporter extends DataImporter {
    protected void parse(String file)  { System.out.println("Parsing JSON: " + file); }
    protected void notify()            { System.out.println("Posting JSON webhook"); }
}

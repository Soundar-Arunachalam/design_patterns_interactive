public class Main {
    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine();
        String doc = "Java Spring Boot developer with cloud experience";

        System.out.println(engine.evaluate("java AND spring", doc));         // true
        System.out.println(engine.evaluate("python OR java", doc));          // true
        System.out.println(engine.evaluate("python", doc));                  // false

        System.out.println("\nTry 'java AND (spring OR boot)': not supported.");
    }
}

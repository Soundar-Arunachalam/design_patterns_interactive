// Problem: parsing logic and evaluation logic are tangled in one giant method.
// Adding a new keyword (e.g. "xor") means editing this method with more if-else.
// Complex nested expressions are not supported at all.
public class SearchEngine {

    public boolean evaluate(String query, String document) {
        // Handles only flat "term AND term" or "term OR term" — no nesting
        if (query.contains(" AND ")) {
            String[] parts = query.split(" AND ");
            return document.contains(parts[0].trim()) && document.contains(parts[1].trim());
        } else if (query.contains(" OR ")) {
            String[] parts = query.split(" OR ");
            return document.contains(parts[0].trim()) || document.contains(parts[1].trim());
        } else {
            return document.contains(query.trim());
        }
        // "java AND (spring OR boot)"? Impossible with this approach.
    }
}

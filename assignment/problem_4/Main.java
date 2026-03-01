/*
 * PROBLEM STATEMENT
 * -----------------
 * A reading list app stores books in a public array. Callers traverse it
 * by directly accessing the array and its size field.
 *
 * Issues:
 *  - If the internal structure changes (array → LinkedList), all callers break.
 *  - There is no way to offer different traversal orders (e.g. alphabetical,
 *    reverse, filtered by genre) without rewriting caller code each time.
 *  - The collection exposes its internals — encapsulation is violated.
 *
 * Task: Refactor so the collection is private and callers use a common
 * traversal interface, without knowing the underlying data structure.
 */
public class Main {
    public static void main(String[] args) {
        ReadingList list = new ReadingList();

        // Caller knows it's an array and uses index-based access
        for (int i = 0; i < list.size; i++) {
            System.out.println("Book: " + list.books[i]);
        }

        // What if we want reverse order or alphabetical? Caller must handle it.
    }
}

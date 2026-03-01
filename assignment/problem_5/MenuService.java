import java.util.List;

// Represents a menu item (leaf) or a sub-menu (branch).
// Treats items and sub-menus differently — no uniform interface.
public class MenuService {

    // Prints a flat list of items — can't nest sub-menus
    public void printMenu(List<String> items) {
        for (String item : items) {
            System.out.println("  - " + item);
        }
    }

    // Separate method needed just to handle sub-menus
    public void printSubMenu(String subMenuName, List<String> items) {
        System.out.println("[" + subMenuName + "]");
        for (String item : items) {
            System.out.println("    - " + item);
        }
    }
}

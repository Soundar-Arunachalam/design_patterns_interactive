import java.util.List;

/*
 * PROBLEM STATEMENT
 * -----------------
 * A restaurant app displays a menu. Menus can contain individual items
 * (e.g. "Burger") or sub-menus (e.g. "Drinks" → ["Coke", "Water"]).
 * Currently, items and sub-menus are handled by completely different methods.
 *
 * Issues:
 *  - Cannot nest sub-menus inside sub-menus (e.g. "Drinks > Hot > Coffee").
 *  - Caller must track what is an item and what is a sub-menu separately.
 *  - Adding a third level requires yet another method and more caller complexity.
 *
 * Task: Refactor so individual items and sub-menus share a common interface,
 * and sub-menus can contain other sub-menus to any depth — callers treat
 * them all the same way.
 */
public class Main {
    public static void main(String[] args) {
        MenuService service = new MenuService();

        System.out.println("=== Main Menu ===");
        service.printMenu(List.of("Burger", "Pizza", "Pasta"));

        service.printSubMenu("Drinks", List.of("Coke", "Water", "Juice"));
        service.printSubMenu("Desserts", List.of("Ice Cream", "Cake"));

        // How do we show "Drinks > Hot > Coffee"? There's no way.
    }
}

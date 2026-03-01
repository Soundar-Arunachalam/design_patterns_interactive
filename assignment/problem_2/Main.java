/*
 * PROBLEM STATEMENT
 * -----------------
 * A role-playing game supports two themes: Fantasy and Sci-Fi.
 * Each theme requires a matching set of equipment: Weapon, Armor, and Mount.
 * Currently a single factory uses if-else in three separate methods.
 *
 * Issues:
 *  - Adding a new theme (e.g. "Steampunk") requires editing all three methods.
 *  - Nothing prevents mixing equipment from different themes (sword + hover bike).
 *  - Theme logic is scattered across three unrelated methods.
 *
 * Task: Refactor so each theme's full equipment set is created together,
 * and adding a new theme only requires adding new classes — not editing existing ones.
 */
public class Main {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();
        String theme = "fantasy";

        System.out.println("Theme: " + theme);
        System.out.println("Weapon: " + factory.createWeapon(theme));
        System.out.println("Armor:  " + factory.createArmor(theme));
        System.out.println("Mount:  " + factory.createMount(theme));
    }
}

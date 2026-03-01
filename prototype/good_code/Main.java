public class Main {
    public static void main(String[] args) {
        CharacterRegistry registry = new CharacterRegistry();

        // Register templates once — stats defined in a single place
        registry.register("warrior", new GameCharacter("Warrior", 200, 50, 80, 30, "Sword", "Plate Armor"));
        registry.register("mage",    new GameCharacter("Mage",    100, 90, 20, 60, "Staff", "Robe"));
        registry.register("rogue",   new GameCharacter("Rogue",   150, 70, 40, 90, "Dagger", "Leather"));

        // Clone — no field copying, no risk of missing a new field
        GameCharacter p1 = registry.create("warrior", "Thor");
        GameCharacter p2 = registry.create("warrior", "Hera"); // same template, different player
        GameCharacter p3 = registry.create("mage", "Merlin");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // Copies are independent — modifying p1 doesn't affect p2
        p1.attack = 999;
        System.out.println("\nAfter buff -> " + p1);
        System.out.println("Unchanged  -> " + p2);
    }
}

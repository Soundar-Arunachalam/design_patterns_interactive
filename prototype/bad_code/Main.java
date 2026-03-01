// Problem: to give two players the same starting character, every field is copy-pasted.
// Add a new field (e.g. "mana") to GameCharacter → must update every copy here too.
public class Main {
    public static void main(String[] args) {
        // Player 1 picks Warrior
        GameCharacter p1 = new GameCharacter(
                "Thor", "Warrior", 200, 50, 80, 30, "Sword", "Plate Armor");

        // Player 2 also picks Warrior — all values duplicated
        GameCharacter p2 = new GameCharacter(
                "Hera", "Warrior", 200, 50, 80, 30, "Sword", "Plate Armor");
        // What if defense was updated to 90? Easy to miss p2's update.

        // Player 3 picks Mage — another full copy
        GameCharacter p3 = new GameCharacter(
                "Merlin", "Mage", 100, 90, 20, 60, "Staff", "Robe");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}

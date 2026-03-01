public class GameCharacter {
    String name, characterClass, weapon, armor;
    int health, attack, defense, speed;

    public GameCharacter(String name, String characterClass,
                         int health, int attack, int defense, int speed,
                         String weapon, String armor) {
        this.name = name; this.characterClass = characterClass;
        this.health = health; this.attack = attack;
        this.defense = defense; this.speed = speed;
        this.weapon = weapon; this.armor = armor;
    }

    public String toString() {
        return "[" + characterClass + "] " + name
                + " HP:" + health + " ATK:" + attack + " DEF:" + defense;
    }
}

public class GameCharacter implements Cloneable {
    String name, characterClass, weapon, armor;
    int health, attack, defense, speed;

    public GameCharacter(String characterClass,
                         int health, int attack, int defense, int speed,
                         String weapon, String armor) {
        this.characterClass = characterClass;
        this.health = health; this.attack = attack;
        this.defense = defense; this.speed = speed;
        this.weapon = weapon; this.armor = armor;
    }

    // Clone the prototype and give the copy a player name
    public GameCharacter cloneFor(String playerName) {
        try {
            GameCharacter copy = (GameCharacter) super.clone();
            copy.name = playerName;
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "[" + characterClass + "] " + name
                + " HP:" + health + " ATK:" + attack + " DEF:" + defense;
    }
}

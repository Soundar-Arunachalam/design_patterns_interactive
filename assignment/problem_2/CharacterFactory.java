// Creates weapons, armor, and mounts for different game themes.
// Adding a new theme means editing all three methods.
public class CharacterFactory {
    public String createWeapon(String theme) {
        if (theme.equals("fantasy"))    return "Sword";
        else if (theme.equals("scifi")) return "Laser Gun";
        else return "Unknown weapon";
    }

    public String createArmor(String theme) {
        if (theme.equals("fantasy"))    return "Chain Mail";
        else if (theme.equals("scifi")) return "Titanium Suit";
        else return "Unknown armor";
    }

    public String createMount(String theme) {
        if (theme.equals("fantasy"))    return "Horse";
        else if (theme.equals("scifi")) return "Hover Bike";
        else return "Unknown mount";
    }
}

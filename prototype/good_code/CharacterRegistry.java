import java.util.HashMap;
import java.util.Map;

// Holds one prototype per character type — registered once, cloned many times
public class CharacterRegistry {
    private final Map<String, GameCharacter> templates = new HashMap<>();

    public void register(String type, GameCharacter template) {
        templates.put(type, template);
    }

    public GameCharacter create(String type, String playerName) {
        GameCharacter template = templates.get(type);
        if (template == null) throw new IllegalArgumentException("Unknown type: " + type);
        return template.cloneFor(playerName);
    }
}

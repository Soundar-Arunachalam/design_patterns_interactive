import java.util.HashMap;
import java.util.Map;

// Cache — ensures only one ParticleType object exists per type name
public class ParticleFactory {
    private static final Map<String, ParticleType> cache = new HashMap<>();

    public static ParticleType getType(String type, String color, String sprite) {
        return cache.computeIfAbsent(type, k -> new ParticleType(type, color, sprite));
    }
}

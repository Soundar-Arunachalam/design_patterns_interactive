// Flyweight — stores only shared (intrinsic) state. One instance per particle type.
public class ParticleType {
    private final String type;
    private final String color;
    private final String sprite;

    public ParticleType(String type, String color, String sprite) {
        this.type = type; this.color = color; this.sprite = sprite;
        System.out.println("  [ParticleType created] " + type);
    }

    // Extrinsic state (position, velocity) is passed in — not stored here
    public void render(int x, int y) {
        System.out.println(type + " [" + color + "] at (" + x + "," + y + ") sprite=" + sprite);
    }
}

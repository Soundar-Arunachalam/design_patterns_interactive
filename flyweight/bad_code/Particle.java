// Every particle on screen is a full object — stores ALL data including shared data.
// In a game with 100,000 particles, "type", "color", "sprite" are duplicated 100,000 times.
public class Particle {
    // intrinsic (shared, same for all particles of same type) — wasteful to duplicate
    String type;
    String color;
    String sprite;

    // extrinsic (unique per particle)
    int x, y;
    int velocityX, velocityY;

    public Particle(String type, String color, String sprite, int x, int y, int vx, int vy) {
        this.type = type; this.color = color; this.sprite = sprite;
        this.x = x; this.y = y;
        this.velocityX = vx; this.velocityY = vy;
    }

    public void render() {
        System.out.println(type + " [" + color + "] at (" + x + "," + y + ") sprite=" + sprite);
    }
}

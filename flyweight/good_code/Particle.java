// Particle only stores what's unique to it — position and velocity.
// The shared data lives in ParticleType (the flyweight).
public class Particle {
    private ParticleType type; // shared
    private int x, y;          // unique

    public Particle(ParticleType type, int x, int y) {
        this.type = type; this.x = x; this.y = y;
    }

    public void render() { type.render(x, y); }
}

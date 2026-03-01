import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Particle> particles = new ArrayList<>();

        // Simulate spawning 6 particles — all "fire" or "smoke"
        // The strings "fire", "red", "fire_sprite.png" are duplicated in every instance
        particles.add(new Particle("fire",  "red",  "fire_sprite.png",  10, 20, 1, -1));
        particles.add(new Particle("fire",  "red",  "fire_sprite.png",  15, 25, 2, -1));
        particles.add(new Particle("fire",  "red",  "fire_sprite.png",  20, 30, 1, -2));
        particles.add(new Particle("smoke", "gray", "smoke_sprite.png", 12, 22, 0, -1));
        particles.add(new Particle("smoke", "gray", "smoke_sprite.png", 18, 28, 1, -1));
        particles.add(new Particle("smoke", "gray", "smoke_sprite.png", 25, 35, 0, -2));

        for (Particle p : particles) p.render();

        System.out.println("\nWith 100,000 particles, 'type','color','sprite'");
        System.out.println("are stored 100,000 times. Should be stored ONCE per type.");
    }
}

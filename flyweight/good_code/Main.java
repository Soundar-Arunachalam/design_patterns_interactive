import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Particle> particles = new ArrayList<>();

        // ParticleType "fire" is created once — shared across all fire particles
        ParticleType fire  = ParticleFactory.getType("fire",  "red",  "fire_sprite.png");
        ParticleType smoke = ParticleFactory.getType("smoke", "gray", "smoke_sprite.png");

        particles.add(new Particle(fire,  10, 20));
        particles.add(new Particle(fire,  15, 25));
        particles.add(new Particle(fire,  20, 30));
        particles.add(new Particle(smoke, 12, 22));
        particles.add(new Particle(smoke, 18, 28));
        particles.add(new Particle(smoke, 25, 35));

        System.out.println("\nRendering:");
        for (Particle p : particles) p.render();

        System.out.println("\n6 particles, but only 2 ParticleType objects in memory.");
        System.out.println("With 100,000 particles of 5 types → still only 5 ParticleType objects.");
    }
}

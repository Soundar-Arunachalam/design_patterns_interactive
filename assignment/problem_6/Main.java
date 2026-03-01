/*
 * PROBLEM STATEMENT
 * -----------------
 * A traffic light cycles through Red → Green → Yellow → Red.
 * It also supports an emergency override that forces the light to green.
 * All state logic is managed with magic strings and if-else in every method.
 *
 * Issues:
 *  - Adding a new state (e.g. "flashing_yellow" for roadworks) requires editing
 *    every method in the class.
 *  - Magic string state = "red" — a typo like "redd" will not be caught at compile time.
 *  - State-specific behaviour is scattered across multiple methods instead of grouped.
 *
 * Task: Refactor so each state is a class that encapsulates its own behaviour.
 * Adding a new state should require only a new class — no edits to existing ones.
 */
public class Main {
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();

        light.next(); // Green
        light.next(); // Yellow
        light.next(); // Red

        System.out.println();
        light.emergencyOverride(); // force green from red
        System.out.println("Current: " + light.getState());
    }
}

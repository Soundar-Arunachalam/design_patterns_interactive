// A traffic light controller with magic string state and if-else in every action.
// Adding a new state (e.g. "flashing_yellow") means editing all methods.
public class TrafficLight {
    private String state = "red"; // magic string — typos compile fine

    public void next() {
        if (state.equals("red"))    { state = "green";  System.out.println("Green — Go!"); }
        else if (state.equals("green"))  { state = "yellow"; System.out.println("Yellow — Slow down!"); }
        else if (state.equals("yellow")) { state = "red";    System.out.println("Red — Stop!"); }
    }

    public void emergencyOverride() {
        if (state.equals("red"))    System.out.println("[Emergency] Switching red → green.");
        else if (state.equals("green"))  System.out.println("[Emergency] Already green, hold.");
        else if (state.equals("yellow")) System.out.println("[Emergency] Switching yellow → green.");
        state = "green";
    }

    public String getState() { return state; }
}

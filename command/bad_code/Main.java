public class Main {
    public static void main(String[] args) {
        SmartHomeRemote remote = new SmartHomeRemote();
        remote.pressLightOn();
        remote.pressVolumeUp();
        remote.pressLightOff();

        System.out.println("\nUser: 'Undo last action.'");
        System.out.println("You: *has no undo mechanism — no command history exists*");
    }
}

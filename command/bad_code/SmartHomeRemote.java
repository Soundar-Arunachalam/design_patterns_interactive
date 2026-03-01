// Problem: actions are triggered directly — no history, no undo.
// To add undo, you'd have to save state manually everywhere and remember reverse operations.
public class SmartHomeRemote {
    private boolean lightOn = false;
    private int volume = 50;

    // Command LightOnCommand lightOnCommand = new LightOnCommand(light);
    public void pressLightOn()  { lightOn = true;  System.out.println("Light ON"); }
    // Command LightOffCommand lightOffCommand = new LightOffCommand(light);
    public void pressLightOff() { lightOn = false; System.out.println("Light OFF"); }
    // Command VolumeUpCommand volumeUpCommand = new VolumeUpCommand(speaker);
    public void pressVolumeUp() { volume += 10;    System.out.println("Volume: " + volume); }

    // Undo? You'd need to store what the previous state was for each action — messy.
    // Also: how do you schedule a command to run later? Or queue it? You can't.
}

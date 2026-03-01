public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();

        // --- Lights ---
        Light light = new Light();
        System.out.println("=== Lights ===");
        remote.press(new LightOnCommand(light));
        remote.press(new LightOffCommand(light));
        remote.press(new LightOnCommand(light));

        System.out.println("\n-- Undo light --");
        remote.undoLast(); // undoes LightOn  → turns off
        remote.undoLast(); // undoes LightOff → turns on

        // --- Washing Machine ---
        WashingMachine washer = new WashingMachine();
        System.out.println("\n=== Washing Machine ===");
        remote.press(new WashingMachineOnCommand(washer));
        remote.press(new WashingMachineRinseCommand(washer));
        remote.press(new WashingMachineSpinCommand(washer));
        remote.press(new WashingMachineDryCommand(washer));
        remote.press(new WashingMachineOffCommand(washer));

        System.out.println("\n-- Undo last two washing machine steps --");
        remote.undoLast(); // undoes Off  → back On
        remote.undoLast(); // undoes Dry  → Dry cancelled

        // RemoteControl and Command interface never changed — new device, zero edits to existing code.
    }
}

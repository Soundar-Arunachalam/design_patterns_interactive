public class WashingMachineRinseCommand implements Command {
    private WashingMachine machine;
    public WashingMachineRinseCommand(WashingMachine machine) { this.machine = machine; }

    public void execute() { machine.rinse(); }
    public void undo()    { System.out.println("Washing Machine RINSE cancelled"); }
}

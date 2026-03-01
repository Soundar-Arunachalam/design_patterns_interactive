public class WashingMachineOffCommand implements Command {
    private WashingMachine machine;
    public WashingMachineOffCommand(WashingMachine machine) { this.machine = machine; }

    public void execute() { machine.off(); }
    public void undo()    { machine.on();  }
}

public class WashingMachineOnCommand implements Command {
    private WashingMachine machine;
    public WashingMachineOnCommand(WashingMachine machine) { this.machine = machine; }

    public void execute() { machine.on();  }
    public void undo()    { machine.off(); }
}

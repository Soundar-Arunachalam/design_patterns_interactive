public class WashingMachineSpinCommand implements Command {
    private WashingMachine machine;
    public WashingMachineSpinCommand(WashingMachine machine) { this.machine = machine; }

    public void execute() { machine.spin();  }
    public void undo()    { System.out.println("Washing Machine SPIN cancelled"); }
}

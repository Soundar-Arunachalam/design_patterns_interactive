public class WashingMachineDryCommand implements Command {
    private WashingMachine machine;
    public WashingMachineDryCommand(WashingMachine machine) { this.machine = machine; }

    public void execute() { machine.dry();  }
    public void undo()    { System.out.println("Washing Machine DRY cancelled"); }
}

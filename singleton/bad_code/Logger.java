public class Logger {
    private String owner;

    public Logger(String owner) {
        this.owner = owner;
        System.out.println("  !! New Logger instance created for: " + owner);
    }

    public void log(String message) {
        System.out.println("[" + owner + "] " + message);
    }
}

public class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("  Logger initialized (only once)");
    }

    // synchronized keeps this safe when multiple threads call it at startup
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

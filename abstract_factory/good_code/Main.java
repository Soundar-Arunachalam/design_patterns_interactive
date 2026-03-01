public class Main {
    public static void main(String[] args) {
        System.out.println("--- Web ---");
        new UIService(new WebUIFactory()).renderLoginScreen();

        System.out.println("\n--- Mobile ---");
        new UIService(new MobileUIFactory()).renderLoginScreen();

        // Adding Desktop?
        // 1. Create DesktopButton, DesktopDialog, DesktopInput
        // 2. Create DesktopUIFactory
        // UIService.java is NOT touched.
    }
}

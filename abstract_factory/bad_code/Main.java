public class Main {
    public static void main(String[] args) {
        System.out.println("--- Web ---");
        UIService web = new UIService("web");
        web.renderButton("Submit");
        web.renderDialog("Are you sure?");
        web.renderInput("Enter email");

        System.out.println("\n--- Mobile ---");
        UIService mobile = new UIService("mobile");
        mobile.renderButton("Submit");
        mobile.renderDialog("Are you sure?");
        mobile.renderInput("Enter email");

        System.out.println("\nBoss: 'Add Desktop support.'");
        System.out.println("You: *adds else-if in renderButton, renderDialog, renderInput...*");
    }
}

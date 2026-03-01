// Problem: every UI element has its own if-else block.
// Adding a new platform (e.g. "desktop") means editing every method.
// Nothing stops you from mixing Web buttons with Mobile dialogs.
public class UIService {
    private String platform;

    public UIService(String platform) {
        this.platform = platform;
    }

    public void renderButton(String label) {
        if (platform.equals("web")) {
            System.out.println("<button>" + label + "</button>");
        } else if (platform.equals("mobile")) {
            System.out.println("[TouchableOpacity] " + label);
        }
    }

    public void renderDialog(String message) {
        if (platform.equals("web")) {
            System.out.println("<dialog>" + message + "</dialog>");
        } else if (platform.equals("mobile")) {
            System.out.println("[Alert] " + message);
        }
    }

    public void renderInput(String placeholder) {
        if (platform.equals("web")) {
            System.out.println("<input placeholder='" + placeholder + "' />");
        } else if (platform.equals("mobile")) {
            System.out.println("[TextInput hint='" + placeholder + "']");
        }
    }
}

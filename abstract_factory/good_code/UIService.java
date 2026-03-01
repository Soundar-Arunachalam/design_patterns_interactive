// UIService has zero knowledge of web or mobile — it just uses the factory.
public class UIService {
    private final Button button;
    private final Dialog dialog;
    private final Input  input;

    public UIService(UIFactory factory) {
        this.button = factory.createButton();
        this.dialog = factory.createDialog();
        this.input  = factory.createInput();
    }

    public void renderLoginScreen() {
        input.render("Enter email");
        button.render("Login");
        dialog.show("Welcome back!");
    }
}

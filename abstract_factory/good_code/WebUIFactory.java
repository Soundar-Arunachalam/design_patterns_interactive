public class WebUIFactory implements UIFactory {
    public Button createButton() { return new WebButton(); }
    public Dialog createDialog() { return new WebDialog(); }
    public Input  createInput()  { return new WebInput();  }
}

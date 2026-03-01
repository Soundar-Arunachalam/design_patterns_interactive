public class MobileUIFactory implements UIFactory {
    public Button createButton() { return new MobileButton(); }
    public Dialog createDialog() { return new MobileDialog(); }
    public Input  createInput()  { return new MobileInput();  }
}

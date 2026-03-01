// The factory guarantees all components belong to the same platform — no mixing.
public interface UIFactory {
    Button createButton();
    Dialog createDialog();
    Input  createInput();
}

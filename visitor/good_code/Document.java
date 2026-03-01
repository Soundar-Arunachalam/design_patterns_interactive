// Documents just accept a visitor — they don't know what the visitor does
public interface Document {
    void accept(DocumentVisitor visitor);
}

public interface DocumentVisitor {
    void visit(Invoice invoice);
    void visit(Receipt receipt);
}

public class Receipt implements Document {
    public String item;
    public double price;

    public Receipt(String item, double price) {
        this.item = item; this.price = price;
    }

    public void accept(DocumentVisitor visitor) { visitor.visit(this); }
}

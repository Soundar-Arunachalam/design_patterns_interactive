public class Invoice implements Document {
    public String clientName;
    public double amount;

    public Invoice(String clientName, double amount) {
        this.clientName = clientName; this.amount = amount;
    }

    public void accept(DocumentVisitor visitor) { visitor.visit(this); }
}

// Problem: every new operation (export, tax, discount) requires adding a method
// to every document type. The document classes grow forever.
public class Invoice {
    public String clientName;
    public double amount;

    public Invoice(String clientName, double amount) {
        this.clientName = clientName; this.amount = amount;
    }

    public void exportToPdf() { System.out.println("PDF invoice for " + clientName + ": $" + amount); }
    public void exportToXml() { System.out.println("<invoice client='" + clientName + "' amount='" + amount + "'/>"); }
    // Next sprint: exportToJson(), calculateTax(), applyDiscount()... all go here
}

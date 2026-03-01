// Each new operation is duplicated across all document types
public class Receipt {
    public String item;
    public double price;

    public Receipt(String item, double price) {
        this.item = item; this.price = price;
    }

    public void exportToPdf() { System.out.println("PDF receipt: " + item + " $" + price); }
    public void exportToXml() { System.out.println("<receipt item='" + item + "' price='" + price + "'/>"); }
}

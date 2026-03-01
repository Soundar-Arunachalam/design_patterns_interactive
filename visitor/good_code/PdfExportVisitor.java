public class PdfExportVisitor implements DocumentVisitor {
    public void visit(Invoice i) { System.out.println("PDF invoice for " + i.clientName + ": $" + i.amount); }
    public void visit(Receipt r) { System.out.println("PDF receipt: " + r.item + " $" + r.price); }
}

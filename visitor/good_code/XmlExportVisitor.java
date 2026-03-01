public class XmlExportVisitor implements DocumentVisitor {
    public void visit(Invoice i) { System.out.println("<invoice client='" + i.clientName + "' amount='" + i.amount + "'/>"); }
    public void visit(Receipt r) { System.out.println("<receipt item='" + r.item + "' price='" + r.price + "'/>"); }
}

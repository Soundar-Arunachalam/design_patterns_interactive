```
┌──────────────────────────────────────────────────────────┐
│             visitor / good_code — Class Diagram          │
└──────────────────────────────────────────────────────────┘

         «interface»                    «interface»
  ┌────────────────────┐        ┌────────────────────────────┐
  │      Document      │        │      DocumentVisitor       │
  ├────────────────────┤        ├────────────────────────────┤
  │ + accept(          │        │ + visit(Invoice): void     │
  │   DocumentVisitor) │        │ + visit(Receipt): void     │
  └────────────────────┘        └────────────────────────────┘
      ▲         ▲                    ▲              ▲
      │         │                    │              │
  ┌───────┐ ┌────────┐    ┌──────────────────┐ ┌──────────────────┐
  │Invoice│ │Receipt │    │PdfExportVisitor  │ │XmlExportVisitor  │
  ├───────┤ ├────────┤    ├──────────────────┤ ├──────────────────┤
  │+client│ │+item   │    │+visit(Invoice)   │ │+visit(Invoice)   │
  │+amount│ │+price  │    │ → "PDF invoice.."│ │ → "<invoice.."   │
  ├───────┤ ├────────┤    │+visit(Receipt)   │ │+visit(Receipt)   │
  │+accept│ │+accept │    │ → "PDF receipt.."│ │ → "<receipt.."   │
  │ (v)   │ │ (v)    │    └──────────────────┘ └──────────────────┘
  └───────┘ └────────┘
   calls     calls
  v.visit   v.visit        // Adding JsonExportVisitor?
  (this)    (this)         // One new class — Invoice and Receipt UNTOUCHED

   ┌──────────────────────────────────────────────────────────────┐
   │                         Main                                │
   ├──────────────────────────────────────────────────────────────┤
   │ List<Document> docs = List.of(                              │
   │   new Invoice("Acme", 500.00),                              │
   │   new Receipt("Laptop", 999.99),                            │
   │   new Invoice("Globex", 1200.00));                          │
   │                                                              │
   │ DocumentVisitor pdf = new PdfExportVisitor();               │
   │ for (Document d : docs) d.accept(pdf);                      │
   │                                                              │
   │ DocumentVisitor xml = new XmlExportVisitor();               │
   │ for (Document d : docs) d.accept(xml);                      │
   └──────────────────────────────────────────────────────────────┘
```

## Visitor pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Element interface | `Document` | Declares `accept(DocumentVisitor)` — the entry point for double dispatch |
| Concrete Element | `Invoice`, `Receipt` | Implement `accept()` by calling `visitor.visit(this)` |
| Visitor interface | `DocumentVisitor` | Declares one `visit()` overload per element type |
| Concrete Visitor | `PdfExportVisitor`, `XmlExportVisitor` | Implement the operation for each document type |
| Client | `Main` | Iterates documents; passes any visitor — loop never changes |

## Double dispatch call trace

```
d.accept(pdf)   where d is an Invoice
  → invoice.accept(pdf)          // runtime type: Invoice
  → pdf.visit(this)              // this = Invoice
  → PdfExportVisitor.visit(Invoice)
  → "PDF invoice for Acme Corp: $500.00"
```

## Key design insight

| | Without pattern (bad_code) | With Visitor (good_code) |
|---|---|---|
| Adding JSON export | Add `exportToJson()` to every document class | Add one `JsonExportVisitor` class |
| Adding a new doc type | Relatively easy | Must add `visit(NewDoc)` to every Visitor — this is Visitor's trade-off |
| Separating concerns | Output format logic lives inside data classes | Each Visitor owns one operation; documents stay data-only |
| Iterating with mixed types | Branch on type in caller | `d.accept(visitor)` — double dispatch resolves type |

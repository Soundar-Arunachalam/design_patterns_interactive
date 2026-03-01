```
┌──────────────────────────────────────────────────────────┐
│             visitor / bad_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                          Invoice                            │
   ├──────────────────────────────────────────────────────────────┤
   │ + clientName: String                                         │
   │ + amount: double                                             │
   ├──────────────────────────────────────────────────────────────┤
   │ + exportToPdf(): void  → "PDF invoice for ..."              │
   │ + exportToXml(): void  → "<invoice client='...' .../>"      │
   │ // Next: exportToJson(), calculateTax(), applyDiscount()... │
   │ // All new operations go here → class grows forever         │
   └──────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                          Receipt                            │
   ├──────────────────────────────────────────────────────────────┤
   │ + item: String                                               │
   │ + price: double                                              │
   ├──────────────────────────────────────────────────────────────┤
   │ + exportToPdf(): void  → "PDF receipt: ..."                 │
   │ + exportToXml(): void  → "<receipt item='...' .../>"        │
   │ // Same operations duplicated again                         │
   └──────────────────────────────────────────────────────────────┘
   // Each new operation (JSON, tax) means editing ALL document types

   ┌──────────────────────────────────────────────────────────────┐
   │                          Main                               │
   ├──────────────────────────────────────────────────────────────┤
   │ new Invoice("Acme", 500.00).exportToPdf()                    │
   │ new Receipt("Laptop", 999.99).exportToPdf()                  │
   └──────────────────────────────────────────────────────────────┘
```

## Visitor pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Growing element | `Invoice` | Accumulates every new operation as a new method |
| Growing element | `Receipt` | Same operations duplicated; parallel growth |
| Client | `Main` | Calls operations directly on document objects |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Open/Closed violation | Every new operation (JSON export, tax) means editing every document class |
| Parallel duplication | Each operation is copy-pasted across all document types |
| Responsibility bloat | `Invoice` becomes a god class — it knows how to render PDF, XML, JSON, calculate tax… |
| Unrelated concerns mixed | Document structure (data) mixed with all output formats |

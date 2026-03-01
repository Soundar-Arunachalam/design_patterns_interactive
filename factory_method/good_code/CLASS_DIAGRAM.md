```
┌──────────────────────────────────────────────────────────┐
│        factory_method / good_code — Class Diagram        │
└──────────────────────────────────────────────────────────┘

                       «interface»
              ┌──────────────────────────────┐
              │            Report            │
              ├──────────────────────────────┤
              │ + generate(data: String):void│
              │ + save(): void               │
              └──────────────────────────────┘
                  ▲         ▲         ▲
                  │         │         │
          ┌───────┘   ┌─────┘   ┌────┘
          │           │         │
  ┌────────────┐ ┌───────────┐ ┌──────────┐
  │  PdfReport │ │ExcelReport│ │ CsvReport│
  ├────────────┤ ├───────────┤ ├──────────┤
  │+generate() │ │+generate()│ │+generate │
  │  → parses  │ │  → cells  │ │  → lines │
  │+save()     │ │+save()    │ │+save()   │
  │  → .pdf    │ │  → .xlsx  │ │  → .csv  │
  └────────────┘ └───────────┘ └──────────┘
          ▲           ▲              ▲
          │creates    │creates       │creates
          │           │              │
  ┌─────────────┐ ┌────────────────┐ ┌────────────────┐
  │PdfReportEx- │ │ExcelReportEx-  │ │CsvReportEx-    │
  │porter       │ │porter          │ │porter          │
  ├─────────────┤ ├────────────────┤ ├────────────────┤
  │+createReport│ │+createReport() │ │+createReport() │
  │ →new Pdf... │ │ →new Excel...  │ │ →new Csv...    │
  └─────────────┘ └────────────────┘ └────────────────┘
          ▲               ▲                 ▲
          └───────────────┼─────────────────┘
                          │
              ┌───────────────────────────────────┐
              │    ReportExporter  «abstract»     │
              ├───────────────────────────────────┤
              │ + exportReport(data:String): void │
              │   [template method — final]       │
              │     1. report = createReport()    │
              │     2. report.generate(data)      │
              │     3. report.save()              │
              ├───────────────────────────────────┤
              │ # createReport(): Report «abstract│
              └───────────────────────────────────┘

              ┌───────────────────────────────────┐
              │               Main                │
              ├───────────────────────────────────┤
              │ new PdfReportExporter()           │
              │   .exportReport("Q1 Sales...")    │
              │ new ExcelReportExporter()         │
              │   .exportReport("Q1 Sales...")    │
              └───────────────────────────────────┘
```

## Factory Method pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Creator (abstract) | `ReportExporter` | Defines the `exportReport()` template that calls the factory method |
| Factory Method | `createReport()` in each subclass | Decides which concrete `Report` to instantiate |
| Concrete Creator | `PdfReportExporter`, `ExcelReportExporter`, `CsvReportExporter` | Override `createReport()` to return their format |
| Product interface | `Report` | Common API that the template relies on |
| Concrete Product | `PdfReport`, `ExcelReport`, `CsvReport` | Format-specific generate + save logic |
| Client | `Main` | Selects subclass; calls `exportReport()` — no magic strings |

## Call trace

```
new PdfReportExporter().exportReport("Q1 Sales...")
  → createReport()          // PdfReportExporter override → new PdfReport()
  → report.generate("Q1..") // "Generating PDF report..."
  → report.save()            // "Saving PDF to file..."
```

## Key design insight

| | Without pattern (bad_code) | With Factory Method (good_code) |
|---|---|---|
| Adding XML format | Edit `exportReport()` method with more if-else | Add `XmlReport` + `XmlReportExporter`; existing classes untouched |
| Object creation | Interleaved with business logic | Isolated in `createReport()` — swappable per subclass |
| Magic strings | `"pdf"`, `"excel"` | None — Java types used directly |
| Testing a format | Must invoke full god-method | Instantiate `PdfReportExporter`, test in isolation |

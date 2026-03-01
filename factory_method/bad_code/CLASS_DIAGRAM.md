```
┌──────────────────────────────────────────────────────────┐
│        factory_method / bad_code — Class Diagram         │
└──────────────────────────────────────────────────────────┘

   ┌─────────────────────────────────────────────────────────────────┐
   │                        ReportService                           │
   ├─────────────────────────────────────────────────────────────────┤
   │ + exportReport(format: String, data: String): void             │
   │     if "pdf"   → parse, layout, embed fonts, write .pdf        │
   │     if "excel" → create workbook, write cells, save .xlsx      │
   │     if "csv"   → split data, write lines, save .csv            │
   │     else       → throw IllegalArgumentException                │
   └─────────────────────────────────────────────────────────────────┘

   ┌────────────────────────────────────────────────────────┐
   │                        Main                           │
   ├────────────────────────────────────────────────────────┤
   │ service.exportReport("pdf",   "Q1 Sales...")           │
   │ service.exportReport("excel", "Q1 Sales...")           │
   │ service.exportReport("csv",   "Q1 Sales...")           │
   └────────────────────────────────────────────────────────┘
```

## Factory Method pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| God class | `ReportService` | Handles creation logic AND export logic for every format in one method |
| Client | `Main` | Passes magic string `"pdf"` / `"excel"` / `"csv"` |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Open/Closed violation | Adding XML export means editing `exportReport()` again |
| Mixed responsibilities | Object creation and business logic live in the same method |
| Magic strings | `"pdf"` typo compiles; throws exception at runtime |
| Untestable | Can't test PDF logic in isolation; all formats share one method |

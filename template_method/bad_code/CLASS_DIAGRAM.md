```
┌──────────────────────────────────────────────────────────┐
│         template_method / bad_code — Class Diagram       │
└──────────────────────────────────────────────────────────┘

   ┌─────────────────────────────────────────────────────────────────┐
   │                        CsvImporter                             │
   ├─────────────────────────────────────────────────────────────────┤
   │ + importData(file: String): void                               │
   │   1. "Parsing CSV: " + file        ← varies                    │
   │   2. "Validating rows..."          ← SAME (copy-pasted)        │
   │   3. "Saving to database..."       ← SAME (copy-pasted)        │
   │   4. "Sending CSV import email"    ← varies                    │
   └─────────────────────────────────────────────────────────────────┘

   ┌─────────────────────────────────────────────────────────────────┐
   │                       JsonImporter                             │
   ├─────────────────────────────────────────────────────────────────┤
   │ + importData(file: String): void                               │
   │   1. "Parsing JSON: " + file       ← varies                    │
   │   2. "Validating rows..."          ← SAME (duplicated!)        │
   │   3. "Saving to database..."       ← SAME (duplicated!)        │
   │   4. "Posting JSON webhook"        ← varies                    │
   └─────────────────────────────────────────────────────────────────┘
   // Steps 2 and 3 are copy-pasted into every new importer

   ┌──────────────────────────────────────────────────────────────────┐
   │                           Main                                  │
   ├──────────────────────────────────────────────────────────────────┤
   │ new CsvImporter().importData("users.csv")                        │
   │ new JsonImporter().importData("products.json")                   │
   │ // Add XmlImporter → copy-paste validate + save again           │
   └──────────────────────────────────────────────────────────────────┘
```

## Template Method pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Duplicated sibling | `CsvImporter` | Full pipeline hardcoded — shares no code with JsonImporter |
| Duplicated sibling | `JsonImporter` | Same pipeline duplicated — validate + save copy-pasted |
| Client | `Main` | Calls `importData()` on each; unaware of the duplication |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Duplicated skeleton | Steps 2 and 3 are copy-pasted in every importer class |
| Inconsistency risk | Changing "Validating rows..." to log level means editing all importers |
| No shared structure | The pipeline is invisible — it's re-typed every time |
| Adding XmlImporter | Copy-paste validate + save a third time |

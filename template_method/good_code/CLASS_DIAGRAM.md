```
┌──────────────────────────────────────────────────────────┐
│         template_method / good_code — Class Diagram      │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────┐
   │                   DataImporter  «abstract»                      │
   ├──────────────────────────────────────────────────────────────────┤
   │ + importData(file: String): void  «final — template method»     │
   │     1. parse(file)    ← abstract — subclass fills in            │
   │     2. validate()     ← private — defined once here             │
   │     3. save()         ← private — defined once here             │
   │     4. notify()       ← abstract — subclass fills in            │
   ├──────────────────────────────────────────────────────────────────┤
   │ # parse(file: String): void   «abstract»                        │
   │ # notify(): void              «abstract»                        │
   │ - validate(): void            → "Validating rows..."            │
   │ - save(): void                → "Saving to database..."         │
   └──────────────────────────────────────────────────────────────────┘
              ▲                         ▲
              │                         │
   ┌─────────────────────┐    ┌──────────────────────┐
   │    CsvImporter      │    │     JsonImporter     │
   ├─────────────────────┤    ├──────────────────────┤
   │ # parse(file):      │    │ # parse(file):       │
   │   "Parsing CSV:..." │    │   "Parsing JSON:..." │
   │ # notify():         │    │ # notify():          │
   │   "Sending CSV      │    │   "Posting JSON      │
   │    import email"    │    │    webhook"          │
   └─────────────────────┘    └──────────────────────┘
   // validate() and save() inherited — written once in DataImporter

   ┌──────────────────────────────────────────────────────────────────┐
   │                           Main                                  │
   ├──────────────────────────────────────────────────────────────────┤
   │ new CsvImporter().importData("users.csv")                        │
   │ new JsonImporter().importData("products.json")                   │
   │                                                                  │
   │ // Adding XmlImporter? Override parse() and notify() only.      │
   │ // validate() and save() come for free from DataImporter.        │
   └──────────────────────────────────────────────────────────────────┘
```

## Template Method pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Abstract class / Template | `DataImporter` | Defines the `importData()` skeleton with `final`; implements common steps; declares abstract hooks |
| Invariant steps | `validate()`, `save()` in `DataImporter` | Written once; shared by all subclasses automatically |
| Hook (abstract) | `parse()`, `notify()` | Vary per format — each subclass overrides them |
| Concrete class | `CsvImporter`, `JsonImporter` | Override only the format-specific steps |
| Client | `Main` | Calls `importData()` — same call for any importer |

## Call trace

```
new CsvImporter().importData("users.csv")
  → parse("users.csv")    // CsvImporter override → "Parsing CSV: users.csv"
  → validate()            // DataImporter         → "Validating rows..."
  → save()                // DataImporter         → "Saving to database..."
  → notify()              // CsvImporter override → "Sending CSV import email"
```

## Key design insight

| | Without pattern (bad_code) | With Template Method (good_code) |
|---|---|---|
| Common steps (validate, save) | Copy-pasted in every importer | Written once in `DataImporter`; inherited |
| Adding XmlImporter | Copy-paste full pipeline with duplicates | Override `parse()` + `notify()` only |
| Changing validate logic | Edit every importer class | Edit `DataImporter.validate()` once |
| Pipeline order | Implicit — each class decides | Explicit in `importData()` skeleton — enforced and visible |

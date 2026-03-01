```
┌──────────────────────────────────────────────────────────┐
│           interpreter / bad_code — Class Diagram         │
└──────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────────┐
   │                        SearchEngine                              │
   ├───────────────────────────────────────────────────────────────────┤
   │ + evaluate(query: String, document: String): boolean             │
   │     if query contains " AND "                                    │
   │       → split on " AND "; check both terms                      │
   │     else if query contains " OR "                                │
   │       → split on " OR "; check either term                      │
   │     else                                                          │
   │       → check single term                                        │
   │                                                                   │
   │     // "java AND (spring OR boot)"? → not supported              │
   │     // Adding XOR? → edit this method with more branches         │
   └───────────────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────────┐
   │                          Main                                    │
   ├───────────────────────────────────────────────────────────────────┤
   │ engine.evaluate("java AND spring", doc)   // true                │
   │ engine.evaluate("python OR java",  doc)   // true                │
   │ engine.evaluate("python", doc)            // false               │
   │                                                                   │
   │ // "java AND (spring OR boot)"? → impossible                     │
   └───────────────────────────────────────────────────────────────────┘
```

## Interpreter pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Parser + evaluator (tangled) | `SearchEngine.evaluate()` | String-split parsing and evaluation mixed in one method |
| Client | `Main` | Passes raw strings; cannot compose nested expressions |

## Problems with this code

| Problem | Detail |
|---------|--------|
| No nesting support | `"java AND (spring OR boot)"` is impossible with string-split |
| Adding a new operator | Edit `evaluate()` with more if-else — parsing and evaluation both grow |
| No composability | Operators can't be combined at depth > 1 |
| Parsing and logic coupled | Can't test OR-evaluation without parsing — two responsibilities in one method |

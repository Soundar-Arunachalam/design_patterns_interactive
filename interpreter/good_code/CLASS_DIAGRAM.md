```
┌──────────────────────────────────────────────────────────┐
│           interpreter / good_code — Class Diagram        │
└──────────────────────────────────────────────────────────┘

                       «interface»
              ┌──────────────────────────────┐
              │          Expression          │
              ├──────────────────────────────┤
              │ + interpret(context:String)  │
              │   : boolean                  │
              └──────────────────────────────┘
          ▲           ▲          ▲          ▲
          │           │          │          │
 ┌──────────────┐  ┌──────────┐ ┌─────────────┐ ┌───────────────┐
 │TermExpression│  │AndExpress│ │OrExpression │ │NotExpression  │
 │  (terminal)  │  │ (non-term│ │  (non-term) │ │  (non-term)   │
 ├──────────────┤  ├──────────┤ ├─────────────┤ ├───────────────┤
 │ - term:String│  │ - left:  │ │ - left:     │ │ - expr:       │
 ├──────────────┤  │   Expr   │ │   Expr      │ │   Expression  │
 │+interpret(): │  │ - right: │ │ - right:    │ ├───────────────┤
 │ context      │  │   Expr   │ │   Expr      │ │+interpret():  │
 │ .contains(   │  ├──────────┤ ├─────────────┤ │ !expr.interp  │
 │   term)      │  │+interp():│ │+interp():   │ │   ret(context)│
 └──────────────┘  │ left &&  │ │ left ||     │ └───────────────┘
                   │ right    │ │ right       │
                   └──────────┘ └─────────────┘

   ┌────────────────────────────────────────────────────────────────┐
   │                           Main                                │
   ├────────────────────────────────────────────────────────────────┤
   │ // "java AND spring"                                           │
   │ new AndExpression(                                             │
   │   new TermExpression("java"),                                  │
   │   new TermExpression("spring"))                                │
   │                                                                │
   │ // "java AND (spring OR boot)" — trivial to compose:          │
   │ new AndExpression(                                             │
   │   new TermExpression("java"),                                  │
   │   new OrExpression(                                            │
   │     new TermExpression("spring"),                              │
   │     new TermExpression("boot")))                               │
   │                                                                │
   │ // "java AND NOT python":                                      │
   │ new AndExpression(                                             │
   │   new TermExpression("java"),                                  │
   │   new NotExpression(new TermExpression("python")))             │
   └────────────────────────────────────────────────────────────────┘
```

## Interpreter pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Abstract Expression | `Expression` | Common `interpret(context)` interface |
| Terminal Expression | `TermExpression` | Leaf node — checks if context contains a single term |
| Non-terminal Expression | `AndExpression` | Composes two expressions with AND logic |
| Non-terminal Expression | `OrExpression` | Composes two expressions with OR logic |
| Non-terminal Expression | `NotExpression` | Negates one expression |
| Client | `Main` | Builds expression trees by composing classes; calls `interpret(doc)` |

## Evaluation call trace

```
query = new AndExpression(
          new TermExpression("java"),
          new OrExpression(
            new TermExpression("spring"),
            new TermExpression("boot")))

query.interpret("Java Spring Boot developer...")
  → AndExpression.interpret()
     → left.interpret()   // TermExpression("java") → true
     → right.interpret()  // OrExpression
        → left.interpret()  // TermExpression("spring") → true
        → true || ...       → true
     → true && true → true
```

## Key design insight

| | Without pattern (bad_code) | With Interpreter (good_code) |
|---|---|---|
| Nested expressions | Impossible — string-split only handles flat queries | Naturally recursive — any depth |
| Adding XOR operator | Edit `evaluate()` method | Add `XorExpression` class; Expression.java untouched |
| Composability | OR inside AND? Not supported | `new AndExpression(term, new OrExpression(...))` |
| Testing operators | Must test through the full `evaluate()` | Each expression class is independently testable |

```
┌──────────────────────────────────────────────────────────┐
│           builder / good_code — Class Diagram            │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────────┐
   │                           EmailMessage                              │
   ├──────────────────────────────────────────────────────────────────────┤
   │ - to:       String  (final)                                          │
   │ - subject:  String  (final)                                          │
   │ - body:     String  (final)                                          │
   │ - cc:       String  (final)                                          │
   │ - bcc:      String  (final)                                          │
   │ - isHtml:   boolean (final)                                          │
   │ - priority: int     (final)                                          │
   ├──────────────────────────────────────────────────────────────────────┤
   │ - EmailMessage(builder: Builder)  «private constructor»              │
   │ + getTo(), getSubject(), …  (getters only — immutable)               │
   ├──────────────────────────────────────────────────────────────────────┤
   │  ┌──────────────────────────────────────────────────────────────┐   │
   │  │                    EmailMessage.Builder                      │   │
   │  ├──────────────────────────────────────────────────────────────┤   │
   │  │ - to:       String  «required»                               │   │
   │  │ - subject:  String  «required»                               │   │
   │  │ - body:     String  «required»                               │   │
   │  │ - cc:       String  «optional, default null»                 │   │
   │  │ - bcc:      String  «optional, default null»                 │   │
   │  │ - isHtml:   boolean «optional, default false»                │   │
   │  │ - priority: int     «optional, default 0»                    │   │
   │  ├──────────────────────────────────────────────────────────────┤   │
   │  │ + Builder(to, subject, body)  «required fields»              │   │
   │  │ + cc(String cc): Builder      «fluent, returns this»         │   │
   │  │ + bcc(String bcc): Builder    «fluent, returns this»         │   │
   │  │ + html(boolean): Builder      «fluent, returns this»         │   │
   │  │ + priority(int): Builder      «fluent, returns this»         │   │
   │  │ + build(): EmailMessage       «validates + constructs»        │   │
   │  └──────────────────────────────────────────────────────────────┘   │
   └──────────────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────────┐
   │                              Main                                    │
   ├──────────────────────────────────────────────────────────────────────┤
   │ new EmailMessage.Builder("bob@x.com", "Report", "Q1 body")           │
   │   .html(true)                                                        │
   │   .priority(2)                                                       │
   │   .build()                     // cc/bcc not set → default null      │
   └──────────────────────────────────────────────────────────────────────┘
```

## Builder pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Product | `EmailMessage` | Immutable object; private constructor forces use of the Builder |
| Builder | `EmailMessage.Builder` | Collects optional fields with named fluent setters; `build()` validates and constructs |
| Client | `Main` | Chains only the fields it needs — readable, no nulls, no position counting |

## Builder call trace

```
new EmailMessage.Builder("bob@x.com", "Report", "Q1 body")
  → Builder created with required fields set

.html(true)
  → builder.isHtml = true; return this

.priority(2)
  → builder.priority = 2; return this

.build()
  → validates required fields
  → calls new EmailMessage(this)  // private constructor
  → EmailMessage: all fields final, set once
```

## Key design insight

| | Without pattern (bad_code) | With Builder (good_code) |
|---|---|---|
| Optional fields | Must pass `null, null, ...` to reach desired param | Set by name: `.cc("...")` — skip the rest |
| Immutability | All fields `public` and mutable | All fields `final`; no setters |
| Adding a new optional field | Add yet another constructor overload | Add one setter method to Builder; existing calls unchanged |
| Readable call site | `new EmailMessage("a","b","c",null,null,true,2)` | `.html(true).priority(2).build()` — self-documenting |
| Invalid state | `new EmailMessage("a", null, null)` compiles fine | Required fields enforced in Builder constructor |

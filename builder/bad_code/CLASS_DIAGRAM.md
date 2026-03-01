```
┌──────────────────────────────────────────────────────────┐
│           builder / bad_code — Class Diagram             │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                       EmailMessage                          │
   ├──────────────────────────────────────────────────────────────┤
   │ + to: String                                                 │
   │ + subject: String                                            │
   │ + body: String                                               │
   │ + cc: String                                                 │
   │ + bcc: String                                                │
   │ + isHtml: boolean                                            │
   │ + priority: int                                              │
   ├──────────────────────────────────────────────────────────────┤
   │ + EmailMessage(to, subject, body)             ← 3-arg        │
   │ + EmailMessage(to, subject, body, cc)         ← 4-arg        │
   │ + EmailMessage(to, subject, body,             ← 7-arg        │
   │                cc, bcc, isHtml, priority)                    │
   └──────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────┐
   │                           Main                                  │
   ├──────────────────────────────────────────────────────────────────┤
   │ // Simple — OK                                                   │
   │ new EmailMessage("bob@x.com", "Report", "Q1 body")              │
   │                                                                  │
   │ // Want HTML + priority but no cc/bcc?                           │
   │ // Must pass null for intermediate params:                       │
   │ new EmailMessage("bob@x.com", "Report", "body",                  │
   │                  null, null, true, 2)                            │
   │                                   ↑    ↑                         │
   │              cc (unwanted) ───────┘    └─── bcc (unwanted)       │
   └──────────────────────────────────────────────────────────────────┘
```

## Builder pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Product (poorly constructed) | `EmailMessage` | Multiple telescoping constructors — caller must count positions |
| Client | `Main` | Must pass `null` for optional params just to reach the one it wants |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Telescoping constructors | 3 constructors; adding any new optional field requires yet another overload |
| Caller counts positions | `null, null, true, 2` — meaning of each arg is invisible at the call site |
| Null pollution | `cc = null`, `bcc = null` to reach `isHtml` and `priority` |
| Partially constructed objects | Nothing stops `new EmailMessage("a", null, null)` — invalid state compiles fine |
| Mutable fields | All fields `public` — nothing prevents post-construction modification |

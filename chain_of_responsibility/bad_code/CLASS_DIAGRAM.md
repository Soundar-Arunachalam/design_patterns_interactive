```
┌──────────────────────────────────────────────────────────────┐
│    chain_of_responsibility / bad_code — Class Diagram        │
└──────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                       SupportService                        │
   ├──────────────────────────────────────────────────────────────┤
   │ + handleTicket(issue: String, priority: int): void           │
   │     if priority <= 1 → "[L1 Support] Handling: ..."         │
   │     if priority <= 3 → "[L2 Support] Handling: ..."         │
   │     if priority <= 5 → "[Manager] Handling: ..."            │
   │     else             → "[CEO] Handling: ..."                │
   │     // Want VP between Manager and CEO?                     │
   │     // This method grows forever.                            │
   └──────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                          Main                               │
   ├──────────────────────────────────────────────────────────────┤
   │ support.handleTicket("Password reset", 1)                    │
   │ support.handleTicket("App crash", 3)                         │
   │ support.handleTicket("Data breach", 5)                       │
   │ support.handleTicket("Company-wide outage", 6)               │
   └──────────────────────────────────────────────────────────────┘
```

## Chain of Responsibility pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| God class | `SupportService` | Contains every level's logic — one giant if-else method |
| Client | `Main` | Calls the one method with priority numbers |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Adding a new level | Must edit `handleTicket()` — inserting an if-else in the right place |
| Levels are not independent | All level logic is tangled in one method — can't test L1 in isolation |
| Fixed routing | Can't change the chain at runtime (e.g. skip Manager for priority 4) |
| Thresholds hardcoded | Priority boundaries scattered in one method — no clear ownership per level |

```
┌──────────────────────────────────────────────────────────────┐
│    chain_of_responsibility / good_code — Class Diagram       │
└──────────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────┐
   │               SupportHandler  «abstract»                 │
   ├───────────────────────────────────────────────────────────┤
   │ # next: SupportHandler                                    │
   ├───────────────────────────────────────────────────────────┤
   │ + setNext(next: SupportHandler): SupportHandler           │
   │   → this.next = next; return next  (enables chaining)     │
   │ + handle(issue: String,                                   │
   │          priority: int): void  «abstract»                 │
   │ # passToNext(issue, priority): void                       │
   │   → if next != null: next.handle(...)                     │
   │     else: "[Unresolved] No handler for priority N"        │
   └───────────────────────────────────────────────────────────┘
              ▲              ▲              ▲
              │              │              │
   ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐
   │  L1Support   │  │  L2Support   │  │  ManagerSupport  │
   ├──────────────┤  ├──────────────┤  ├──────────────────┤
   │ + handle()   │  │ + handle()   │  │ + handle()       │
   │   priority≤1 │  │   priority≤3 │  │   priority≤5     │
   │   → handle   │  │   → handle   │  │   → handle       │
   │   else       │  │   else       │  │   else           │
   │   passToNext │  │   passToNext │  │   passToNext     │
   └──────────────┘  └──────────────┘  └──────────────────┘

   Chain:  L1Support → L2Support → ManagerSupport

   ┌──────────────────────────────────────────────────────────────┐
   │                          Main                               │
   ├──────────────────────────────────────────────────────────────┤
   │ SupportHandler l1 = new L1Support();                        │
   │ l1.setNext(new L2Support())                                  │
   │   .setNext(new ManagerSupport());                            │
   │                                                              │
   │ l1.handle("Password reset", 1)       // L1 handles          │
   │ l1.handle("App crash", 3)            // L2 handles          │
   │ l1.handle("Data breach", 5)          // Manager handles     │
   │ l1.handle("Company-wide outage", 6)  // Unresolved          │
   │                                                              │
   │ // Adding CeoSupport? Append to chain. Nothing else changes. │
   └──────────────────────────────────────────────────────────────┘
```

## Chain of Responsibility pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Handler (abstract) | `SupportHandler` | Defines `handle()` contract; manages `next` link; `passToNext()` fallback |
| Concrete Handler | `L1Support` | Handles priority ≤ 1; otherwise passes along |
| Concrete Handler | `L2Support` | Handles priority ≤ 3; otherwise passes along |
| Concrete Handler | `ManagerSupport` | Handles priority ≤ 5; otherwise passes along |
| Client | `Main` | Builds the chain; sends requests to head only |

## Request flow trace

```
l1.handle("Data breach", 5)
  → L1Support: priority 5 > 1 → passToNext
     → L2Support: priority 5 > 3 → passToNext
        → ManagerSupport: priority 5 ≤ 5 → "[Manager] Handling: Data breach"

l1.handle("Company-wide outage", 6)
  → L1 → L2 → Manager → passToNext → next == null
  → "[Unresolved] No handler for priority 6"
```

## Key design insight

| | Without pattern (bad_code) | With Chain of Responsibility (good_code) |
|---|---|---|
| Adding VP level | Edit `handleTicket()` if-else | Add `VpSupport extends SupportHandler`; insert in chain |
| Each level's logic | Tangled in one method | Encapsulated in its own class — independently testable |
| Chain order | Fixed in code | Configurable at runtime by `setNext()` wiring |
| Unknown priority | Falls through silently or hits else | `passToNext()` always reports if unresolved |

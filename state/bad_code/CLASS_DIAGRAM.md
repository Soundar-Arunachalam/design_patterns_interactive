```
┌──────────────────────────────────────────────────────────┐
│              state / bad_code — Class Diagram            │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────┐
   │                       VendingMachine                            │
   ├──────────────────────────────────────────────────────────────────┤
   │ - state: String = "idle"   ← magic string — typos compile fine  │
   │ - itemCount: int = 3                                             │
   ├──────────────────────────────────────────────────────────────────┤
   │ + insertCoin(): void                                             │
   │     if "idle"       → state = "has_coin"                        │
   │     if "has_coin"   → "Coin already inserted."                  │
   │     if "dispensing" → "Wait, dispensing..."                     │
   │                                                                  │
   │ + pressButton(): void                                            │
   │     if "idle"       → "Insert a coin first."                    │
   │     if "has_coin"   → state = "dispensing"                      │
   │     if "dispensing" → "Already dispensing."                     │
   │                                                                  │
   │ + dispense(): void                                               │
   │     if "dispensing" && itemCount > 0                            │
   │       → itemCount--; state = itemCount>0? "idle":"empty"        │
   │     else → "Cannot dispense."                                   │
   └──────────────────────────────────────────────────────────────────┘
   // Adding "maintenance" state → edit every single method

   ┌──────────────────────────────────────────────────────────────────┐
   │                          Main                                   │
   ├──────────────────────────────────────────────────────────────────┤
   │ machine.insertCoin()                                             │
   │ machine.pressButton()                                            │
   │ machine.dispense()                                               │
   │ machine.insertCoin()                                             │
   │ machine.insertCoin()   // already has coin                       │
   └──────────────────────────────────────────────────────────────────┘
```

## State pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| God class | `VendingMachine` | Holds all state transitions as if-else in every method |
| Client | `Main` | Calls actions; no visibility into internal state |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Adding a new state | Edit every method — `insertCoin()`, `pressButton()`, `dispense()` all must grow |
| Magic strings | `"idle"`, `"has_coin"`, `"dispensing"` — typo is a silent runtime bug |
| State logic not encapsulated | One state's behavior spread across 3 methods |
| State transitions unclear | Transition logic scattered — hard to draw the state machine from code |

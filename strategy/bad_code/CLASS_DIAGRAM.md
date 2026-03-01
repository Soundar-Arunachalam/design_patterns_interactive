```
┌──────────────────────────────────────────────────────────┐
│            strategy / bad_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────┐
   │                      DeliveryService                            │
   ├──────────────────────────────────────────────────────────────────┤
   │ + calculateCost(type: String, weight: double): double            │
   │     if "standard" → weight * 1.5                                │
   │     if "express"  → weight * 3.0 + 5.0                         │
   │     if "drone"    → weight < 2.0 ? 8.0 : -1  (weight limit)    │
   │     else          → throw IllegalArgumentException              │
   └──────────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────┐
   │                          Main                                   │
   ├──────────────────────────────────────────────────────────────────┤
   │ service.calculateCost("standard", 3.0)  // $4.50                │
   │ service.calculateCost("express",  3.0)  // $14.00               │
   │ service.calculateCost("drone",    1.5)  // $8.00                │
   │                                                                  │
   │ // Boss: "Add same-day delivery."                               │
   │ // You: *adds another else-if and hopes nothing breaks*         │
   └──────────────────────────────────────────────────────────────────┘
```

## Strategy pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| God method | `DeliveryService.calculateCost()` | Contains all algorithm variants in one if-else chain |
| Client | `Main` | Passes magic strings to select algorithm |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Open/Closed violation | Adding same-day delivery requires editing `calculateCost()` |
| Algorithms mixed together | All formulas live in one method — can't test drone logic in isolation |
| Magic strings | `"standard"`, `"express"`, `"drone"` — typo is a runtime exception |
| Special cases accumulate | Drone weight limit is buried inside a single method — invisible to callers |

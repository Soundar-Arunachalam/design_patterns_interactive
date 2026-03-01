```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                  strategy / good_code — Class Diagram                           │
└─────────────────────────────────────────────────────────────────────────────────┘

                         «interface»
                  ┌────────────────────────────┐
                  │      DeliveryStrategy       │
                  ├────────────────────────────┤
                  │ + calculate(weight): double │
                  └────────────────────────────┘
                            ▲
          ┌─────────────────┼─────────────────┐
          │ implements      │ implements      │ implements
          │                 │                 │
┌──────────────────┐ ┌──────────────────┐ ┌──────────────────┐
│ StandardDelivery │ │ ExpressDelivery  │ │  DroneDelivery   │
├──────────────────┤ ├──────────────────┤ ├──────────────────┤
│ + calculate(w)   │ │ + calculate(w)   │ │ + calculate(w)   │
│   → w × 1.5      │ │   → w × 3.0 + 5  │ │   → 8.0 flat     │
│                  │ │                  │ │   (max 2 kg)     │
└──────────────────┘ └──────────────────┘ └──────────────────┘

                         ┌───────────────────────────────────┐
                         │          DeliveryService          │
                         │            (Context)              │
                         ├───────────────────────────────────┤
                         │ - strategy: DeliveryStrategy      │
                         ├───────────────────────────────────┤
                         │ + DeliveryService(s: Strategy)    │
                         │ + setStrategy(s: Strategy): void  │
                         │ + calculateCost(weight): double   │
                         └───────────────────────────────────┘
                                        │
                              uses (delegates to)
                                        │
                                        ▼
                         ┌────────────────────────────┐
                         │      DeliveryStrategy       │  ← interface only
                         └────────────────────────────┘

                         ┌───────────────────────────────────┐
                         │             Main                  │
                         │            (Client)               │
                         ├───────────────────────────────────┤
                         │ + main(args): void                │
                         └───────────────────────────────────┘
                                        │
                         creates DeliveryService + injects strategy
```

## Strategy pattern roles

| Role             | Class / Interface    | Responsibility                                              |
|------------------|----------------------|-------------------------------------------------------------|
| Strategy         | `DeliveryStrategy`   | Common algorithm interface — one method `calculate(weight)` |
| Concrete Strategy| `StandardDelivery`   | weight × 1.5                                                |
| Concrete Strategy| `ExpressDelivery`    | weight × 3.0 + $5 flat fee                                  |
| Concrete Strategy| `DroneDelivery`      | $8 flat, throws if weight ≥ 2 kg                            |
| Context          | `DeliveryService`    | Holds a strategy; delegates `calculateCost` to it           |
| Client           | `Main`               | Selects and injects the strategy; can swap at runtime       |

## Runtime swap (key benefit)

```
DeliveryService service = new DeliveryService(new StandardDelivery());
service.calculateCost(3.0)   →  4.50   (Standard: 3 × 1.5)

service.setStrategy(new ExpressDelivery());
service.calculateCost(3.0)   →  14.00  (Express: 3 × 3.0 + 5)
```

`DeliveryService` never changed — only the injected strategy object did.

## Key design insight vs bad_code

| | bad_code | good_code |
|---|---|---|
| Adding a new delivery type | Edit `if-else` inside `DeliveryService` | Add one new class; `DeliveryService` untouched |
| Testing a single algorithm | Must run through the entire if-else | Instantiate one strategy class in isolation |
| Runtime swap | Impossible without re-entering the if-else | `setStrategy()` — swap any time |
| Coupling | `DeliveryService` knows every algorithm | `DeliveryService` knows only the interface |

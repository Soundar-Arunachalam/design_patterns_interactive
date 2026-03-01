```
┌──────────────────────────────────────────────────────┐
│         singleton / bad_code — Class Diagram         │
└──────────────────────────────────────────────────────┘

   ┌────────────────────────────────────┐
   │               Logger               │
   ├────────────────────────────────────┤
   │ - owner: String                    │
   ├────────────────────────────────────┤
   │ + Logger(owner: String)            │
   │   → prints "New Logger instance    │
   │     created for: <owner>"          │
   │ + log(message: String): void       │
   └────────────────────────────────────┘
          ▲                  ▲
          │ creates          │ creates
          │                  │
 ┌─────────────────┐  ┌─────────────────────┐
 │  OrderService   │  │   PaymentService    │
 ├─────────────────┤  ├─────────────────────┤
 │ - logger:Logger │  │ - logger: Logger    │
 ├─────────────────┤  ├─────────────────────┤
 │ + placeOrder()  │  │ + processPayment()  │
 └─────────────────┘  └─────────────────────┘
   new Logger(...)       new Logger(...)

   ┌──────────────────────────────────────────────────┐
   │                     Main                         │
   ├──────────────────────────────────────────────────┤
   │ Logger a = new Logger("A")  // instance 1        │
   │ Logger b = new Logger("B")  // instance 2        │
   │ Logger c = new Logger("C")  // instance 3        │
   │ // comment: "Should be 1 — but we get 3"         │
   └──────────────────────────────────────────────────┘
```

## Singleton pattern roles

| Role    | Class(es)                           | Responsibility                                    |
|---------|-------------------------------------|---------------------------------------------------|
| Singleton (broken) | `Logger`               | Intended to be shared, but constructor is public — every caller creates a new instance |
| Client  | `OrderService`, `PaymentService`    | Each creates its own private `Logger`, defeating shared logging |
| Client  | `Main`                              | Creates 3 separate loggers; demonstrates the problem |

## Object creation trace

```
new OrderService()   → new Logger("OrderService")   → prints "New Logger instance created"
new PaymentService() → new Logger("PaymentService") → prints "New Logger instance created"
Main: new Logger("A"), new Logger("B"), new Logger("C")
// 5 separate objects — none of them is shared
```

## Problems with this code

| Problem | Detail |
|---------|--------|
| No shared instance | Every caller gets its own `Logger` — log entries are scattered across objects |
| Uncontrolled construction | Public constructor lets anyone create unlimited instances |
| Wasted resources | Each instance holds its own state; memory grows with each creation |
| Silent inconsistency | Compiles and runs fine — the bug is invisible until traced at runtime |

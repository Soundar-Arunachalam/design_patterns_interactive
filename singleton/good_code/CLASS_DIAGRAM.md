```
┌──────────────────────────────────────────────────────┐
│         singleton / good_code — Class Diagram        │
└──────────────────────────────────────────────────────┘

   ┌────────────────────────────────────────────────┐
   │                    Logger                      │
   ├────────────────────────────────────────────────┤
   │ - instance: Logger  «static»                   │
   ├────────────────────────────────────────────────┤
   │ - Logger()          «private constructor»      │
   │ + getInstance(): Logger  «static synchronized» │
   │ + log(message: String): void                   │
   └────────────────────────────────────────────────┘
              ▲                    ▲
              │ Logger.getInstance()│ Logger.getInstance()
              │                    │
   ┌──────────────────┐  ┌──────────────────────┐
   │   OrderService   │  │    PaymentService    │
   ├──────────────────┤  ├──────────────────────┤
   │ - logger: Logger │  │ - logger: Logger     │
   ├──────────────────┤  ├──────────────────────┤
   │ + placeOrder()   │  │ + processPayment()   │
   └──────────────────┘  └──────────────────────┘
     (same object)            (same object)

   ┌──────────────────────────────────────────────┐
   │                    Main                      │
   ├──────────────────────────────────────────────┤
   │ Logger a = Logger.getInstance()              │
   │ Logger b = Logger.getInstance()              │
   │ System.out.println(a == b);  // true         │
   └──────────────────────────────────────────────┘
```

## Singleton pattern roles

| Role      | Class(es)                           | Responsibility                                      |
|-----------|-------------------------------------|-----------------------------------------------------|
| Singleton | `Logger`                            | Holds a single `static` instance; private constructor prevents external creation; `synchronized` `getInstance()` is thread-safe |
| Client    | `OrderService`, `PaymentService`    | Call `Logger.getInstance()` — guaranteed to receive the same object |
| Client    | `Main`                              | Proves `a == b` is `true` — only one logger exists |

## getInstance() call trace

```
First call:  Logger.getInstance()
             → instance == null → new Logger() → instance assigned → return instance

Second call: Logger.getInstance()
             → instance != null → return same instance (no new object)

OrderService.logger  ──┐
                       ├──► same Logger@7c30 object
PaymentService.logger ─┘
```

## Key design insight

| | Without pattern (bad_code) | With Singleton (good_code) |
|---|---|---|
| Instance count | Unlimited — one per `new Logger(...)` call | Exactly one — enforced by private constructor |
| Thread safety | N/A (each gets their own) | `synchronized` prevents race condition on first creation |
| Shared log output | No — scattered across multiple loggers | Yes — all callers share the same logger |
| Constructor access | `public` — anyone can create | `private` — only the class controls creation |

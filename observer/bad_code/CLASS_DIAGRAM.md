```
┌──────────────────────────────────────────────────────────┐
│            observer / bad_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                       StockMarket                           │
   ├──────────────────────────────────────────────────────────────┤
   │ - stockName: String                                          │
   │ - price: double                                              │
   │ - mobileAlert: MobileAlert  ← hard-wired concrete class      │
   │ - emailAlert:  EmailAlert   ← hard-wired concrete class      │
   ├──────────────────────────────────────────────────────────────┤
   │ + StockMarket(stockName: String)                             │
   │ + setPrice(price: double): void                              │
   │   → mobileAlert.notify(stockName, price)                    │
   │   → emailAlert.notify(stockName, price)                     │
   │   // Adding DashboardAlert → edit THIS method               │
   └──────────────────────────────────────────────────────────────┘
         │ creates                   │ creates
         ▼                           ▼
   ┌───────────────┐           ┌──────────────┐
   │  MobileAlert  │           │  EmailAlert  │
   ├───────────────┤           ├──────────────┤
   │ + notify(     │           │ + notify(    │
   │   stock, price│           │   stock,price│
   │   ): void     │           │   ): void    │
   └───────────────┘           └──────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                          Main                               │
   ├──────────────────────────────────────────────────────────────┤
   │ StockMarket market = new StockMarket("AAPL");               │
   │ market.setPrice(175.50);                                     │
   │ // "To add DashboardAlert, edit StockMarket.java"           │
   └──────────────────────────────────────────────────────────────┘
```

## Observer pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Subject (broken) | `StockMarket` | Hard-codes concrete observer fields and calls |
| Hard-wired observer | `MobileAlert`, `EmailAlert` | Concrete alert classes with no common interface |
| Client | `Main` | Creates market; no way to subscribe new observers |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Adding a new subscriber | Must edit `StockMarket.setPrice()` with another hard-coded call |
| Can't unsubscribe | No mechanism to remove an observer at runtime |
| Tight coupling | `StockMarket` imports and instantiates `MobileAlert` and `EmailAlert` directly |
| No common interface | Can't write `for (Alert a : alerts) a.notify(...)` — they're different types |

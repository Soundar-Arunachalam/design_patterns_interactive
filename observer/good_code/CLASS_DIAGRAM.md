```
┌──────────────────────────────────────────────────────────┐
│           observer / good_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

                       «interface»
              ┌──────────────────────────────────┐
              │          StockObserver           │
              ├──────────────────────────────────┤
              │ + onPriceChange(stock: String,   │
              │                 price: double)   │
              └──────────────────────────────────┘
                   ▲                    ▲
                   │                    │
        ┌──────────────────┐  ┌──────────────────┐
        │   MobileAlert    │  │   EmailAlert     │
        ├──────────────────┤  ├──────────────────┤
        │+onPriceChange()  │  │+onPriceChange()  │
        │ → "[Mobile]..."  │  │ → "[Email]..."   │
        └──────────────────┘  └──────────────────┘
                   ▲
               subscribe / unsubscribe
                   │
   ┌──────────────────────────────────────────────────────────┐
   │                      StockMarket                        │
   │                        (Subject)                        │
   ├──────────────────────────────────────────────────────────┤
   │ - stockName: String                                      │
   │ - observers: List<StockObserver>                         │
   ├──────────────────────────────────────────────────────────┤
   │ + StockMarket(stockName: String)                         │
   │ + subscribe(o: StockObserver): void                      │
   │ + unsubscribe(o: StockObserver): void                    │
   │ + setPrice(price: double): void                          │
   │   → for each o in observers: o.onPriceChange(name,price)│
   │   // Never imports MobileAlert or EmailAlert             │
   └──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │                         Main                            │
   ├──────────────────────────────────────────────────────────┤
   │ StockMarket market = new StockMarket("AAPL");            │
   │ market.subscribe(new MobileAlert());                     │
   │ market.subscribe(new EmailAlert());                      │
   │ // Add DashboardAlert: market.subscribe(new Dashboard..) │
   │ // StockMarket.java is NOT touched.                      │
   │ market.setPrice(175.50);                                 │
   │ market.setPrice(180.00);                                 │
   └──────────────────────────────────────────────────────────┘
```

## Observer pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Observer interface | `StockObserver` | Common callback API for all subscribers |
| Concrete Observer | `MobileAlert`, `EmailAlert` | Implement `onPriceChange()` with their specific notification logic |
| Subject | `StockMarket` | Manages observer list; notifies all on `setPrice()` |
| Client | `Main` | Subscribes/unsubscribes observers at runtime |

## Notification call trace

```
market.setPrice(180.00)
  → for MobileAlert: onPriceChange("AAPL", 180.00) → "[Mobile] Push alert: AAPL is now $180.00"
  → for EmailAlert:  onPriceChange("AAPL", 180.00) → "[Email] Alert sent: AAPL is now $180.00"
```

## Key design insight

| | Without pattern (bad_code) | With Observer (good_code) |
|---|---|---|
| Adding a new subscriber | Edit `StockMarket.setPrice()` | `market.subscribe(new DashboardAlert())` |
| Removing a subscriber | Edit the method | `market.unsubscribe(observer)` |
| Subject coupling | Imports concrete alert classes | Knows only `StockObserver` interface |
| Runtime flexibility | Fixed subscribers at creation | Observers added/removed while running |

```
┌──────────────────────────────────────────────────────┐
│          adapter / bad_code — Class Diagram          │
└──────────────────────────────────────────────────────┘

   ┌─────────────────────────────────────────────────────────────────┐
   │                        PaymentService                          │
   ├─────────────────────────────────────────────────────────────────┤
   │ - paypal: PayPalClient                                          │
   │ - stripe: StripeClient                                          │
   │ - square: SquareClient                                          │
   ├─────────────────────────────────────────────────────────────────┤
   │ + processPayment(type: String, amount: double): void            │
   │     if "paypal" → paypal.sendPayment(amount, "USD")             │
   │     if "stripe" → stripe.charge((int)(amount*100), "USD")       │
   │     if "square" → square.makePayment("USD", amount)             │
   │ + refundPayment(type: String, amount: double): void             │
   │     if "paypal" → paypal.sendRefund(...)                        │
   │     if "stripe" → stripe.issueRefund(...)                       │
   │     if "square" → square.makeRefund(...)                        │
   └─────────────────────────────────────────────────────────────────┘
         │ uses               │ uses               │ uses
         ▼                    ▼                    ▼
 ┌────────────────┐  ┌─────────────────┐  ┌─────────────────────┐
 │  PayPalClient  │  │  StripeClient   │  │    SquareClient      │
 ├────────────────┤  ├─────────────────┤  ├─────────────────────┤
 │ + sendPayment  │  │ + charge        │  │ + makePayment        │
 │   (double,     │  │   (int cents,   │  │   (String currency,  │
 │    String)     │  │    String)      │  │    double total)     │
 │ + sendRefund   │  │ + issueRefund   │  │ + makeRefund(...)    │
 │   (double,     │  │   (int, String) │  │   → returns boolean  │
 │    String)     │  │                 │  │                      │
 └────────────────┘  └─────────────────┘  └─────────────────────┘

   ┌────────────────────────────────────────────────────────┐
   │                         Main                          │
   ├────────────────────────────────────────────────────────┤
   │ service.processPayment("paypal",  50.00)               │
   │ service.processPayment("stripe", 120.00)               │
   │ service.processPayment("square",  75.00)               │
   └────────────────────────────────────────────────────────┘
```

## Adapter pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| God class | `PaymentService` | Knows all 3 clients directly; holds if-else for every operation and every type |
| Incompatible SDK | `PayPalClient` | `sendPayment(double, String)` — dollars |
| Incompatible SDK | `StripeClient` | `charge(int cents, String)` — must multiply by 100 |
| Incompatible SDK | `SquareClient` | `makePayment(String, double)` — reversed params; returns boolean |
| Client | `Main` | Passes magic strings "paypal"/"stripe"/"square" |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Open/Closed violation | Adding Apple Pay → edit `PaymentService` with more if-else |
| Type conversion in wrong place | `(int)(amount * 100)` for Stripe is inside service business logic |
| Magic strings | `"paypal"`, `"stripe"`, `"square"` — typos compile silently |
| All clients coupled to one class | `PaymentService` must import and know every SDK |

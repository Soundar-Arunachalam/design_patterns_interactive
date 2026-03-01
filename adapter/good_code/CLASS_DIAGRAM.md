```
┌──────────────────────────────────────────────────────┐
│         adapter / good_code — Class Diagram          │
└──────────────────────────────────────────────────────┘

                        «interface»
               ┌──────────────────────────────┐
               │       PaymentProcessor       │
               ├──────────────────────────────┤
               │ + processPayment(double):void│
               │ + refundPayment(double): void│
               └──────────────────────────────┘
                  ▲        ▲        ▲        ▲
                  │        │        │        │
       ┌──────────┘   ┌────┘   ┌───┘   ┌────┘
       │              │        │        │
 ┌─────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────────┐
 │PayPalAdapter│ │StripeAdapter │ │SquareAdapter │ │ApplePayAdapter   │
 ├─────────────┤ ├──────────────┤ ├──────────────┤ ├──────────────────┤
 │-client:     │ │-client:      │ │-client:      │ │-client:          │
 │ PayPalClient│ │ StripeClient │ │ SquareClient │ │ ApplePayClient   │
 ├─────────────┤ ├──────────────┤ ├──────────────┤ ├──────────────────┤
 │+process()   │ │+process()    │ │+process()    │ │+process()        │
 │ →sendPayment│ │ →charge(     │ │ →makePayment │ │ →processTransaction│
 │+refund()    │ │  cents,USD)  │ │  ("USD",amt) │ │  ("pay",amt,"USD")│
 │ →sendRefund │ │+refund()     │ │+refund()     │ │+refund()         │
 └─────────────┘ └──────────────┘ └──────────────┘ └──────────────────┘
       │ wraps        │ wraps        │ wraps          │ wraps
       ▼              ▼              ▼                ▼
 ┌───────────┐ ┌────────────┐ ┌────────────┐ ┌─────────────────────────┐
 │PayPalClient│ │StripeClient│ │SquareClient│ │      ApplePayClient     │
 ├───────────┤ ├────────────┤ ├────────────┤ ├─────────────────────────┤
 │+sendPayment│ │+charge     │ │+makePayment│ │+processTransaction      │
 │+sendRefund │ │+issueRefund│ │+makeRefund │ │  (type,amount,currency) │
 └───────────┘ └────────────┘ └────────────┘ └─────────────────────────┘

               ┌──────────────────────────────┐
               │        PaymentService        │
               ├──────────────────────────────┤
               │ - processor: PaymentProcessor│
               ├──────────────────────────────┤
               │ + PaymentService(            │
               │     PaymentProcessor)        │
               │ + pay(amount: double): void  │
               │ + refund(amount: double):void│
               └──────────────────────────────┘
                         │ delegates to PaymentProcessor
                         │ (no if-else, no SDK knowledge)

               ┌──────────────────────────────┐
               │             Main             │
               ├──────────────────────────────┤
               │ new PaymentService(          │
               │   new PayPalAdapter(...))    │
               │ new PaymentService(          │
               │   new StripeAdapter(...))    │
               └──────────────────────────────┘
```

## Adapter pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Target interface | `PaymentProcessor` | Uniform API that `PaymentService` depends on |
| Adapter | `PayPalAdapter`, `StripeAdapter`, `SquareAdapter`, `ApplePayAdapter` | Translates the target interface into each SDK's incompatible API |
| Adaptee | `PayPalClient`, `StripeClient`, `SquareClient`, `ApplePayClient` | Third-party SDKs with different method signatures |
| Context | `PaymentService` | Uses only `PaymentProcessor` — knows nothing about SDKs |
| Client | `Main` | Wires the correct adapter into `PaymentService` |

## Call trace

```
PaymentService.pay(50.00)
  → processor.processPayment(50.00)
     [if PayPalAdapter]  → paypal.sendPayment(50.00, "USD")
     [if StripeAdapter]  → stripe.charge(5000, "USD")       // *100 hidden inside adapter
     [if SquareAdapter]  → square.makePayment("USD", 50.00) // param swap hidden inside adapter
     [if ApplePayAdapter]→ applePay.processTransaction("pay", "50.00", "USD")
```

## Key design insight

| | Without pattern (bad_code) | With Adapter (good_code) |
|---|---|---|
| Adding a new payment provider | Edit `PaymentService` with more if-else | Add one new Adapter class; `PaymentService` unchanged |
| SDK differences (cents, param order) | Conversion logic in service business code | Hidden inside each adapter |
| Magic strings | `"paypal"`, `"stripe"` | Gone — adapter type selected by wiring in `Main` |
| `PaymentService` coupling | Imports all 3 SDKs | Imports only `PaymentProcessor` interface |

# 🔌 Adapter Pattern — Lecture Demo

> **Gang of Four — Structural Pattern**

---

## 🧩 The Real-World Scenario

Your team built a checkout system that supports **PayPal**. It works great.

Six months later, marketing says: *"We need to add Stripe — customers are asking for it."*
You add Stripe. Then Square. Then Apple Pay.

Every third-party SDK has its own API:

| Provider   | Method name        | Amount format       | Return type |
|------------|--------------------|---------------------|-------------|
| PayPal     | `sendPayment()`    | `double` (dollars)  | `void`      |
| Stripe     | `charge()`         | `int` (cents!)      | `void`      |
| Square     | `makePayment()`    | `double`, reversed args | `boolean` |
| Apple Pay  | `processTransaction()` | `String` ("50.00") | `String`  |

Without a pattern, a developer naturally reaches for **if-else chains**. It works... at first.

---

## ❌ The Bad Code (`bad_code/`)

Open `PaymentService.java`. Here's what the developer wrote:

```java
public void processPayment(String provider, double amount) {
    if (provider.equals("paypal")) {
        payPalClient.sendPayment(amount, "USD");
    } else if (provider.equals("stripe")) {
        int amountInCents = (int) (amount * 100); // Why does this live here?!
        stripeClient.charge(amountInCents, "USD");
    } else if (provider.equals("square")) {
        boolean success = squareClient.makePayment("USD", amount); // reversed!
        if (!success) { System.out.println("Square payment failed!"); }
    } else {
        throw new IllegalArgumentException("Unknown provider: " + provider);
    }
}
```

### Code Smells — Ask the students to spot them

| # | Smell | Why it hurts |
|---|-------|-------------|
| 1 | **Shotgun Surgery** | Adding Apple Pay means editing `processPayment()`, `refundPayment()`, and every other method that has the same if-else block. |
| 2 | **Violates Open/Closed Principle** | This class is never done. Every new provider forces a modification to existing, working code. |
| 3 | **Too much knowledge** | `PaymentService` knows Stripe's cent format, Square's reversed params, PayPal's method name. That's not its job. |
| 4 | **Magic strings** | `"paypal"`, `"stripe"`, `"square"` — a typo compiles fine but crashes at runtime. |
| 5 | **Duplicated logic** | The cents conversion appears in *both* `processPayment` and `refundPayment`. Fix it in one place → forget the other → bug. |
| 6 | **Untestable** | The service `new`s up its own clients. You can't inject a mock. |

---

## ✅ The Good Code (`good_code/`)

### The Core Idea

> **Define a common interface. Write one small "adapter" class per provider that translates between your interface and the provider's API. Your service only ever talks to the interface.**

### Pattern Participants

```
«interface»
PaymentProcessor          ← TARGET: what your app expects
+ processPayment(amount)
+ refundPayment(amount)
        ▲
        │ implements
  ┌─────┴──────────────────────────┐
  │             │                  │
PayPalAdapter  StripeAdapter  SquareAdapter  ApplePayAdapter
  │             │                  │              │
  wraps         wraps              wraps          wraps
  │             │                  │              │
PayPalClient  StripeClient   SquareClient   ApplePayClient
                                            ← ADAPTEE: the incompatible third-party API
```

### The Clean `PaymentService`

```java
public class PaymentService {
    private final PaymentProcessor paymentProcessor; // only knows about THIS interface

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void processPayment(double amount) {
        paymentProcessor.processPayment(amount); // no if-else, no provider names
    }
}
```

### Adding Apple Pay (the "Friday feature")

```java
// Step 1: Create the adapter (new file, ~15 lines)
public class ApplePayAdapter implements PaymentProcessor {
    private final ApplePayClient applePayClient;
    ...
    @Override
    public void processPayment(double amount) {
        applePayClient.processTransaction("PAYMENT", String.format("%.2f", amount), "USD");
    }
}

// Step 2: Wire it up
PaymentService applePayService = new PaymentService(new ApplePayAdapter(new ApplePayClient()));
```

**Files changed:** 2 new files. Zero modifications to existing code. ✅

---

## 🗺️ File Structure

```
adapter/
├── bad_code/
│   ├── PayPalClient.java        ← third-party (read-only)
│   ├── StripeClient.java        ← third-party (read-only)
│   ├── SquareClient.java        ← third-party (read-only)
│   ├── PaymentService.java      ← ❌ the if-else nightmare
│   └── Main.java
│
└── good_code/
    ├── PayPalClient.java        ← third-party (read-only, same as bad_code)
    ├── StripeClient.java        ← third-party (read-only, same as bad_code)
    ├── SquareClient.java        ← third-party (read-only, same as bad_code)
    ├── ApplePayClient.java      ← new third-party SDK (boss's Friday request)
    │
    ├── PaymentProcessor.java    ← ✅ TARGET interface (your app's contract)
    ├── PayPalAdapter.java       ← ✅ Adapter for PayPal
    ├── StripeAdapter.java       ← ✅ Adapter for Stripe
    ├── SquareAdapter.java       ← ✅ Adapter for Square
    ├── ApplePayAdapter.java     ← ✅ Adapter for Apple Pay (added without touching anything else)
    │
    ├── PaymentService.java      ← ✅ Clean service, zero if-else
    └── Main.java
```

---

## ▶️ How to Run

**Bad code:**
```bash
cd adapter/bad_code
javac *.java
java Main
```

**Good code:**
```bash
cd adapter/good_code
javac *.java
java Main
```

---

## 💡 Discussion Questions for Students

1. In `bad_code/PaymentService.java`, how many files would you need to edit to add Google Pay?
2. What kind of bug can happen if you add a new provider to `processPayment()` but forget `refundPayment()`?
3. In the good code, which file would you change if Stripe updated its API to use millicents instead of cents?
4. Could you write a unit test for `bad_code/PaymentService` without calling real PayPal/Stripe APIs? What about `good_code/PaymentService`?
5. The Adapter Pattern is often described as a "wrapper". Can you think of other real-world wrappers (in code or in life)?

---

## 🔑 One-Line Summary

> **The Adapter Pattern lets incompatible interfaces work together by introducing a wrapper (adapter) that translates one interface into another — without changing either the client or the third-party code.**

---

*Design Patterns: Elements of Reusable Object-Oriented Software — Gamma, Helm, Johnson, Vlissides (GoF)*

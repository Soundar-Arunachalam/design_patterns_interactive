# Vending Machine — State Diagram

```
                        ┌─────────────────────────────────────────────────────┐
                        │              VENDING MACHINE STATES                 │
                        └─────────────────────────────────────────────────────┘

                                       ● (start)
                                           │
                                           ▼
                              ┌────────────────────────┐
                              │          IDLE          │◄──────────────────────┐
                              │  "Insert a coin..."    │                       │
                              └────────────────────────┘                       │
                                           │                                   │
                                    insertCoin()                         [items > 0]
                                           │                           dispense()
                                           ▼                                   │
                              ┌────────────────────────┐                       │
                              │       HAS_COIN         │                       │
                              │  "Coin inserted.       │                       │
                              │   Press button."       │                       │
                              └────────────────────────┘                       │
                                           │                                   │
                                    pressButton()                              │
                                           │                                   │
                                           ▼                                   │
                              ┌────────────────────────┐                       │
                              │      DISPENSING        │───────────────────────┘
                              │  "Dispensing item..."  │
                              └────────────────────────┘
                                           │
                                    [items == 0]
                                    dispense()
                                           │
                                           ▼
                              ┌────────────────────────┐
                              │         EMPTY          │
                              │  "Out of stock."       │
                              └────────────────────────┘
                                           │
                                          ◉ (end / refill needed)
```

## Transitions

| From        | Event           | Guard          | To          |
|-------------|-----------------|----------------|-------------|
| IDLE        | `insertCoin()`  | —              | HAS_COIN    |
| HAS_COIN    | `pressButton()` | —              | DISPENSING  |
| DISPENSING  | `dispense()`    | items > 0      | IDLE        |
| DISPENSING  | `dispense()`    | items == 0     | EMPTY       |

## Invalid actions (ignored / print warning)

| State       | Invalid action  | Response                  |
|-------------|-----------------|---------------------------|
| IDLE        | `pressButton()` | "Insert a coin first."    |
| IDLE        | `dispense()`    | "Cannot dispense."        |
| HAS_COIN    | `insertCoin()`  | "Coin already inserted."  |
| HAS_COIN    | `dispense()`    | "Press the button first." |
| DISPENSING  | `insertCoin()`  | "Wait, dispensing…"       |
| DISPENSING  | `pressButton()` | "Already dispensing."     |
| EMPTY       | any             | "Out of stock."           |

## Bad code vs Good code

| Aspect             | Bad code (`VendingMachine.java`)          | Good code (State pattern)                  |
|--------------------|-------------------------------------------|--------------------------------------------|
| State representation | Magic strings `"idle"`, `"has_coin"` … | Concrete classes `IdleState`, `HasCoinState` … |
| Adding a new state | Edit every `if-else` in every method      | Add one new class, no existing file touched |
| Typo safety        | `"idel"` compiles silently                | Compile error if wrong class name           |
| Logic location     | Scattered across all methods              | Each state class owns its own behaviour     |

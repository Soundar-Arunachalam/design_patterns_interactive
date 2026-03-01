```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                   state / good_code — Class Diagram                             │
└─────────────────────────────────────────────────────────────────────────────────┘

                              «interface»
                  ┌─────────────────────────────────────────┐
                  │               VendingState              │
                  ├─────────────────────────────────────────┤
                  │ + insertCoin(m: VendingMachine): void   │
                  │ + pressButton(m: VendingMachine): void  │
                  │ + dispense(m: VendingMachine): void     │
                  └─────────────────────────────────────────┘
                                      ▲
             ┌────────────────────────┼───────────────────────────┐
             │ implements             │ implements                │ implements
             │                        │                            │
┌────────────────────────┐ ┌──────────────────────────┐ ┌─────────────────────────┐
│       IdleState        │ │      HasCoinState        │ │    DispensingState      │
├────────────────────────┤ ├──────────────────────────┤ ├─────────────────────────┤
│ insertCoin()           │ │ insertCoin()             │ │ insertCoin()            │
│  → setState(HasCoin)   │ │  → "already inserted"   │ │  → "wait, dispensing"   │
│ pressButton()          │ │ pressButton()            │ │ pressButton()           │
│  → "insert coin first" │ │  → setState(Dispensing) │ │  → "already dispensing" │
│ dispense()             │ │ dispense()               │ │ dispense()              │
│  → "no coin inserted"  │ │  → "press button first" │ │  → m.decreaseItem()     │
│                        │ │                          │ │  → setState(Idle|Empty) │
└────────────────────────┘ └──────────────────────────┘ └─────────────────────────┘
                                                                        │
                                                             implements │
                                                                        │
                                              ┌─────────────────────────┐
                                              │       EmptyState         │
                                              ├─────────────────────────┤
                                              │ insertCoin()            │
                                              │  → "refund given"       │
                                              │ pressButton()           │
                                              │  → "machine is empty"   │
                                              │ dispense()              │
                                              │  → "nothing to dispense"│
                                              └─────────────────────────┘

           ┌──────────────────────────────────────────────────────┐
           │                   VendingMachine                     │
           │                    (Context)                         │
           ├──────────────────────────────────────────────────────┤
           │ - state: VendingState                                │
           │ - itemCount: int                                     │
           ├──────────────────────────────────────────────────────┤
           │ + insertCoin():  void  → delegates to state          │
           │ + pressButton(): void  → delegates to state          │
           │ + dispense():    void  → delegates to state          │
           │ + setState(s: VendingState): void                    │
           │ + decreaseItem(): void                               │
           │ + getItemCount(): int                                │
           └──────────────────────────────────────────────────────┘
                         │                    ▲
                         │ holds current      │ states call setState()
                         ▼                    │ and getItemCount() on machine
                  ┌──────────────────┐        │
                  │   VendingState   │ ───────┘
                  └──────────────────┘  (bidirectional dependency)

           ┌──────────────────────────────────────────────────────┐
           │                      Main                            │
           │                    (Client)                          │
           ├──────────────────────────────────────────────────────┤
           │ + main(args): void                                   │
           └──────────────────────────────────────────────────────┘
                         │ creates VendingMachine(2) and
                         │ calls insertCoin() / pressButton() / dispense()
```

## State pattern roles

| Role             | Class / Interface  | Responsibility                                                      |
|------------------|--------------------|---------------------------------------------------------------------|
| State            | `VendingState`     | Common interface — each action takes the context as parameter       |
| Concrete State   | `IdleState`        | Waiting for coin; `insertCoin` transitions to `HasCoinState`        |
| Concrete State   | `HasCoinState`     | Coin inserted; `pressButton` transitions to `DispensingState`       |
| Concrete State   | `DispensingState`  | Dispensing; `dispense` transitions to `IdleState` or `EmptyState`   |
| Concrete State   | `EmptyState`       | Out of stock; all actions print appropriate message                 |
| Context          | `VendingMachine`   | Delegates every action to current state; exposes `setState()`       |
| Client           | `Main`             | Creates context and triggers actions                                |

## State transition diagram

```
          ● start
              │
              ▼
         ┌─────────┐  insertCoin()   ┌────────────┐  pressButton()  ┌─────────────┐
         │  IDLE   │────────────────►│  HAS_COIN  │────────────────►│ DISPENSING  │
         └─────────┘                 └────────────┘                 └─────────────┘
              ▲                                                            │
              │                                              [items > 0]  │  [items == 0]
              │◄───────────────────────────────────────────── dispense()  │
              │                                                            ▼
              │                                                      ┌─────────┐
              │                                                      │  EMPTY  │
              │                                                      └─────────┘
```

## Key design insight vs bad_code

| | bad_code | good_code |
|---|---|---|
| State representation | Magic string `"idle"`, `"has_coin"` … | Concrete classes `IdleState`, `HasCoinState` … |
| Adding a new state | Edit every `if-else` in every method | Add one new class; existing classes untouched |
| Typo safety | `"idel"` compiles silently | Wrong class name → compile error |
| Logic location | Scattered across all methods in one class | Each state class owns its own behaviour |
| Transitions | Hard-coded string assignments | `m.setState(new NextState())` inside the state itself |

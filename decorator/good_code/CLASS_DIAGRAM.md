```
┌──────────────────────────────────────────────────────────┐
│           decorator / good_code — Class Diagram          │
└──────────────────────────────────────────────────────────┘

                       «interface»
              ┌──────────────────────────────┐
              │            Coffee            │
              ├──────────────────────────────┤
              │ + getDescription(): String   │
              │ + getCost(): double          │
              └──────────────────────────────┘
                   ▲                  ▲
                   │                  │
         ┌─────────────────┐  ┌───────────────────────────────┐
         │  SimpleCoffee   │  │    CoffeeDecorator «abstract» │
         ├─────────────────┤  ├───────────────────────────────┤
         │+getDescription()│  │ # coffee: Coffee              │
         │  → "Coffee"     │  ├───────────────────────────────┤
         │+getCost(): 1.00 │  │ + CoffeeDecorator(Coffee)     │
         └─────────────────┘  │ + getDescription(): String    │
                              │   → coffee.getDescription()   │
                              │ + getCost(): double           │
                              │   → coffee.getCost()          │
                              └───────────────────────────────┘
                                    ▲        ▲        ▲
                                    │        │        │
                         ┌──────────┘   ┌────┘   ┌───┘
                         │              │        │
                  ┌─────────────┐ ┌──────────┐ ┌──────────────┐
                  │MilkDecorator│ │SugarDec. │ │WhipDecorator │
                  ├─────────────┤ ├──────────┤ ├──────────────┤
                  │+getDesc()   │ │+getDesc()│ │+getDesc()    │
                  │ super()+    │ │ super()+ │ │ super()+     │
                  │ "+Milk"     │ │ "+Sugar" │ │ "+Whip"      │
                  │+getCost()   │ │+getCost()│ │+getCost()    │
                  │ super()+0.5 │ │ super()+ │ │ super()+0.75 │
                  └─────────────┘ │  0.25   │ └──────────────┘
                                  └──────────┘

              ┌──────────────────────────────────────────────────────┐
              │                       Main                          │
              ├──────────────────────────────────────────────────────┤
              │ Coffee c = new SimpleCoffee();          // $1.00     │
              │ c = new MilkDecorator(c);               // $1.50     │
              │ c = new SugarDecorator(c);              // $1.75     │
              │ c = new WhipDecorator(c);               // $2.50     │
              │ // "Coffee, +Milk, +Sugar, +Whip"                    │
              └──────────────────────────────────────────────────────┘
```

## Decorator pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Component interface | `Coffee` | Common API for base and all decorators |
| Concrete Component | `SimpleCoffee` | Base implementation — plain coffee |
| Base Decorator | `CoffeeDecorator` | Wraps a `Coffee`; delegates both methods; subclasses extend cost/description |
| Concrete Decorator | `MilkDecorator`, `SugarDecorator`, `WhipDecorator` | Each adds its cost and label on top of the wrapped component |
| Client | `Main` | Builds combos by stacking decorators at runtime — no new class needed |

## Call trace (unwrapping chain)

```
whipDecorator.getCost()
  → super.getCost() + 0.75
     → sugarDecorator.getCost() + 0.75
        → milkDecorator.getCost() + 0.25
           → simpleCoffee.getCost() + 0.50
              → 1.00
           → 1.50
        → 1.75
     → 2.50
```

## Key design insight

| | Without pattern (bad_code) | With Decorator (good_code) |
|---|---|---|
| Combinations | One subclass per combo — 2^N explosion | Stack decorators at runtime — infinite combos, no new classes |
| Adding a new topping | N new subclasses for all existing combos | One new `XxxDecorator` class |
| Runtime flexibility | Impossible — class chosen at compile time | Wrap and re-wrap any `Coffee` at runtime |
| Cost calculation | Hardcoded total in each subclass | Each decorator adds its own delta — naturally composable |

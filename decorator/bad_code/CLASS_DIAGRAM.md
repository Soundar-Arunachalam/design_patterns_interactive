```
┌──────────────────────────────────────────────────────────┐
│           decorator / bad_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌────────────────────────────────────────────┐
   │                  Coffee                   │
   ├────────────────────────────────────────────┤
   │ + getDescription(): String → "Coffee"     │
   │ + getCost(): double        → 1.00          │
   └────────────────────────────────────────────┘
            ▲         ▲          ▲
            │         │          │
 ┌──────────────┐ ┌───────────────────┐  ...more...
 │CoffeeWithMilk│ │CoffeeWithSugar    │
 ├──────────────┤ ├───────────────────┤
 │+getDesc()    │ │+getDesc()         │
 │ "..+Milk"    │ │ "..+Sugar"        │
 │+getCost():   │ │+getCost(): 1.25   │
 │  1.50        │ └───────────────────┘
 └──────────────┘
        ▲
        │
 ┌────────────────────────┐
 │CoffeeWithMilkAndSugar  │
 ├────────────────────────┤
 │+getDesc() "..+M+S"     │
 │+getCost(): 1.75        │
 └────────────────────────┘
        ▲
        │
 ┌──────────────────────────────┐
 │CoffeeWithMilkAndSugarAndWhip │
 ├──────────────────────────────┤
 │+getDesc() "..+M+S+Whip"      │
 │+getCost(): 2.25              │
 └──────────────────────────────┘

   // 4 toppings → 2^4 = 16 possible subclasses needed
   // Currently only 4 are implemented — already unmaintainable
```

## Decorator pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Base | `Coffee` | Plain coffee |
| Rigid subclass | `CoffeeWithMilk`, `CoffeeWithSugar` | Single-topping combos |
| Rigid subclass | `CoffeeWithMilkAndSugar`, `CoffeeWithMilkAndSugarAndWhip` | Multi-topping combos locked at compile time |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Subclass explosion | 4 toppings = up to 16 combinations; 5 toppings = 32 |
| No runtime flexibility | Can't add a topping to an existing order — must pick a new class |
| Duplicate logic | `getCost()` in every subclass recomputes previous toppings from scratch |
| Adding a new topping | Requires N new subclass combinations for every existing combo |

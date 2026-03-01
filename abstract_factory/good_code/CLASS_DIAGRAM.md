```
┌──────────────────────────────────────────────────────────┐
│       abstract_factory / good_code — Class Diagram       │
└──────────────────────────────────────────────────────────┘

  «interface»      «interface»      «interface»
  ┌──────────┐     ┌──────────┐     ┌──────────┐
  │  Button  │     │  Dialog  │     │  Input   │
  ├──────────┤     ├──────────┤     ├──────────┤
  │+render() │     │+show()   │     │+display()│
  └──────────┘     └──────────┘     └──────────┘
    ▲      ▲         ▲      ▲         ▲      ▲
    │      │         │      │         │      │
 ┌──────┐ ┌──────┐ ┌──────┐ ┌──────┐ ┌──────┐ ┌──────┐
 │ Web  │ │Mob.  │ │ Web  │ │Mob.  │ │ Web  │ │Mob.  │
 │Button│ │Button│ │Dialog│ │Dialog│ │Input │ │Input │
 └──────┘ └──────┘ └──────┘ └──────┘ └──────┘ └──────┘
    ▲                  ▲                  ▲
    │ creates          │ creates          │ creates
    │                  │                  │
  ┌─────────────────────────────────────────────────┐
  │               «interface»                       │
  │                UIFactory                        │
  ├─────────────────────────────────────────────────┤
  │ + createButton(): Button                        │
  │ + createDialog(): Dialog                        │
  │ + createInput():  Input                         │
  └─────────────────────────────────────────────────┘
              ▲                    ▲
              │                    │
   ┌─────────────────┐    ┌──────────────────────┐
   │  WebUIFactory   │    │   MobileUIFactory    │
   ├─────────────────┤    ├──────────────────────┤
   │+createButton()  │    │+createButton()       │
   │  → WebButton    │    │  → MobileButton      │
   │+createDialog()  │    │+createDialog()       │
   │  → WebDialog    │    │  → MobileDialog      │
   │+createInput()   │    │+createInput()        │
   │  → WebInput     │    │  → MobileInput       │
   └─────────────────┘    └──────────────────────┘
              │                    │
              └────────┬───────────┘
                       ▼
           ┌──────────────────────────────┐
           │          UIService           │
           ├──────────────────────────────┤
           │ - button: Button             │
           │ - dialog: Dialog             │
           │ - input:  Input              │
           ├──────────────────────────────┤
           │ + UIService(factory:UIFactory│
           │ + renderLoginScreen(): void  │
           └──────────────────────────────┘

           ┌──────────────────────────────────────────────┐
           │                   Main                       │
           ├──────────────────────────────────────────────┤
           │ new UIService(new WebUIFactory())            │
           │ new UIService(new MobileUIFactory())         │
           └──────────────────────────────────────────────┘
```

## Abstract Factory pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Abstract Factory | `UIFactory` | Declares creation methods for each product type |
| Concrete Factory | `WebUIFactory`, `MobileUIFactory` | Creates a consistent family of products for one platform |
| Abstract Product | `Button`, `Dialog`, `Input` | Interfaces that `UIService` programs against |
| Concrete Product | `WebButton`, `WebDialog`, `WebInput`, `MobileButton`, `MobileDialog`, `MobileInput` | Platform-specific implementations |
| Context | `UIService` | Uses only interfaces — never knows which platform it's running on |
| Client | `Main` | Chooses the factory; `UIService` receives it via constructor |

## Call trace

```
new UIService(new WebUIFactory())
  → factory.createButton() → new WebButton()
  → factory.createDialog() → new WebDialog()
  → factory.createInput()  → new WebInput()
  → renderLoginScreen()
      button.render()  → "Rendering Web Button"
      dialog.show()    → "Rendering Web Dialog"
      input.display()  → "Rendering Web Input"
```

## Key design insight

| | Without pattern (bad_code) | With Abstract Factory (good_code) |
|---|---|---|
| Adding "tablet" platform | Edit every method in `UIService` | Add `TabletUIFactory` + 3 product classes; `UIService` unchanged |
| Adding a new component | Add method with if-else everywhere | Add method to `UIFactory` + 2 implementations; existing code minimal change |
| Platform consistency | Easy to mix Web button with Mobile dialog | Factory guarantees all products come from same family |
| Magic strings | `"web"`, `"mobile"` — typo is a runtime surprise | Compile-time type: `WebUIFactory` vs `MobileUIFactory` |

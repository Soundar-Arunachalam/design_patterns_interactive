```
┌──────────────────────────────────────────────────────────┐
│       abstract_factory / bad_code — Class Diagram        │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                        UIService                            │
   ├──────────────────────────────────────────────────────────────┤
   │ - platform: String                                           │
   ├──────────────────────────────────────────────────────────────┤
   │ + UIService(platform: String)                                │
   │                                                              │
   │ + renderButton(): void                                       │
   │     if "web"    → "Rendering Web Button"                     │
   │     if "mobile" → "Rendering Mobile Button"                  │
   │                                                              │
   │ + renderDialog(): void                                       │
   │     if "web"    → "Rendering Web Dialog"                     │
   │     if "mobile" → "Rendering Mobile Dialog"                  │
   │                                                              │
   │ + renderInput(): void                                        │
   │     if "web"    → "Rendering Web Input"                      │
   │     if "mobile" → "Rendering Mobile Input"                   │
   └──────────────────────────────────────────────────────────────┘

   ┌────────────────────────────────────────────────────────┐
   │                        Main                           │
   ├────────────────────────────────────────────────────────┤
   │ new UIService("web").renderButton()                    │
   │ new UIService("mobile").renderButton()                 │
   └────────────────────────────────────────────────────────┘
```

## Abstract Factory pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| God class | `UIService` | Handles every product type (button, dialog, input) and every platform inside one class with if-else |
| Client | `Main` | Passes magic strings `"web"` / `"mobile"` |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Every new platform = edit every method | Adding "tablet" means 3 separate if-else edits |
| Every new component = edit this class | Adding a Checkbox means another method with the same if-else duplicated |
| No compile-time safety | Typo `"moible"` compiles fine and silently falls through |
| Single class, unlimited growth | UIService grows forever as platforms and components multiply |

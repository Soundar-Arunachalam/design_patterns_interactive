```
┌──────────────────────────────────────────────────────────┐
│          prototype / good_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌────────────────────────────────────────────────────────┐
   │           GameCharacter  «implements Cloneable»        │
   ├────────────────────────────────────────────────────────┤
   │ - name: String                                         │
   │ - characterClass: String                               │
   │ - health: int                                          │
   │ - attack: int                                          │
   │ - defense: int                                         │
   │ - speed: int                                           │
   │ - weapon: String                                       │
   │ - armor: String                                        │
   ├────────────────────────────────────────────────────────┤
   │ + GameCharacter(class, health, attack,                 │
   │                 defense, speed, weapon, armor)         │
   │   ← no name in template constructor                    │
   │ + cloneFor(playerName: String): GameCharacter          │
   │   → super.clone() then sets name                       │
   │ + display(): void                                      │
   └────────────────────────────────────────────────────────┘
                         ▲
                         │ stores + creates
                         │
   ┌────────────────────────────────────────────────────────┐
   │                  CharacterRegistry                     │
   ├────────────────────────────────────────────────────────┤
   │ - templates: HashMap<String, GameCharacter>            │
   ├────────────────────────────────────────────────────────┤
   │ + register(type: String,                               │
   │            template: GameCharacter): void              │
   │ + create(type: String,                                 │
   │          playerName: String): GameCharacter            │
   │   → templates.get(type).cloneFor(playerName)           │
   └────────────────────────────────────────────────────────┘
                         │
                         │ used by
                         ▼
   ┌────────────────────────────────────────────────────────┐
   │                       Main                            │
   ├────────────────────────────────────────────────────────┤
   │ registry.register("warrior",                           │
   │   new GameCharacter("Warrior",100,15,10,8,"Sword","Shield"))│
   │ registry.register("mage", ...)                         │
   │ registry.register("rogue", ...)                        │
   │                                                        │
   │ registry.create("warrior", "Alice")  // clone          │
   │ registry.create("warrior", "Bob")    // clone          │
   │ registry.create("mage",    "Carol")  // clone          │
   └────────────────────────────────────────────────────────┘
```

## Prototype pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Prototype | `GameCharacter` | Implements `Cloneable`; `cloneFor()` creates a shallow copy with a new name |
| Registry | `CharacterRegistry` | Stores named templates; returns clones on demand |
| Client | `Main` | Registers templates once; calls `create()` per player — no field repetition |

## Clone call trace

```
registry.create("warrior", "Alice")
  → templates.get("warrior")             // warrior template
  → warriorTemplate.cloneFor("Alice")    // super.clone() + setName("Alice")
  → returns new GameCharacter (same stats, different name)

// All 8 stats copied automatically by super.clone()
// Only name differs between players
```

## Key design insight

| | Without pattern (bad_code) | With Prototype (good_code) |
|---|---|---|
| Creating 10 warriors | Repeat all 8 fields × 10 | `registry.create("warrior", "name")` × 10 |
| Adding a 9th field | Update every constructor call | Add field to template; all clones get it automatically |
| Template source of truth | Scattered in every `new` call | Registered once in registry |
| New character class | Copy-paste a new block of 8 values | `register("paladin", new GameCharacter(...))` once |

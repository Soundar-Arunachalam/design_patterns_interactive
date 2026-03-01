```
┌──────────────────────────────────────────────────────────┐
│          prototype / bad_code — Class Diagram            │
└──────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────┐
   │                        GameCharacter                         │
   ├───────────────────────────────────────────────────────────────┤
   │ + name: String                                                │
   │ + characterClass: String                                      │
   │ + health: int                                                 │
   │ + attack: int                                                 │
   │ + defense: int                                                │
   │ + speed: int                                                  │
   │ + weapon: String                                              │
   │ + armor: String                                               │
   ├───────────────────────────────────────────────────────────────┤
   │ + GameCharacter(name, class, health, attack,                  │
   │                 defense, speed, weapon, armor)                │
   └───────────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────────┐
   │                            Main                                  │
   ├───────────────────────────────────────────────────────────────────┤
   │ // Create template stats once...                                  │
   │ // Then manually copy ALL 8 fields for each player:              │
   │                                                                   │
   │ GameCharacter player1 = new GameCharacter(                        │
   │   "Alice", "Warrior", 100, 15, 10, 8, "Sword", "Shield");        │
   │ GameCharacter player2 = new GameCharacter(                        │
   │   "Bob",   "Warrior", 100, 15, 10, 8, "Sword", "Shield");        │
   │ GameCharacter player3 = new GameCharacter(                        │
   │   "Carol", "Warrior", 100, 15, 10, 8, "Sword", "Shield");        │
   │                                                                   │
   │ // Changing class to Mage? Update 8 fields × N players.          │
   └───────────────────────────────────────────────────────────────────┘
```

## Prototype pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Object to copy | `GameCharacter` | 8-arg constructor; no clone support |
| Client | `Main` | Manually re-passes all 8 fields for each player — copy-paste code |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Manual copy | All 8 stats repeated per player; easy to mis-type one value |
| Brittle to field changes | Adding a 9th stat (`luck`) → update every call in Main |
| No template reuse | Stats can't be centralised — they live scattered across constructors |
| Open invitation for inconsistency | Two copies of the same class can accidentally differ by one field |

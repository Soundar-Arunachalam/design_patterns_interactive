```
┌──────────────────────────────────────────────────────────┐
│           flyweight / good_code — Class Diagram          │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │               ParticleType  «flyweight»                 │
   ├──────────────────────────────────────────────────────────┤
   │ - type:   String  (final) ← INTRINSIC — shared          │
   │ - color:  String  (final) ← INTRINSIC — shared          │
   │ - sprite: String  (final) ← INTRINSIC — shared          │
   ├──────────────────────────────────────────────────────────┤
   │ + ParticleType(type, color, sprite)                      │
   │ + render(x: int, y: int): void                           │
   │   ← extrinsic state passed in, not stored               │
   └──────────────────────────────────────────────────────────┘
                         ▲
                         │ referenced by many
                         │
   ┌──────────────────────────────────────────────────────────┐
   │                       Particle                          │
   ├──────────────────────────────────────────────────────────┤
   │ - type: ParticleType  ← shared flyweight reference       │
   │ - x:    int           ← EXTRINSIC — unique per particle  │
   │ - y:    int           ← EXTRINSIC — unique per particle  │
   ├──────────────────────────────────────────────────────────┤
   │ + Particle(type: ParticleType, x: int, y: int)          │
   │ + render(): void → type.render(x, y)                    │
   └──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │                    ParticleFactory                      │
   ├──────────────────────────────────────────────────────────┤
   │ - cache: HashMap<String, ParticleType>                  │
   ├──────────────────────────────────────────────────────────┤
   │ + getType(type, color, sprite): ParticleType            │
   │   → cache.computeIfAbsent(type,                         │
   │       k → new ParticleType(type, color, sprite))        │
   └──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │                        Main                             │
   ├──────────────────────────────────────────────────────────┤
   │ ParticleFactory factory = new ParticleFactory();         │
   │ // 10,000 fire particles share 1 ParticleType object    │
   │ for (int i = 0; i < 10000; i++)                         │
   │   particles.add(new Particle(                           │
   │     factory.getType("fire","red","fire.png"), x, y));   │
   └──────────────────────────────────────────────────────────┘
```

## Flyweight pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Flyweight | `ParticleType` | Immutable shared object holding intrinsic state only |
| Context | `Particle` | Holds flyweight reference + unique extrinsic state (x, y) |
| Flyweight Factory | `ParticleFactory` | Cache that ensures each type is created once and reused |
| Client | `Main` | Creates `Particle` objects using factory — gets shared types automatically |

## Memory comparison

```
Bad code  — 10,000 Particle objects × 7 fields each
           = 10,000 copies of "fire", "red", "fire.png", x, y, vx, vy

Good code — 1 ParticleType object ("fire","red","fire.png") shared
            10,000 Particle objects × 3 fields each (ref, x, y)
           → ~3× less data stored per particle
```

## Key design insight

| | Without pattern (bad_code) | With Flyweight (good_code) |
|---|---|---|
| Intrinsic state | Copied into every instance | Stored once per type in `ParticleType` |
| 3 particle types × 10K each | 30,000 full objects | 3 `ParticleType` + 30,000 lightweight `Particle` |
| Adding `render()` logic | Must be in every `Particle` | Lives in shared `ParticleType.render(x, y)` |
| Extrinsic state | Mixed with intrinsic inside each object | Passed as parameters — not stored in flyweight |

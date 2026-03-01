```
┌──────────────────────────────────────────────────────────┐
│           flyweight / bad_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────┐
   │                          Particle                            │
   ├───────────────────────────────────────────────────────────────┤
   │ + type:   String   ← INTRINSIC (same for all "fire" particles)│
   │ + color:  String   ← INTRINSIC (duplicated across thousands)  │
   │ + sprite: String   ← INTRINSIC (large data, duplicated)       │
   │ + x:      int      ← extrinsic (unique per particle)          │
   │ + y:      int      ← extrinsic (unique per particle)          │
   │ + vx:     int      ← extrinsic (unique per particle)          │
   │ + vy:     int      ← extrinsic (unique per particle)          │
   ├───────────────────────────────────────────────────────────────┤
   │ + Particle(type, color, sprite, x, y, vx, vy)                │
   │ + render(): void                                              │
   └───────────────────────────────────────────────────────────────┘

   // 10,000 "fire" particles all store "fire", "red", "fire.png"
   // Same 3 fields × 10,000 = massive memory waste

   ┌──────────────────────────────────────────────────────────────┐
   │                          Main                               │
   ├──────────────────────────────────────────────────────────────┤
   │ // 10,000 individual Particle objects,                       │
   │ // each carrying all 7 fields independently                  │
   └──────────────────────────────────────────────────────────────┘
```

## Flyweight pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Bloated object | `Particle` | Holds both intrinsic (shared) and extrinsic (unique) state in every instance |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Duplicated intrinsic state | `type`, `color`, `sprite` identical for all particles of same type, yet stored N times |
| Memory waste | 10,000 fire particles each allocate `sprite = "fire.png"` — 10,000 copies of the same string payload |
| No sharing | No mechanism to reuse shared data across instances |
| Scaling | Adding "smoke" and "explosion" types multiplies the duplication further |

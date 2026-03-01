```
┌──────────────────────────────────────────────────────────┐
│           command / bad_code — Class Diagram             │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                     SmartHomeRemote                         │
   ├──────────────────────────────────────────────────────────────┤
   │ - lightOn: boolean  = false                                  │
   │ - volume:  int      = 50                                     │
   ├──────────────────────────────────────────────────────────────┤
   │ + pressLightOn():  void → lightOn = true                     │
   │ + pressLightOff(): void → lightOn = false                    │
   │ + pressVolumeUp(): void → volume += 10                       │
   │                                                              │
   │ // No command objects — actions are direct state mutations   │
   │ // No history deque — nothing to undo                        │
   │ // Can't schedule, queue, or serialize actions               │
   └──────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                          Main                               │
   ├──────────────────────────────────────────────────────────────┤
   │ remote.pressLightOn()                                        │
   │ remote.pressVolumeUp()                                       │
   │ remote.pressLightOff()                                       │
   │                                                              │
   │ // User: "Undo last action."                                 │
   │ // You: *has no undo mechanism*                             │
   └──────────────────────────────────────────────────────────────┘
```

## Command pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Invoker (broken) | `SmartHomeRemote` | Hard-codes every device action directly — no indirection, no history |
| Client | `Main` | Calls methods directly on remote |

## Problems with this code

| Problem | Detail |
|---------|--------|
| No undo | Actions are immediate state changes — there's nothing to reverse |
| No history | No command stack — past actions are unrecoverable |
| Can't queue or delay | Actions fire instantly at method call — no way to schedule |
| Adding a new device | Must add new fields + new methods directly in `SmartHomeRemote` |
| Remote knows all devices | Tight coupling — every device must be wired into the remote class |

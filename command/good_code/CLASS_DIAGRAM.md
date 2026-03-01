```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                  command / good_code — Class Diagram                            │
└─────────────────────────────────────────────────────────────────────────────────┘

                            «interface»
                       ┌────────────────────────┐
                       │        Command         │
                       ├────────────────────────┤
                       │ + execute(): void      │
                       │ + undo(): void         │
                       └────────────────────────┘
                                  ▲
          ┌───────────────────────┼──────────────────────────────────────────────┐
          │                       │                                              │
     (Light commands)             │                           (WashingMachine commands)
          │                       │                                              │
┌──────────────────────┐          │          ┌──────────────────────────────────────┐
│   LightOnCommand     │          │          │      WashingMachineOnCommand         │
├──────────────────────┤          │          ├──────────────────────────────────────┤
│ - light: Light       │          │          │ - machine: WashingMachine            │
├──────────────────────┤          │          ├──────────────────────────────────────┤
│ + execute() → on()   │          │          │ + execute() → machine.on()           │
│ + undo()    → off()  │          │          │ + undo()    → machine.off()          │
└──────────────────────┘          │          └──────────────────────────────────────┘
┌──────────────────────┐          │          ┌──────────────────────────────────────┐
│   LightOffCommand    │          │          │      WashingMachineOffCommand        │
├──────────────────────┤          │          ├──────────────────────────────────────┤
│ - light: Light       │          │          │ - machine: WashingMachine            │
├──────────────────────┤          │          ├──────────────────────────────────────┤
│ + execute() → off()  │          │          │ + execute() → machine.off()          │
│ + undo()    → on()   │          │          │ + undo()    → machine.on()           │
└──────────────────────┘          │          └──────────────────────────────────────┘
          │                       │          ┌──────────────────────────────────────┐
          │ hold ref              │          │     WashingMachineSpinCommand        │
          ▼                       │          ├──────────────────────────────────────┤
┌──────────────────────┐          │          │ - machine: WashingMachine            │
│        Light         │          │          ├──────────────────────────────────────┤
│      (Receiver)      │          │          │ + execute() → machine.spin()         │
├──────────────────────┤          │          │ + undo()    → "SPIN cancelled"       │
│ + on():  void        │          │          └──────────────────────────────────────┘
│ + off(): void        │          │          ┌──────────────────────────────────────┐
└──────────────────────┘          │          │     WashingMachineRinseCommand       │
                                  │          ├──────────────────────────────────────┤
                                  │          │ - machine: WashingMachine            │
                                  │          ├──────────────────────────────────────┤
                                  │          │ + execute() → machine.rinse()        │
                                  │          │ + undo()    → "RINSE cancelled"      │
                                  │          └──────────────────────────────────────┘
                                  │          ┌──────────────────────────────────────┐
                                  │          │      WashingMachineDryCommand        │
                                  │          ├──────────────────────────────────────┤
                                  │          │ - machine: WashingMachine            │
                                  │          ├──────────────────────────────────────┤
                                  │          │ + execute() → machine.dry()          │
                                  │          │ + undo()    → "DRY cancelled"        │
                                  │          └──────────────────────────────────────┘
                                  │                        │
                                  │                        │ hold ref
                                  │                        ▼
                                  │          ┌──────────────────────────────────────┐
                                  │          │          WashingMachine              │
                                  │          │            (Receiver)                │
                                  │          ├──────────────────────────────────────┤
                                  │          │ + on():    void                      │
                                  │          │ + off():   void                      │
                                  │          │ + spin():  void                      │
                                  │          │ + rinse(): void                      │
                                  │          │ + dry():   void                      │
                                  │          └──────────────────────────────────────┘
                                  │
                    ┌─────────────┴──────────────────┐
                    │         RemoteControl           │
                    │           (Invoker)             │
                    ├─────────────────────────────────┤
                    │ - history: Deque<Command>       │
                    ├─────────────────────────────────┤
                    │ + press(command: Command): void │
                    │ + undoLast(): void              │
                    └─────────────────────────────────┘
                                  │
                        uses (Command interface only)

                    ┌─────────────────────────────────┐
                    │             Main                │
                    │           (Client)              │
                    ├─────────────────────────────────┤
                    │ + main(args): void              │
                    └─────────────────────────────────┘
                          │ creates Light + WashingMachine,
                          │ wraps in commands, calls remote.press() / undoLast()
```

## Command pattern roles

| Role             | Class(es)                                                                                              | Responsibility                                                      |
|------------------|--------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| Command          | `Command`                                                                                              | Interface — `execute()` and `undo()`                                |
| Concrete Command | `LightOnCommand`, `LightOffCommand`                                                                    | Wrap `Light`; each knows how to reverse itself                      |
| Concrete Command | `WashingMachineOnCommand`, `WashingMachineOffCommand`, `WashingMachineSpinCommand`, `WashingMachineRinseCommand`, `WashingMachineDryCommand` | Wrap `WashingMachine`; cycle steps undo as "cancelled" |
| Receiver         | `Light`                                                                                                | Real device — `on()` / `off()`                                      |
| Receiver         | `WashingMachine`                                                                                       | Real device — `on()`, `off()`, `spin()`, `rinse()`, `dry()`        |
| Invoker          | `RemoteControl`                                                                                        | Holds history stack; calls any `Command` without knowing the device |
| Client           | `Main`                                                                                                 | Creates receivers, wraps in commands, injects into invoker          |

## Undo call trace

```
=== Lights ===
remote.press(LightOnCommand)              → Light ON       history: [LightOn]
remote.press(LightOffCommand)             → Light OFF      history: [LightOff, LightOn]
remote.press(LightOnCommand)              → Light ON       history: [LightOn, LightOff, LightOn]
remote.undoLast()  → pop LightOn  → undo() → Light OFF
remote.undoLast()  → pop LightOff → undo() → Light ON

=== Washing Machine ===
remote.press(WashingMachineOnCommand)     → WM ON          history: [..., WMOn]
remote.press(WashingMachineRinseCommand)  → WM RINSING     history: [..., WMRinse, WMOn]
remote.press(WashingMachineSpinCommand)   → WM SPINNING    history: [..., WMSpin, ...]
remote.press(WashingMachineDryCommand)    → WM DRYING      history: [..., WMDry, ...]
remote.press(WashingMachineOffCommand)    → WM OFF         history: [..., WMOff, WMDry, ...]
remote.undoLast()  → pop WMOff → undo() → Washing Machine ON
remote.undoLast()  → pop WMDry → undo() → "Washing Machine DRY cancelled"
```

## Key design insight

| | Without pattern | With Command pattern |
|---|---|---|
| Undo support | Impossible — direct calls, no history | `history.pop().undo()` — every command self-reverses |
| Adding a new device | Edit the remote with more if-else | Add `Receiver` + `Command` classes; `RemoteControl` untouched |
| Adding WashingMachine | Rewrite remote | 1 receiver + 5 command classes — zero edits to existing code |
| Queuing / scheduling | Calls happen immediately | Commands are objects — store, delay, or replay freely |
| Invoker coupling | Knows every device and action | Knows only the `Command` interface |

```
┌──────────────────────────────────────────────────────────┐
│            bridge / good_code — Class Diagram            │
└──────────────────────────────────────────────────────────┘

  Abstraction hierarchy          Implementation hierarchy
  (notification types)           (message channels)

                                       «interface»
                               ┌───────────────────────────┐
                               │       MessageSender       │
                               ├───────────────────────────┤
                               │ + send(prefix: String,    │
                               │        message: String)   │
                               └───────────────────────────┘
                                    ▲       ▲       ▲
                                    │       │       │
                          ┌─────────┘  ┌───┘  ┌────┘
                          │            │      │
                    ┌──────────┐ ┌──────────┐ ┌──────────┐
                    │  Email   │ │   Sms    │ │  Slack   │
                    │  Sender  │ │  Sender  │ │  Sender  │
                    ├──────────┤ ├──────────┤ ├──────────┤
                    │+send()   │ │+send()   │ │+send()   │
                    │→"[Email] │ │→"[SMS]   │ │→"[Slack] │
                    │  prefix: │ │  prefix: │ │  prefix: │
                    │  msg"    │ │  msg"    │ │  msg"    │
                    └──────────┘ └──────────┘ └──────────┘
                         ▲            ▲            ▲
                         │ ←──── bridge (composition) ────
                         │
  ┌───────────────────────────────────────────────────┐
  │             Notification  «abstract»              │
  ├───────────────────────────────────────────────────┤
  │ # sender: MessageSender                           │
  ├───────────────────────────────────────────────────┤
  │ + Notification(sender: MessageSender)             │
  │ + send(message: String): void  «abstract»         │
  └───────────────────────────────────────────────────┘
               ▲                    ▲
               │                    │
   ┌───────────────────┐  ┌─────────────────────┐
   │ AlertNotification │  │ReminderNotification │
   ├───────────────────┤  ├─────────────────────┤
   │ + send(msg):      │  │ + send(msg):         │
   │   sender.send(    │  │   sender.send(       │
   │     "ALERT", msg) │  │     "REMINDER", msg) │
   └───────────────────┘  └─────────────────────┘

  ┌──────────────────────────────────────────────────┐
  │                      Main                        │
  ├──────────────────────────────────────────────────┤
  │ new AlertNotification(new EmailSender())         │
  │   .send("Server is down!")                       │
  │ new AlertNotification(new SmsSender())           │
  │   .send("Server is down!")                       │
  │ new ReminderNotification(new SlackSender())      │
  │   .send("Meeting in 10 min")                     │
  └──────────────────────────────────────────────────┘
```

## Bridge pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Abstraction | `Notification` | Notification type hierarchy; holds a `MessageSender` reference |
| Refined Abstraction | `AlertNotification`, `ReminderNotification` | Specific type logic; calls `sender.send()` with appropriate prefix |
| Implementor | `MessageSender` | Channel API interface |
| Concrete Implementor | `EmailSender`, `SmsSender`, `SlackSender` | Channel-specific sending logic |
| Client | `Main` | Mixes any type with any sender freely at construction time |

## Call trace

```
new AlertNotification(new EmailSender()).send("Server down!")
  → alert.send("Server down!")
  → sender.send("ALERT", "Server down!")        // EmailSender
  → "[Email] ALERT: Server down!"

new AlertNotification(new SmsSender()).send("Server down!")
  → sender.send("ALERT", "Server down!")        // SmsSender
  → "[SMS] ALERT: Server down!"
```

## Key design insight

| | Without pattern (bad_code) | With Bridge (good_code) |
|---|---|---|
| N types × M channels | N×M if-else branches in one class | N + M classes — add independently |
| Adding Slack channel | Edit every notification method | Add `SlackSender` class; no existing code changes |
| Adding PromoNotification | Add new method with M channel if-else | Add `PromoNotification` subclass; senders untouched |
| Testing | Can't isolate type from channel | Inject a mock `MessageSender` — type and channel tested separately |

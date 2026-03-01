```
┌──────────────────────────────────────────────────────────┐
│            bridge / bad_code — Class Diagram             │
└──────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────────┐
   │                       NotificationService                        │
   ├───────────────────────────────────────────────────────────────────┤
   │ + sendAlert(channel: String, message: String): void               │
   │     if "email" → "Email alert: ..."                               │
   │     if "sms"   → "SMS alert: ..."                                 │
   │     if "push"  → "Push alert: ..."                                │
   │                                                                   │
   │ + sendReminder(channel: String, message: String): void            │
   │     if "email" → "Email reminder: ..."                            │
   │     if "sms"   → "SMS reminder: ..."                              │
   │     if "push"  → "Push reminder: ..."                             │
   │                                                                   │
   │ + sendPromotion(channel: String, message: String): void           │
   │     if "email" → "Email promo: ..."                               │
   │     if "sms"   → "SMS promo: ..."                                 │
   │     if "push"  → "Push promo: ..."                                │
   └───────────────────────────────────────────────────────────────────┘
   // 3 notification types × 3 channels = 9 if-else branches
   // Adding Slack = 3 more edits; adding SMS Promo = 1 more edit

   ┌────────────────────────────────────────────────────────┐
   │                        Main                           │
   ├────────────────────────────────────────────────────────┤
   │ service.sendAlert("email", "Server is down!")          │
   │ service.sendReminder("sms", "Meeting in 10 min")       │
   │ service.sendPromotion("push", "50% off today!")        │
   └────────────────────────────────────────────────────────┘
```

## Bridge pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| God class | `NotificationService` | Contains all N×M combinations (types × channels) as if-else |
| Client | `Main` | Passes magic strings for channel selection |

## Problems with this code

| Problem | Detail |
|---------|--------|
| N×M explosion | 3 types × 3 channels = 9 branches; 4 types × 4 channels = 16 branches |
| Every new channel | Edit every notification method (3 edits for 1 new channel) |
| Every new type | Add a new method with all channel if-else branches duplicated |
| Magic strings | `"email"`, `"sms"`, `"push"` — typos are runtime bugs |

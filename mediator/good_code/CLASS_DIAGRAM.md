```
┌──────────────────────────────────────────────────────────┐
│           mediator / good_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

                       «interface»
              ┌──────────────────────────────────┐
              │          ChatMediator            │
              ├──────────────────────────────────┤
              │ + sendMessage(message: String,   │
              │               sender: User): void│
              │ + addUser(user: User): void       │
              └──────────────────────────────────┘
                              ▲
                              │
              ┌──────────────────────────────────┐
              │            ChatRoom              │
              ├──────────────────────────────────┤
              │ - users: List<User>              │
              ├──────────────────────────────────┤
              │ + addUser(user: User): void       │
              │ + sendMessage(message, sender):   │
              │   for each u in users:            │
              │     if u != sender: u.receive(...)│
              └──────────────────────────────────┘
                              │ ← all users register here
                              │
              ┌──────────────────────────────────┐
              │              User                │
              ├──────────────────────────────────┤
              │ + name: String                   │
              │ - mediator: ChatMediator         │
              ├──────────────────────────────────┤
              │ + User(name, mediator)           │
              │ + send(message): void            │
              │   → mediator.sendMessage(msg,this│
              │ + receive(from, msg): void       │
              └──────────────────────────────────┘
              // Users know ONLY the mediator — never each other

              ┌──────────────────────────────────────────────────┐
              │                    Main                          │
              ├──────────────────────────────────────────────────┤
              │ ChatRoom room = new ChatRoom();                  │
              │ User alice = new User("Alice", room);            │
              │ User bob   = new User("Bob",   room);            │
              │ User carol = new User("Carol", room);            │
              │ room.addUser(alice); room.addUser(bob);          │
              │ room.addUser(carol);                             │
              │ alice.send("Hello everyone!");                   │
              │                                                  │
              │ // Adding Dave? new User("Dave", room) +         │
              │ // room.addUser(dave). Alice/Bob/Carol unchanged. │
              └──────────────────────────────────────────────────┘
```

## Mediator pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Mediator interface | `ChatMediator` | Declares `sendMessage()` and `addUser()` |
| Concrete Mediator | `ChatRoom` | Maintains user list; routes messages to all except sender |
| Colleague | `User` | Knows only the mediator — calls `mediator.sendMessage()` to communicate |
| Client | `Main` | Creates room and users; registers users with room |

## Message flow trace

```
alice.send("Hello everyone!")
  → mediator.sendMessage("Hello everyone!", alice)    // ChatRoom
     → for bob:   bob != alice   → bob.receive("Alice", "Hello everyone!")
     → for carol: carol != alice → carol.receive("Alice", "Hello everyone!")
```

## Key design insight

| | Without pattern (bad_code) | With Mediator (good_code) |
|---|---|---|
| Adding a 4th user | Re-wire Alice, Bob, Carol with new lists | `new User("Dave", room); room.addUser(dave)` |
| User coupling | Each user holds N-1 references | Each user holds 1 mediator reference |
| User independence | Cannot exist without knowing all others | Can be created with just the mediator |
| N users wiring effort | O(N²) references | O(N) registrations — one per user |

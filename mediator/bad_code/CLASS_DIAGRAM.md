```
┌──────────────────────────────────────────────────────────┐
│            mediator / bad_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────────┐
   │                           User                                  │
   ├──────────────────────────────────────────────────────────────────┤
   │ + name: String                                                   │
   │ - others: List<User>   ← every user holds ALL other users        │
   ├──────────────────────────────────────────────────────────────────┤
   │ + User(name: String, others: List<User>)                        │
   │ + send(message: String): void                                    │
   │   → for each u in others: u.receive(name, message)              │
   │ + receive(from: String, message: String): void                  │
   └──────────────────────────────────────────────────────────────────┘

   Alice ←───────────────────────────────────────────┐
     │ others = [Bob, Carol]                         │
     ├──► Bob                                        │ re-wire on add
     └──► Carol                                      │
                                                     │
   Bob   ←──────────────────────────────────────────►│
     │ others = [Alice, Carol]                       │
     ├──► Alice                                      │
     └──► Carol                                      │

   // Adding Dave requires re-constructing Alice, Bob, Carol with new lists

   ┌──────────────────────────────────────────────────────────────────┐
   │                          Main                                   │
   ├──────────────────────────────────────────────────────────────────┤
   │ alice = new User("Alice", Arrays.asList(bob, carol));           │
   │ bob   = new User("Bob",   Arrays.asList(alice, carol));         │
   │ carol = new User("Carol", Arrays.asList(alice, bob));           │
   │ alice.send("Hello everyone!");                                  │
   └──────────────────────────────────────────────────────────────────┘
```

## Mediator pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Tightly coupled peer | `User` | Each user holds references to ALL other users |
| Client | `Main` | Must wire every pair of users manually before use |

## Problems with this code

| Problem | Detail |
|---------|--------|
| O(N²) coupling | Every user knows every other — N users = N² references to manage |
| Adding a new user | Must rebuild every existing user's `others` list |
| Users can't exist independently | User requires a list of other users to be constructed |
| Circular dependencies | Alice → Bob → Alice creates complex initialization order |

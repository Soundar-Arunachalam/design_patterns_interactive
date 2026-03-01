```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                    proxy / bad_code — Class Diagram                             │
└─────────────────────────────────────────────────────────────────────────────────┘

 ┌────────────────────┐
 │       Main         │
 │                    │
 │ + main(args): void │
 └────────────────────┘
          │
          │ creates                         creates
          ├────────────────────────────────────────────────────┐
          │                                                    │
          ▼                                                    ▼
┌───────────────────────────────────┐    ┌───────────────────────────────────┐
│         OrderController           │    │        ProfileController           │
├───────────────────────────────────┤    ├───────────────────────────────────┤
│ - userService: UserService        │    │ - userService: UserService        │
│ - cache: Map<Integer,String>  ⚠️  │    │ - cache: Map<Integer,String>  ⚠️  │
├───────────────────────────────────┤    ├───────────────────────────────────┤
│ + showOrder(userId: int): void    │    │ + showProfile(userId: int): void  │
└───────────────────────────────────┘    └───────────────────────────────────┘
          │                                          │
          │ owns & calls                             │ owns & calls
          │                                          │
          ▼                                          ▼
┌───────────────────────────────────┐    ┌───────────────────────────────────┐
│           UserService             │    │           UserService             │
├───────────────────────────────────┤    ├───────────────────────────────────┤
│ + getUserById(id: int): String    │    │ + getUserById(id: int): String    │
└───────────────────────────────────┘    └───────────────────────────────────┘
           (instance A)                             (instance B)
```

> ⚠️ Each controller creates its **own** `UserService` instance and its **own** isolated `cache`.

## Call trace (from Main)

```
Main
 ├─► OrderController.showOrder(1)
 │      cache miss → UserService-A.getUserById(1)  [DB hit #1]
 │
 ├─► OrderController.showOrder(1)
 │      cache hit  → returns from OrderController.cache
 │
 └─► ProfileController.showProfile(1)
        cache miss → UserService-B.getUserById(1)  [DB hit #2 — isolated cache!]
```

## Problems highlighted by the diagram

| Problem | Detail |
|---------|--------|
| **Duplicated cache field** | Both controllers declare `Map<Integer,String> cache` — same code copy-pasted |
| **Isolated caches** | Each controller owns its own cache, so user 1 is fetched from the DB twice |
| **Scattered cross-cutting concern** | Cache-check if-else lives inside every controller; changing the strategy means editing all of them |
| **No shared abstraction** | `UserService` is a concrete class with no interface — callers cannot be swapped to a decorated/proxied version |

> **Fix (Proxy pattern):** Extract `UserService` to an interface. Create a `CachingUserServiceProxy`  
> that wraps the real service and holds the **one shared cache**. Both controllers receive the  
> proxy via injection — zero cache logic inside the controllers.

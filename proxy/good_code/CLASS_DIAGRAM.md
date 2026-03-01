```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                   proxy / good_code — Class Diagram                             │
└─────────────────────────────────────────────────────────────────────────────────┘

              «interface»
         ┌──────────────────────────┐
         │       UserService        │
         ├──────────────────────────┤
         │ + getUserById(id): String│
         └──────────────────────────┘
                    ▲
          ┌─────────┴──────────────┐
          │ implements             │ implements
          │                        │
┌─────────────────────┐   ┌──────────────────────────────────────────┐
│   RealUserService   │   │         CachingUserServiceProxy          │
│   (Real Subject)    │   │               (Proxy)                    │
├─────────────────────┤   ├──────────────────────────────────────────┤
│                     │   │ - realService: UserService               │
│                     │   │ - cache: Map<Integer,String>  (shared ✅)│
├─────────────────────┤   ├──────────────────────────────────────────┤
│ + getUserById(id)   │   │ + getUserById(id: int): String           │
│   → hits DB         │◄──│   → checks cache first, delegates if miss│
└─────────────────────┘   └──────────────────────────────────────────┘
                                        ▲
                                        │ depends on interface only
                                        │
                           ┌────────────────────────┐
                           │         Main           │
                           │       (Client)         │
                           ├────────────────────────┤
                           │ + main(args): void     │
                           └────────────────────────┘
```

## Proxy pattern roles

| Role           | Class                      | Responsibility                                        |
|----------------|----------------------------|-------------------------------------------------------|
| Subject        | `UserService`              | Common interface — clients code against this only     |
| Real Subject   | `RealUserService`          | Actual DB call, no caching concern                    |
| Proxy          | `CachingUserServiceProxy`  | Wraps real service; one shared cache; same interface  |
| Client         | `Main`                     | Receives `UserService` — never knows it's a proxy     |

## Call trace (from Main)

```
Main
 │  UserService userService = new CachingUserServiceProxy(new RealUserService())
 │
 ├─► userService.getUserById(1)   cache miss  → RealUserService.getUserById(1)  [DB hit #1]
 ├─► userService.getUserById(1)   cache hit   → returns "User-1" from cache
 ├─► userService.getUserById(1)   cache hit   → returns "User-1" from cache  ← shared, no 2nd DB call
 └─► userService.getUserById(2)   cache miss  → RealUserService.getUserById(2)  [DB hit #2]
```

## Key design insight vs bad_code

| | bad_code | good_code |
|---|---|---|
| Cache location | Duplicated inside every controller | One place — `CachingUserServiceProxy` |
| Cache sharing | Each controller has its own isolated cache | All callers share the same proxy instance |
| Swapping strategy | Edit every controller | Replace proxy class; controllers untouched |
| Interface | Concrete class only | `UserService` interface — open for extension |

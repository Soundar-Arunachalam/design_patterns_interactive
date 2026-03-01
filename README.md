# Design Patterns — Interactive Lecture Reference

A hands-on Java repository covering all **23 Gang of Four (GoF) design patterns**.  
Every pattern is presented in two versions — a deliberately broken `bad_code` and a
refactored `good_code` — so you can see exactly *why* the pattern exists and *how* it
solves a real design problem.

---

## Table of Contents

1. [How This Repo Is Organised](#how-this-repo-is-organised)
2. [Folder Convention](#folder-convention)
3. [Patterns — Creational](#patterns--creational)
4. [Patterns — Structural](#patterns--structural)
5. [Patterns — Behavioural](#patterns--behavioural)
6. [Assignment Problems](#assignment-problems)
7. [How to Read a Class Diagram](#how-to-read-a-class-diagram)
8. [Quick Reference](#quick-reference)

---

## How This Repo Is Organised

```
dp_interactive/
│
├── <pattern_name>/
│   ├── bad_code/          ← broken / naive implementation
│   │   ├── *.java
│   │   └── CLASS_DIAGRAM.md
│   └── good_code/         ← pattern-applied implementation
│       ├── *.java
│       └── CLASS_DIAGRAM.md
│
└── assignment/            ← 10 practice problems (no solutions provided)
    ├── problem_1/
    ├── problem_2/
    │   └── ...
    └── problem_10/
```

The repository contains **23 pattern folders** (one per GoF pattern) plus one
`assignment` folder.

---

## Folder Convention

| Folder | Purpose |
|--------|---------|
| `bad_code/` | Shows **what happens without the pattern** — tight coupling, duplication, hard-coded branching, or broken extensibility. Read this first to feel the pain. |
| `good_code/` | Shows **the pattern applied** — clean roles, open for extension, easy to test. |
| `CLASS_DIAGRAM.md` | ASCII class diagram, role table, call trace, and a problems vs. insights comparison table. |

> **Suggested workflow:** read `bad_code/Main.java` → open `bad_code/CLASS_DIAGRAM.md`
> to see the structural problem → read `good_code/` to see the fix → open
> `good_code/CLASS_DIAGRAM.md` to confirm your understanding.

---

## Patterns — Creational

Creational patterns deal with **object creation** — decoupling what gets created from
how and when it gets created.

| # | Pattern | Intent | Bad Code Problem | Good Code Fix |
|---|---------|--------|-----------------|---------------|
| 1 | **Singleton** | Ensure a class has exactly one instance | Public constructor → multiple logger instances, inconsistent state | `private` constructor + `synchronized getInstance()` |
| 2 | **Factory Method** | Subclasses decide which class to instantiate | `if-else` on format string inside `ReportService` to pick PDF / Excel / CSV | Abstract `ReportExporter` + `createReport()` factory method per subclass |
| 3 | **Abstract Factory** | Create families of related objects without specifying their class | Three `if-else` methods in `UIService` mixing Web and Mobile widgets | `UIFactory` interface + `WebUIFactory` / `MobileUIFactory` producing matched widget sets |
| 4 | **Builder** | Construct complex objects step by step | Telescoping constructors on `EmailMessage` — caller passes `null, null` to reach `isHtml` | Inner `Builder` class with fluent setters; all fields `final` |
| 5 | **Prototype** | Clone existing objects instead of constructing new ones | `GameCharacter` repeated with all 8 stats manually per player | `GameCharacter` implements `Cloneable` + `CharacterRegistry` cache |

### Folder Links — Creational

- [singleton/bad_code](singleton/bad_code/) · [CLASS_DIAGRAM](singleton/bad_code/CLASS_DIAGRAM.md)  |  [singleton/good_code](singleton/good_code/) · [CLASS_DIAGRAM](singleton/good_code/CLASS_DIAGRAM.md)
- [factory_method/bad_code](factory_method/bad_code/) · [CLASS_DIAGRAM](factory_method/bad_code/CLASS_DIAGRAM.md)  |  [factory_method/good_code](factory_method/good_code/) · [CLASS_DIAGRAM](factory_method/good_code/CLASS_DIAGRAM.md)
- [abstract_factory/bad_code](abstract_factory/bad_code/) · [CLASS_DIAGRAM](abstract_factory/bad_code/CLASS_DIAGRAM.md)  |  [abstract_factory/good_code](abstract_factory/good_code/) · [CLASS_DIAGRAM](abstract_factory/good_code/CLASS_DIAGRAM.md)
- [builder/bad_code](builder/bad_code/) · [CLASS_DIAGRAM](builder/bad_code/CLASS_DIAGRAM.md)  |  [builder/good_code](builder/good_code/) · [CLASS_DIAGRAM](builder/good_code/CLASS_DIAGRAM.md)
- [prototype/bad_code](prototype/bad_code/) · [CLASS_DIAGRAM](prototype/bad_code/CLASS_DIAGRAM.md)  |  [prototype/good_code](prototype/good_code/) · [CLASS_DIAGRAM](prototype/good_code/CLASS_DIAGRAM.md)

---

## Patterns — Structural

Structural patterns deal with **class and object composition** — how objects are
assembled into larger structures while keeping them flexible and efficient.

| # | Pattern | Intent | Bad Code Problem | Good Code Fix |
|---|---------|--------|-----------------|---------------|
| 6 | **Adapter** | Convert an incompatible interface into one a client expects | `PaymentService` directly calls 3 SDK clients with `if-else` + inconsistent units (cents vs dollars) | `PaymentProcessor` interface + 4 adapters (PayPal, Stripe, Square, ApplePay) |
| 7 | **Bridge** | Decouple an abstraction from its implementation | `NotificationService` has N×M if-else combinations (3 types × 3 channels) | `Notification` abstract + `MessageSender` interface; types and channels vary independently |
| 8 | **Composite** | Treat individual objects and compositions uniformly | `FileSystem` with public `String[]` files and folders — no nesting, no polymorphism | `FileComponent` interface + `File` leaf + `Directory` composite; unlimited nesting |
| 9 | **Decorator** | Add behaviour to objects dynamically without subclassing | Subclass explosion — `CoffeeWithMilk`, `CoffeeWithMilkAndSugar`, … | `CoffeeDecorator` abstract wraps any `Coffee`; stacking unlimited at runtime |
| 10 | **Facade** | Provide a simplified interface to a complex subsystem | Callers must orchestrate 5 subsystems (decoder, mixer, parser, encoder, thumbnail) manually | `VideoProcessorFacade.process(file)` hides all 5 steps behind one call |
| 11 | **Flyweight** | Share common state among many fine-grained objects | `Particle` stores all 7 fields per instance — type/color/sprite repeated across 10 K particles | `ParticleType` flyweight (intrinsic) + `Particle` (extrinsic x,y only) + factory cache |
| 12 | **Proxy** | Provide a surrogate that controls access to another object | Direct database calls on every request — no caching, no auth check | `ImageProxy` / access-control proxy intercepts calls before delegating to real subject |

### Folder Links — Structural

- [adapter/bad_code](adapter/bad_code/) · [CLASS_DIAGRAM](adapter/bad_code/CLASS_DIAGRAM.md)  |  [adapter/good_code](adapter/good_code/) · [CLASS_DIAGRAM](adapter/good_code/CLASS_DIAGRAM.md)
- [bridge/bad_code](bridge/bad_code/) · [CLASS_DIAGRAM](bridge/bad_code/CLASS_DIAGRAM.md)  |  [bridge/good_code](bridge/good_code/) · [CLASS_DIAGRAM](bridge/good_code/CLASS_DIAGRAM.md)
- [composite/bad_code](composite/bad_code/) · [CLASS_DIAGRAM](composite/bad_code/CLASS_DIAGRAM.md)  |  [composite/good_code](composite/good_code/) · [CLASS_DIAGRAM](composite/good_code/CLASS_DIAGRAM.md)
- [decorator/bad_code](decorator/bad_code/) · [CLASS_DIAGRAM](decorator/bad_code/CLASS_DIAGRAM.md)  |  [decorator/good_code](decorator/good_code/) · [CLASS_DIAGRAM](decorator/good_code/CLASS_DIAGRAM.md)
- [facade/bad_code](facade/bad_code/) · [CLASS_DIAGRAM](facade/bad_code/CLASS_DIAGRAM.md)  |  [facade/good_code](facade/good_code/) · [CLASS_DIAGRAM](facade/good_code/CLASS_DIAGRAM.md)
- [flyweight/bad_code](flyweight/bad_code/) · [CLASS_DIAGRAM](flyweight/bad_code/CLASS_DIAGRAM.md)  |  [flyweight/good_code](flyweight/good_code/) · [CLASS_DIAGRAM](flyweight/good_code/CLASS_DIAGRAM.md)
- [proxy/bad_code](proxy/bad_code/) · [CLASS_DIAGRAM](proxy/bad_code/CLASS_DIAGRAM.md)  |  [proxy/good_code](proxy/good_code/) · [CLASS_DIAGRAM](proxy/good_code/CLASS_DIAGRAM.md)

---

## Patterns — Behavioural

Behavioural patterns deal with **algorithms and responsibilities** — how objects
communicate and divide work between themselves.

| # | Pattern | Intent | Bad Code Problem | Good Code Fix |
|---|---------|--------|-----------------|---------------|
| 13 | **Chain of Responsibility** | Pass a request along a chain of handlers | `SupportService.handleTicket()` with 4 priority `if-else` branches | `SupportHandler` abstract + L1 / L2 / Manager chain; `setNext()` wiring |
| 14 | **Command** | Encapsulate a request as an object | `SmartHomeRemote` mutates state directly — no history, no undo | `Command` interface with `execute()` / `undo()` + `RemoteControl` history stack |
| 15 | **Interpreter** | Define a grammar and interpret sentences of it | `SearchEngine.evaluate()` splits on spaces with `if-else` — no nesting supported | `Expression` interface + `TermExpression` terminal + `AndExpression` / `OrExpression` / `NotExpression` composable tree |
| 16 | **Iterator** | Provide a way to traverse a collection without exposing its internals | `Playlist` with `public Song[] songs` and `public int size` — callers use raw index loops | `SongIterator` interface + `InOrderIterator` / `ShuffleIterator` + private `List` |
| 17 | **Mediator** | Define an object that encapsulates how objects interact | `User` holds `List<User>` of all others — O(N²) coupling, re-wiring on every add | `ChatMediator` interface + `ChatRoom`; each `User` knows only the mediator |
| 18 | **Memento** | Capture and restore an object's internal state | `TextEditor` has `public String content`; `Main` stores raw strings and writes back directly | `TextEditor` originator + opaque `EditorMemento` + `History` caretaker — content never leaked |
| 19 | **Observer** | Notify multiple dependents automatically when state changes | `StockMarket` has hard-wired `MobileAlert` and `EmailAlert` fields | `StockObserver` interface + `subscribe()` / `unsubscribe()` + dynamic observer list |
| 20 | **State** | Let an object alter its behaviour when its internal state changes | `VendingMachine` uses magic string state + `if-else` in every method | Each state is a class (`IdleState`, `HasCoinState`, `DispensingState`) with its own methods |
| 21 | **Strategy** | Define a family of algorithms and make them interchangeable | `DeliveryService.calculateCost()` with growing `if-else` for standard / express / drone | `DeliveryStrategy` interface + one class per algorithm; injected as a dependency |
| 22 | **Template Method** | Define the skeleton of an algorithm; let subclasses fill in the steps | `CsvImporter` and `JsonImporter` each duplicate `validate()` and `save()` | `DataImporter` abstract with `final importData()` template + abstract `parse()` / `notify()` hooks |
| 23 | **Visitor** | Add new operations to objects without modifying them | `Invoice` and `Receipt` each accumulate every operation (`exportToPdf`, `exportToXml`) as methods | `Document` interface + `accept(visitor)` + `PdfExportVisitor` / `XmlExportVisitor` |

### Folder Links — Behavioural

- [chain_of_responsibility/bad_code](chain_of_responsibility/bad_code/) · [CLASS_DIAGRAM](chain_of_responsibility/bad_code/CLASS_DIAGRAM.md)  |  [chain_of_responsibility/good_code](chain_of_responsibility/good_code/) · [CLASS_DIAGRAM](chain_of_responsibility/good_code/CLASS_DIAGRAM.md)
- [command/bad_code](command/bad_code/) · [CLASS_DIAGRAM](command/bad_code/CLASS_DIAGRAM.md)  |  [command/good_code](command/good_code/) · [CLASS_DIAGRAM](command/good_code/CLASS_DIAGRAM.md)
- [interpreter/bad_code](interpreter/bad_code/) · [CLASS_DIAGRAM](interpreter/bad_code/CLASS_DIAGRAM.md)  |  [interpreter/good_code](interpreter/good_code/) · [CLASS_DIAGRAM](interpreter/good_code/CLASS_DIAGRAM.md)
- [iterator/bad_code](iterator/bad_code/) · [CLASS_DIAGRAM](iterator/bad_code/CLASS_DIAGRAM.md)  |  [iterator/good_code](iterator/good_code/) · [CLASS_DIAGRAM](iterator/good_code/CLASS_DIAGRAM.md)
- [mediator/bad_code](mediator/bad_code/) · [CLASS_DIAGRAM](mediator/bad_code/CLASS_DIAGRAM.md)  |  [mediator/good_code](mediator/good_code/) · [CLASS_DIAGRAM](mediator/good_code/CLASS_DIAGRAM.md)
- [memento/bad_code](memento/bad_code/) · [CLASS_DIAGRAM](memento/bad_code/CLASS_DIAGRAM.md)  |  [memento/good_code](memento/good_code/) · [CLASS_DIAGRAM](memento/good_code/CLASS_DIAGRAM.md)
- [observer/bad_code](observer/bad_code/) · [CLASS_DIAGRAM](observer/bad_code/CLASS_DIAGRAM.md)  |  [observer/good_code](observer/good_code/) · [CLASS_DIAGRAM](observer/good_code/CLASS_DIAGRAM.md)
- [state/bad_code](state/bad_code/) · [CLASS_DIAGRAM](state/bad_code/CLASS_DIAGRAM.md)  |  [state/good_code](state/good_code/) · [CLASS_DIAGRAM](state/good_code/CLASS_DIAGRAM.md)
- [strategy/bad_code](strategy/bad_code/) · [CLASS_DIAGRAM](strategy/bad_code/CLASS_DIAGRAM.md)  |  [strategy/good_code](strategy/good_code/) · [CLASS_DIAGRAM](strategy/good_code/CLASS_DIAGRAM.md)
- [template_method/bad_code](template_method/bad_code/) · [CLASS_DIAGRAM](template_method/bad_code/CLASS_DIAGRAM.md)  |  [template_method/good_code](template_method/good_code/) · [CLASS_DIAGRAM](template_method/good_code/CLASS_DIAGRAM.md)
- [visitor/bad_code](visitor/bad_code/) · [CLASS_DIAGRAM](visitor/bad_code/CLASS_DIAGRAM.md)  |  [visitor/good_code](visitor/good_code/) · [CLASS_DIAGRAM](visitor/good_code/CLASS_DIAGRAM.md)

---

## Assignment Problems

The [`assignment/`](assignment/) folder contains **10 practice problems**.  
Each problem gives you broken starter code and asks you to refactor it using the
appropriate design pattern. **No solution files are provided** — work it out yourself!

> **How to approach each problem:**
> 1. Read `Main.java` — it contains the full problem statement in the comment block.
> 2. Read the other `.java` files to understand the current (broken) design.
> 3. Identify the pattern that fixes the stated issues.
> 4. Refactor into `good_code`-style classes — create as many new files as you need.

---

### Problem 1 — Document Converter

**Folder:** [assignment/problem_1](assignment/problem_1/)  
**Files:** `DocumentConverter.java`, `Main.java`

A document management system exports content to **PDF, HTML, and Markdown**.  
A single `DocumentConverter` class handles all formats with one big `if-else`.  
Adding a new format (e.g. EPUB) requires editing the converter, and each format's
logic is tangled together — making isolated testing impossible.

**Your task:** Refactor so each format is a separate class and `DocumentConverter`
never needs to change when new formats are added.

> 💡 *Hint: Which pattern lets you define a family of algorithms and swap them
> without changing the caller?*

---

### Problem 2 — Character Factory (RPG Themes)

**Folder:** [assignment/problem_2](assignment/problem_2/)  
**Files:** `CharacterFactory.java`, `Main.java`

A role-playing game supports **Fantasy** and **Sci-Fi** themes.  
Each theme needs a matched set of equipment: Weapon, Armor, and Mount.  
`CharacterFactory` currently picks each item with a separate `if-else` method —
so nothing prevents mixing a fantasy sword with a sci-fi hover bike.

**Your task:** Refactor so each theme's complete equipment set is created together,
and adding a new theme (e.g. "Steampunk") only requires adding new classes.

> 💡 *Hint: Which pattern creates families of related objects without specifying
> their concrete classes?*

---

### Problem 3 — Text Editor with Undo

**Folder:** [assignment/problem_3](assignment/problem_3/)  
**Files:** `TextEditor.java`, `Main.java`

A text editor supports **type**, **delete**, and **bold** operations.  
Users expect Ctrl+Z to undo the last action, but operations are called directly
with no history — once something runs, it cannot be reversed.

**Your task:** Refactor so every operation is an object with both `execute()` and
`undo()` methods, and a history stack enables multi-level undo.

> 💡 *Hint: Which pattern encapsulates a request as an object, enabling queuing,
> logging, and undo?*

---

### Problem 4 — Reading List Iterator

**Folder:** [assignment/problem_4](assignment/problem_4/)  
**Files:** `ReadingList.java`, `Main.java`

A reading list stores books in a **public array**.  
Callers traverse it by directly accessing the array and its `size` field —
breaking if the internal structure ever changes to a `LinkedList`.  
There is also no way to offer alternative traversal orders (alphabetical,
reverse, genre-filtered) without rewriting every caller.

**Your task:** Make the collection private and give callers a common traversal
interface — without exposing the underlying data structure.

> 💡 *Hint: Which pattern provides a way to access elements of a collection
> sequentially without exposing its representation?*

---

### Problem 5 — Restaurant Menu (Nested)

**Folder:** [assignment/problem_5](assignment/problem_5/)  
**Files:** `MenuService.java`, `Main.java`

A restaurant menu contains individual **items** (e.g. "Burger") and **sub-menus**
(e.g. "Drinks" → ["Coke", "Water"]).  
Currently items and sub-menus are handled by completely different methods;
sub-menus cannot be nested inside sub-menus.

**Your task:** Refactor so items and sub-menus share a common interface, and
sub-menus can contain other sub-menus to any depth — callers treat them all the
same way.

> 💡 *Hint: Which pattern lets you compose objects into tree structures to
> represent part-whole hierarchies?*

---

### Problem 6 — Traffic Light State Machine

**Folder:** [assignment/problem_6](assignment/problem_6/)  
**Files:** `TrafficLight.java`, `Main.java`

A traffic light cycles through **Red → Green → Yellow → Red** and supports an
emergency override to force green.  
All state logic is implemented with magic strings (`state = "red"`) and
`if-else` blocks scattered across every method — a typo won't be caught at
compile time, and adding a new state means editing every method.

**Your task:** Refactor so each state is a class that encapsulates its own
behaviour. Adding a new state should require only a new class.

> 💡 *Hint: Which pattern allows an object to alter its behaviour when its
> internal state changes, appearing to change its class?*

---

### Problem 7 — Pricing Service (Discounts)

**Folder:** [assignment/problem_7](assignment/problem_7/)  
**Files:** `PricingService.java`, `Main.java`

An e-commerce checkout applies various discounts: **no discount, 10% off,
20% off, flat $5 off, and BOGO**.  
All logic lives in one method with a growing `if-else` chain.  
Adding a "loyalty points" discount requires editing `PricingService`, and
discounts cannot be switched at runtime based on membership tier.

**Your task:** Refactor so each discount algorithm is its own class and
`PricingService` receives the discount as a dependency rather than selecting
it with `if-else`.

> 💡 *Hint: Which pattern defines a family of algorithms, encapsulates each one,
> and makes them interchangeable?*

---

### Problem 8 — Report Generator Pipeline

**Folder:** [assignment/problem_8](assignment/problem_8/)  
**Files:** `DatabaseReportGenerator.java`, `ApiReportGenerator.java`, `Main.java`

An analytics platform generates monthly reports from a **database** and a
**REST API**.  
Both follow the same four-step pipeline: *fetch → validate → format → send by
email*.  
The validate and send steps are copy-pasted in every generator class — a bug
fix in "send email" must be applied in every class separately.

**Your task:** Refactor so the overall pipeline is defined once in a base class
with the common steps implemented there, and only the source-specific steps are
overridden by subclasses.

> 💡 *Hint: Which pattern defines the skeleton of an algorithm in a base class
> and defers some steps to subclasses?*

---

### Problem 9 — Login Facade

**Folder:** [assignment/problem_9](assignment/problem_9/)  
**Files:** `AuthService.java`, `ProfileService.java`, `RecommendationService.java`, `AuditLogger.java`, `Main.java`

A web app login flow requires **four subsystems** to work together in order:
`AuthService → ProfileService → RecommendationService → AuditLogger`.  
Every caller (controller, test, CLI) must know all four services, wire them
manually, and call them in the correct sequence.  
Adding a new step (e.g. 2FA) requires updating every caller.

**Your task:** Introduce a single class that hides all four subsystems behind
one simple `login(user, password)` method.

> 💡 *Hint: Which pattern provides a simplified interface to a complex subsystem?*

---

### Problem 10 — Pizza Toppings

**Folder:** [assignment/problem_10](assignment/problem_10/)  
**Files:** `MargheritaPizza.java`, `PepperoniPizza.java`, `MargheritaWithExtraCheese.java`, `MargheritaWithExtraCheeseAndOlives.java`, `PepperoniWithOlives.java`, `Main.java`

A pizza shop lets customers customise pizzas with toppings: **Extra Cheese,
Olives, Jalapeños**, etc.  
Currently every unique combination is a separate class — two bases × four toppings
already creates dozens of classes.  
Adding one new topping requires a new class for *every* base pizza, and there
is no way to stack toppings dynamically at runtime.

**Your task:** Refactor so toppings can be added dynamically by wrapping a base
pizza object. Each topping adds its own cost and label without creating new
subclasses.

> 💡 *Hint: Which pattern attaches additional responsibilities to an object
> dynamically, as a flexible alternative to subclassing?*

---

## How to Read a Class Diagram

Every `CLASS_DIAGRAM.md` file in this repository follows the same four-section
layout:

```
## 1. Class Diagram
   ASCII diagram showing classes, fields, methods, and relationships.

## 2. Pattern Roles
   Table mapping Role → Class(es) → Responsibility.

## 3. Call Trace / Object Example
   A concrete walkthrough of how objects interact at runtime.

## 4. Problems (bad_code) / Key Design Insights (good_code)
   A table comparing the anti-pattern issues or the benefits gained.
```

### Notation used in the diagrams

| Symbol | Meaning |
|--------|---------|
| `<<interface>>` | Java interface |
| `<<abstract>>` | Abstract class |
| `▲` (solid line) | Inheritance (`extends`) |
| `▲` (dashed line) | Implementation (`implements`) |
| `◆──` | Composition (owns / has-a with lifecycle) |
| `◇──` | Aggregation (references, independent lifecycle) |
| `───>` | Association / dependency (uses) |
| `+` prefix | `public` member |
| `-` prefix | `private` member |
| `#` prefix | `protected` member |
| `{abstract}` | Abstract method |

---

## Quick Reference

| Pattern | Category | One-line intent |
|---------|----------|-----------------|
| Singleton | Creational | One instance, global access point |
| Factory Method | Creational | Subclass decides which object to create |
| Abstract Factory | Creational | Create families of related objects |
| Builder | Creational | Construct complex objects step by step |
| Prototype | Creational | Clone existing objects |
| Adapter | Structural | Convert an interface into another |
| Bridge | Structural | Separate abstraction from implementation |
| Composite | Structural | Tree structures of uniform objects |
| Decorator | Structural | Add behaviour without subclassing |
| Facade | Structural | Simplified interface to a subsystem |
| Flyweight | Structural | Share state among many fine-grained objects |
| Proxy | Structural | Surrogate with controlled access |
| Chain of Responsibility | Behavioural | Pass request along a chain of handlers |
| Command | Behavioural | Encapsulate a request as an object |
| Interpreter | Behavioural | Grammar + interpreter for a language |
| Iterator | Behavioural | Sequential access without exposing internals |
| Mediator | Behavioural | Centralised object controls communication |
| Memento | Behavioural | Capture and restore object state |
| Observer | Behavioural | Notify dependents on state change |
| State | Behavioural | Behaviour changes with internal state |
| Strategy | Behavioural | Interchangeable family of algorithms |
| Template Method | Behavioural | Algorithm skeleton; subclasses fill steps |
| Visitor | Behavioural | New operations without modifying classes |

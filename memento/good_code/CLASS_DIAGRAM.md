```
┌──────────────────────────────────────────────────────────┐
│           memento / good_code — Class Diagram            │
└──────────────────────────────────────────────────────────┘

   ┌─────────────────────────────────────────────────────────────┐
   │                       TextEditor                           │
   │                       (Originator)                         │
   ├─────────────────────────────────────────────────────────────┤
   │ - content: String  «private»                               │
   ├─────────────────────────────────────────────────────────────┤
   │ + type(text: String): void  → content += text              │
   │ + save(): EditorMemento     → new EditorMemento(content)   │
   │ + restore(m: EditorMemento) → content = m.getContent()     │
   │ + getContent(): String                                     │
   └─────────────────────────────────────────────────────────────┘
                  │ creates / reads
                  ▼
   ┌─────────────────────────────────────────────────────────────┐
   │                     EditorMemento                          │
   │                       (Memento)                            │
   ├─────────────────────────────────────────────────────────────┤
   │ - content: String  «private final»                         │
   ├─────────────────────────────────────────────────────────────┤
   │ EditorMemento(content)  «package-private constructor»      │
   │ getContent(): String    «package-private»                  │
   │ // Main cannot read content — only TextEditor can          │
   └─────────────────────────────────────────────────────────────┘
                  │ stored in
                  ▼
   ┌─────────────────────────────────────────────────────────────┐
   │                        History                             │
   │                      (Caretaker)                           │
   ├─────────────────────────────────────────────────────────────┤
   │ - stack: Deque<EditorMemento>                              │
   ├─────────────────────────────────────────────────────────────┤
   │ + push(m: EditorMemento): void                             │
   │ + pop(): EditorMemento   → stack.pop() (or null)           │
   │ // Never reads content — treats mementos as opaque tokens  │
   └─────────────────────────────────────────────────────────────┘

   ┌─────────────────────────────────────────────────────────────┐
   │                         Main                               │
   ├─────────────────────────────────────────────────────────────┤
   │ history.push(editor.save())                                │
   │ editor.type("Hello ")                                      │
   │ history.push(editor.save())                                │
   │ editor.type("World")                                       │
   │ history.push(editor.save())                                │
   │ editor.type("!!!")                                         │
   │                                                             │
   │ // Undo:                                                    │
   │ history.pop();               // discard current snapshot   │
   │ editor.restore(history.pop());  // restore previous        │
   │ // Main never touches editor.content directly              │
   └─────────────────────────────────────────────────────────────┘
```

## Memento pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Originator | `TextEditor` | Creates snapshots (`save()`); restores from them (`restore()`); `content` is private |
| Memento | `EditorMemento` | Stores snapshot; package-private access prevents external reading |
| Caretaker | `History` | Holds the stack of mementos; never reads their contents |
| Client | `Main` | Drives save/restore; never touches editor internals |

## Undo call trace

```
editor.type("!!!")         → content = "Hello World!!!"
history.pop()              → discard "Hello World!!!" snapshot
editor.restore(history.pop())
  → memento.getContent()   → "Hello "
  → content = "Hello "
editor.getContent()        → "Hello "
```

## Key design insight

| | Without pattern (bad_code) | With Memento (good_code) |
|---|---|---|
| State encapsulation | `content` is `public` — anyone reads/writes | `content` is `private` — only editor accesses it |
| Adding a new field | Main's undo stack must change | Only `save()`/`restore()` change — Main unaffected |
| Undo responsibility | Main manages undo logic | Editor encapsulates undo via `save()`/`restore()` |
| Caretaker coupling | Stores raw string — knows the structure | Stores opaque `EditorMemento` — structure invisible |

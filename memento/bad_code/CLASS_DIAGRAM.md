```
┌──────────────────────────────────────────────────────────┐
│            memento / bad_code — Class Diagram            │
└──────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────┐
   │                        TextEditor                            │
   ├───────────────────────────────────────────────────────────────┤
   │ + content: String  «public» ← fully exposed for undo to work │
   ├───────────────────────────────────────────────────────────────┤
   │ + type(text: String): void → content += text                 │
   └───────────────────────────────────────────────────────────────┘

   ┌───────────────────────────────────────────────────────────────┐
   │                          Main                                │
   │                       (Caretaker — wrong!)                   │
   ├───────────────────────────────────────────────────────────────┤
   │ Deque<String> history = new ArrayDeque<>();                   │
   │                                                               │
   │ history.push(editor.content)  ← reads raw content directly   │
   │ editor.type("Hello ")                                         │
   │                                                               │
   │ history.push(editor.content)                                  │
   │ editor.type("World")                                          │
   │                                                               │
   │ // Undo:                                                      │
   │ history.pop();                    // discard current          │
   │ editor.content = history.peek();  // writes to editor!        │
   └───────────────────────────────────────────────────────────────┘
   // If TextEditor adds a "font" field, Main must change too.
```

## Memento pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Originator (broken) | `TextEditor` | State is `public` — any caller can read and write it directly |
| Caretaker (wrong place) | `Main` | Stores raw `String` content externally; manually rewrites `editor.content` |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Encapsulation violated | `content` is `public` — Main reaches directly into editor state |
| Undo is Main's job | Undo logic belongs to the editor, not the client |
| Fragile to state changes | Add a `font` field → Main's undo stack must also track `font` |
| History stores implementation detail | History holds raw `String` — tied to current state representation |

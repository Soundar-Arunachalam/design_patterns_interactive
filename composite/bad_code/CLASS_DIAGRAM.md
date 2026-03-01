```
┌──────────────────────────────────────────────────────────┐
│          composite / bad_code — Class Diagram            │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                        FileSystem                           │
   ├──────────────────────────────────────────────────────────────┤
   │ + files:   List<String>   (public)                           │
   │ + folders: List<String>   (public)                           │
   ├──────────────────────────────────────────────────────────────┤
   │ + addFile(name: String): void                                │
   │ + addFolder(name: String): void                              │
   │ + showContents(): void                                       │
   │     for each folder → print "📁 folder/"                    │
   │       // can't recurse — folder is just a String            │
   │     for each file   → print "  📄 file"                     │
   └──────────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────────┐
   │                         Main                                │
   ├──────────────────────────────────────────────────────────────┤
   │ root.addFolder("src")                                        │
   │ root.addFolder("test")                                       │
   │ root.addFile("README.md")                                    │
   │ root.addFile("pom.xml")                                      │
   │ root.showContents()                                          │
   │                                                              │
   │ // "How do we show files inside 'src'?"                      │
   │ // "How do we get total file count?"                         │
   │ // → Can't. Files and folders are treated differently.       │
   └──────────────────────────────────────────────────────────────┘
```

## Composite pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| No common component | — | Files and folders have no shared interface — caller must handle them separately |
| Flat container | `FileSystem` | Holds files and folders as separate string lists; no nesting supported |
| Client | `Main` | Can only create a flat, non-nestable structure |

## Problems with this code

| Problem | Detail |
|---------|--------|
| No recursion possible | Folders stored as plain `String` — can't hold children |
| Two separate lists | Any operation on "file or folder" requires two loops and type checks |
| No tree structure | Can't represent `src/main/java/` — depth is impossible |
| No polymorphism | Can't write `item.show()` — must branch on "is it a file or folder?" |

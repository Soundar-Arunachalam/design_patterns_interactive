```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                  composite / good_code — Class Diagram                          │
└─────────────────────────────────────────────────────────────────────────────────┘

              «interface»
         ┌──────────────────────┐
         │    FileSystemItem    │
         ├──────────────────────┤
         │ + show(indent): void │
         │ + fileCount(): int   │
         └──────────────────────┘
                   ▲
         ┌─────────┴──────────┐
         │                    │
         │ implements         │ implements
         │                    │
┌────────────────┐    ┌─────────────────────────────────────┐
│     File       │    │               Folder                │
│   (Leaf)       │    │            (Composite)              │
├────────────────┤    ├─────────────────────────────────────┤
│ - name: String │    │ - name: String                      │
├────────────────┤    │ - children: List<FileSystemItem>    │
│ + show(): void │    ├─────────────────────────────────────┤
│ + fileCount()  │    │ + add(item: FileSystemItem): void   │
│     → 1        │    │ + show(indent: String): void        │
└────────────────┘    │ + fileCount(): int  (recursive sum) │
                      └─────────────────────────────────────┘
                                      │
                          contains 0..* (self-referential)
                                      │
                                      ▼
                          ┌──────────────────────┐
                          │    FileSystemItem     │  ← could be File OR Folder
                          └──────────────────────┘
```

## Composite pattern roles

| Role        | Class / Interface  | Responsibility                                     |
|-------------|--------------------|----------------------------------------------------|
| Component   | `FileSystemItem`   | Common interface — client only talks to this       |
| Leaf        | `File`             | No children; `fileCount()` always returns 1        |
| Composite   | `Folder`           | Holds `List<FileSystemItem>`; delegates recursively|
| Client      | `Main`             | Builds the tree; calls `show()` / `fileCount()` on the root only |

## Object tree built in Main

```
Folder "project"
 ├── Folder "src"
 │    ├── File "Main.java"
 │    └── File "PaymentService.java"
 ├── Folder "test"
 │    └── File "PaymentServiceTest.java"
 ├── File "README.md"
 └── File "pom.xml"

root.fileCount() → 5
```

## Key design insight

- `Folder.children` is typed as `List<FileSystemItem>`, not `List<File>` or `List<Folder>`.  
- This means a `Folder` can contain other `Folder`s to any depth — the recursion in  
  `show()` and `fileCount()` works the same for every level with **zero `instanceof` checks**.

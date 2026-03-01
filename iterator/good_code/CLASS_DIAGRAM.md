```
┌──────────────────────────────────────────────────────────┐
│           iterator / good_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

                       «interface»
              ┌──────────────────────────────┐
              │         SongIterator         │
              ├──────────────────────────────┤
              │ + hasNext(): boolean         │
              │ + next(): String             │
              └──────────────────────────────┘
                   ▲                  ▲
                   │                  │
      ┌────────────────────┐  ┌────────────────────────────┐
      │  InOrderIterator   │  │     ShuffleIterator        │
      ├────────────────────┤  ├────────────────────────────┤
      │ - songs: List<Str> │  │ - songs: List<String>      │
      │ - index: int = 0   │  │   (shuffled copy)          │
      ├────────────────────┤  │ - index: int = 0           │
      │ + hasNext()        │  ├────────────────────────────┤
      │ + next()           │  │ + hasNext()                │
      └────────────────────┘  │ + next()                   │
                              └────────────────────────────┘
                    ▲                    ▲
                    │ creates            │ creates
                    └────────┬───────────┘
                             │
              ┌──────────────────────────────┐
              │           Playlist           │
              ├──────────────────────────────┤
              │ - songs: List<String> «priv» │
              ├──────────────────────────────┤
              │ + add(song: String): void    │
              │ + iterator(): SongIterator   │
              │   → new InOrderIterator(songs│
              │ + shuffleIterator():         │
              │     SongIterator             │
              │   → new ShuffleIterator(songs│
              └──────────────────────────────┘

              ┌──────────────────────────────────────────────┐
              │                    Main                      │
              ├──────────────────────────────────────────────┤
              │ SongIterator it = playlist.iterator();       │
              │ while (it.hasNext())                         │
              │     System.out.println(it.next());           │
              │                                              │
              │ SongIterator sh = playlist.shuffleIterator();│
              │ while (sh.hasNext())                         │
              │     System.out.println(sh.next());           │
              │ // Loop never changed — just swap iterator   │
              └──────────────────────────────────────────────┘
```

## Iterator pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Iterator interface | `SongIterator` | Common traversal API: `hasNext()`, `next()` |
| Concrete Iterator | `InOrderIterator` | Traverses songs in insertion order |
| Concrete Iterator | `ShuffleIterator` | Shuffles a copy of the list; traverses randomly |
| Aggregate | `Playlist` | Hides internal `List<String>`; exposes only factory methods for iterators |
| Client | `Main` | Uses `SongIterator` — unaware of storage type or order strategy |

## Traversal call trace

```
playlist.shuffleIterator()
  → new ShuffleIterator(songs)
     → Collections.shuffle(copy)
     → index = 0

while (sh.hasNext())   → index < songs.size()
    sh.next()          → songs.get(index++)

// Playlist internals unchanged — only iterator strategy swapped
```

## Key design insight

| | Without pattern (bad_code) | With Iterator (good_code) |
|---|---|---|
| Changing storage (array → List) | Every caller's loop breaks | `Playlist` internals change; caller loop unchanged |
| Shuffle traversal | Must rewrite caller loop | `playlist.shuffleIterator()` — same client loop |
| Encapsulation | `songs[]` is `public` | `songs` is `private` — hidden from callers |
| Supporting multiple collections | Different loop per type | One `SongIterator` loop works for all |

```
┌──────────────────────────────────────────────────────────┐
│            iterator / bad_code — Class Diagram           │
└──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │                  Playlist  (array-based)                │
   ├──────────────────────────────────────────────────────────┤
   │ + songs: String[]   «public» ← internal storage exposed │
   │ + size:  int        «public»                             │
   └──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │                  ArrayListPlaylist                      │
   ├──────────────────────────────────────────────────────────┤
   │ + songs: List<String>  «public»                          │
   │ + size:  int           «public»                          │
   └──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │                  HashMapPlaylist                        │
   ├──────────────────────────────────────────────────────────┤
   │ + songs: Map<String,String>  «public»                    │
   │ + size:  int                «public»                     │
   └──────────────────────────────────────────────────────────┘

   ┌──────────────────────────────────────────────────────────┐
   │                         Main                            │
   ├──────────────────────────────────────────────────────────┤
   │ // Caller must know the internal structure:              │
   │ for (int i = 0; i < playlist.size; i++)                 │
   │     System.out.println(playlist.songs[i]);              │
   │                                                          │
   │ // Switch Playlist to ArrayList → this loop breaks      │
   │ // Want shuffle? Must modify caller, not Playlist        │
   └──────────────────────────────────────────────────────────┘
```

## Iterator pattern roles (broken version)

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Exposed container | `Playlist` | Internal `songs[]` and `size` are `public` — caller loops with index |
| Parallel containers | `ArrayListPlaylist`, `HashMapPlaylist` | Same data, different storage — each requires a different loop in caller |
| Client | `Main` | Knows the index, field name, and collection type — tightly coupled to internals |

## Problems with this code

| Problem | Detail |
|---------|--------|
| Encapsulation broken | `songs` is `public` — any caller can mutate or re-order it |
| Caller couples to storage type | `songs[i]` breaks if you switch to `ArrayList` |
| No traversal variation | Shuffle order requires changing the caller's loop, not the Playlist |
| Three playlist types, three loops | Code that works on one doesn't work on others |

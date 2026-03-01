// Problem: internal storage (array) is exposed publicly so callers can iterate.
// If you switch from array to LinkedList, every caller that uses songs[i] breaks.
// You can't offer different traversal orders (e.g. shuffle) without changing the caller.

import java.util.List;
import java.util.Map;

public class Playlist {
    public String[] songs = { "Bohemian Rhapsody", "Hotel California", "Imagine", "Stairway to Heaven" };
    public int size = 4;
}

public class ArrayListPlaylist {
    public List<String> songs = List.of("Bohemian Rhapsody", "Hotel California", "Imagine", "Stairway to Heaven");
    public int size = songs.size();
}

public class HashMapPlaylist {
    public Map<String, String> songs = Map.of(
            "Bohemian Rhapsody", "Queen",
            "Hotel California", "Eagles",
            "Imagine", "John Lennon",
            "Stairway to Heaven", "Led Zeppelin"
    );
    public int size = songs.size();
}

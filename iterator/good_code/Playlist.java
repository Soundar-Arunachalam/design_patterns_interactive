import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    // Internal storage is private — callers never see it
    private List<String> songs = new ArrayList<>();

    public void add(String song) { songs.add(song); }

    // Return a standard iterator for in-order playback
    public SongIterator iterator() {
        return new InOrderIterator(songs);
    }

    // Return a shuffle iterator — caller doesn't change, just swap the iterator
    public SongIterator shuffleIterator() {
        return new ShuffleIterator(songs);
    }
}

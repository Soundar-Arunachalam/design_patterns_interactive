import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleIterator implements SongIterator {
    private List<String> songs;
    private int index = 0;

    public ShuffleIterator(List<String> songs) {
        this.songs = new ArrayList<>(songs);
        Collections.shuffle(this.songs);
    }

    public boolean hasNext() { return index < songs.size(); }
    public String next()     { return songs.get(index++); }
}

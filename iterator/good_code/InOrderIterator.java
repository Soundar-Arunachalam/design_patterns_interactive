import java.util.List;

public class InOrderIterator implements SongIterator {
    private List<String> songs;
    private int index = 0;

    public InOrderIterator(List<String> songs) { 
        this.songs = songs; }

    public boolean hasNext() { return index < songs.size(); }
    public String next()     { return songs.get(index++); }
}

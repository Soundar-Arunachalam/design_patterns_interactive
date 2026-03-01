public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.add("Bohemian Rhapsody");
        playlist.add("Hotel California");
        playlist.add("Imagine");
        playlist.add("Stairway to Heaven");

        System.out.println("-- In Order --");
        SongIterator it = playlist.iterator();
        while (it.hasNext()) System.out.println("Playing: " + it.next());

        System.out.println("\n-- Shuffle --");
        SongIterator shuffle = playlist.shuffleIterator();
        while (shuffle.hasNext()) System.out.println("Playing: " + shuffle.next());

        // Caller loop never changed. Playlist internals (array → list) can change freely.
    }
}

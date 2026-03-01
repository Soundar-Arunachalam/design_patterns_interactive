public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        // Caller is forced to know about the internal array and its size
        for (int i = 0; i < playlist.size; i++) {
            System.out.println("Playing: " + playlist.songs[i]);
        }

        System.out.println("\nIf Playlist switches to ArrayList, this loop breaks.");
        System.out.println("If we want a shuffle order, we modify the caller, not the Playlist.");
    }
}

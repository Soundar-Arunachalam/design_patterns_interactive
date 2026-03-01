// Problem: every caller must know all subsystem classes and their correct order.
// Duplicate this sequence in 3 places and you have 3 places to keep in sync.
public class Main {
    public static void main(String[] args) {
        String file = "lecture.mp4";

        VideoDecoder decoder = new VideoDecoder();
        AudioMixer mixer = new AudioMixer();
        SubtitleParser subtitles = new SubtitleParser();
        VideoEncoder encoder = new VideoEncoder();
        ThumbnailGenerator thumbs = new ThumbnailGenerator();

        // Client must know the exact order and every subsystem class
        decoder.decode(file);
        mixer.normalize(file);
        subtitles.load(file);
        encoder.encode(file, "mp4");
        thumbs.generate(file);

        System.out.println("Done.");
        System.out.println("\nWhat if the order changes? Every caller must be updated.");
    }
}

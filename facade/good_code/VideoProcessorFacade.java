// Facade: one simple method hides all subsystem complexity.
// Callers don't know about VideoDecoder, AudioMixer, etc.
// Change the pipeline order here — callers are unaffected.
public class VideoProcessorFacade {
    private VideoDecoder decoder       = new VideoDecoder();
    private AudioMixer mixer           = new AudioMixer();
    private SubtitleParser subtitles   = new SubtitleParser();
    private VideoEncoder encoder       = new VideoEncoder();
    private ThumbnailGenerator thumbs  = new ThumbnailGenerator();

    public void process(String file) {
        System.out.println("Processing: " + file);
        decoder.decode(file);
        mixer.normalize(file);
        subtitles.load(file);
        encoder.encode(file, "mp4");
        thumbs.generate(file);
        System.out.println("Done.\n");
    }
}

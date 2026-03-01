public class Main {
    public static void main(String[] args) {
        VideoProcessorFacade processor = new VideoProcessorFacade();

        // Caller knows nothing about the pipeline internals
        processor.process("lecture.mp4");
        processor.process("demo.mp4");
    }
}

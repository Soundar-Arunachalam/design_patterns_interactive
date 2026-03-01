```
┌──────────────────────────────────────────────────────────┐
│           facade / good_code — Class Diagram             │
└──────────────────────────────────────────────────────────┘

                 ┌────────────────────────────────────────┐
                 │         VideoProcessorFacade           │
                 ├────────────────────────────────────────┤
                 │ - decoder:   VideoDecoder              │
                 │ - mixer:     AudioMixer                │
                 │ - subtitles: SubtitleParser            │
                 │ - encoder:   VideoEncoder              │
                 │ - thumbnail: ThumbnailGenerator        │
                 ├────────────────────────────────────────┤
                 │ + VideoProcessorFacade()               │
                 │ + process(file: String): void          │
                 │   1. decoder.decode(file)              │
                 │   2. mixer.normalizeAudio(file)        │
                 │   3. subtitles.parse(file)             │
                 │   4. encoder.encode(file)              │
                 │   5. thumbnail.generate(file)          │
                 └────────────────────────────────────────┘
                   │       │        │        │        │
                   ▼       ▼        ▼        ▼        ▼
           ┌───────────┐ ┌───────┐ ┌──────┐ ┌───────┐ ┌──────────────────┐
           │  Video    │ │Audio  │ │Sub-  │ │Video  │ │  Thumbnail       │
           │  Decoder  │ │Mixer  │ │title │ │Encoder│ │  Generator       │
           ├───────────┤ ├───────┤ │Parser│ ├───────┤ ├──────────────────┤
           │+decode()  │ │+norm- │ ├──────┤ │+encod │ │+generate()       │
           │           │ │ alize │ │+pars │ │  e()  │ │                  │
           │           │ │ Audio │ │  e() │ │       │ │                  │
           └───────────┘ └───────┘ └──────┘ └───────┘ └──────────────────┘
                          (all subsystems hidden from client)

   ┌──────────────────────────────────────────────────┐
   │                     Main                        │
   ├──────────────────────────────────────────────────┤
   │ VideoProcessorFacade processor =                 │
   │     new VideoProcessorFacade();                  │
   │ processor.process("lecture.mp4");                │
   │ // Main never knows VideoDecoder even exists     │
   └──────────────────────────────────────────────────┘
```

## Facade pattern roles

| Role | Class(es) | Responsibility |
|------|-----------|----------------|
| Facade | `VideoProcessorFacade` | Single entry point; owns all subsystems; orchestrates them in `process()` |
| Subsystem | `VideoDecoder` | Decodes raw video frames |
| Subsystem | `AudioMixer` | Normalizes audio track |
| Subsystem | `SubtitleParser` | Parses subtitle file |
| Subsystem | `VideoEncoder` | Encodes to output format |
| Subsystem | `ThumbnailGenerator` | Generates preview thumbnail |
| Client | `Main` | Calls `processor.process()` — unaware of any subsystem |

## Call trace

```
processor.process("lecture.mp4")
  → decoder.decode("lecture.mp4")          // [Decoder] Decoding video...
  → mixer.normalizeAudio("lecture.mp4")    // [AudioMixer] Normalizing audio...
  → subtitles.parse("lecture.mp4")         // [SubtitleParser] Parsing subtitles...
  → encoder.encode("lecture.mp4")          // [Encoder] Encoding video...
  → thumbnail.generate("lecture.mp4")      // [Thumbnail] Generating thumbnail...
```

## Key design insight

| | Without Facade (bad_code) | With Facade (good_code) |
|---|---|---|
| Client knowledge | Must know and call 5 subsystems in correct order | Calls one method — `process()` |
| Subsystem changes | Any change to order or subsystem breaks every client | Change inside `process()` — clients unaffected |
| Onboarding | New developer must learn all 5 subsystems | New developer uses `VideoProcessorFacade` immediately |
| Subsystem reuse | Each subsystem used directly | Still accessible directly if advanced use needed |

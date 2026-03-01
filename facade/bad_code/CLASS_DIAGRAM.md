```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                        facade / bad_code — Class Diagram                        │
└─────────────────────────────────────────────────────────────────────────────────┘

 ┌────────────────────┐   creates &    ┌─────────────────────┐
 │       Main         │───────────────►│    VideoDecoder      │
 │                    │   calls all    ├─────────────────────┤
 │ + main(args): void │   subsystems   │ + decode(file): void│
 └────────────────────┘   directly     └─────────────────────┘
           │
           │ creates & calls            ┌─────────────────────┐
           ├──────────────────────────►│     AudioMixer       │
           │                           ├─────────────────────┤
           │                           │ + normalize(f): void │
           │                           └─────────────────────┘
           │
           │ creates & calls            ┌─────────────────────┐
           ├──────────────────────────►│   SubtitleParser     │
           │                           ├─────────────────────┤
           │                           │ + load(file): void   │
           │                           └─────────────────────┘
           │
           │ creates & calls            ┌──────────────────────────┐
           ├──────────────────────────►│      VideoEncoder         │
           │                           ├──────────────────────────┤
           │                           │ + encode(f,fmt): void     │
           │                           └──────────────────────────┘
           │
           │ creates & calls            ┌──────────────────────────┐
           └──────────────────────────►│   ThumbnailGenerator      │
                                       ├──────────────────────────┤
                                       │ + generate(file): void    │
                                       └──────────────────────────┘
```

## Call sequence (hardcoded inside Main)

```
Main
 │
 ├─1─► VideoDecoder.decode("lecture.mp4")
 ├─2─► AudioMixer.normalize("lecture.mp4")
 ├─3─► SubtitleParser.load("lecture.mp4")
 ├─4─► VideoEncoder.encode("lecture.mp4", "mp4")
 └─5─► ThumbnailGenerator.generate("lecture.mp4")
```

## Problem highlighted by the diagram

- **Main depends on 5 separate classes** — any new subsystem step means changing Main.
- **No shared interface or base class** — the 5 subsystems have nothing in common; Main wires them with raw instantiation.
- **Order is implicit** — steps 1–5 must run in sequence, but nothing enforces this except the order of lines in Main.
- **Duplication risk** — if another caller needs the same pipeline, it must repeat all 5 lines in the correct order.

> **Fix:** Introduce a `VideoProcessorFacade` that owns all 5 subsystems privately  
> and exposes a single `process(file)` method. Main then depends only on the facade.

package codecs;

public enum FileType {
    MP3("mp3"),
    WAV("wav"),
    AIFF("aiff"),
    AAC("aac"),
    WMA("wma");

    String ext;

    FileType(String ext) {
        this.ext = ext;
    }
}

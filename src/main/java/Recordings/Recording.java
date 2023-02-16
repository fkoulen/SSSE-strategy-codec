package Recordings;

import codecs.FileType;

/**
 * A Recording has a title and FileType.  The FileType indicates what kind of Codec is required
 * to play the Recording.
 */
public class Recording {
    public final FileType fileType;
    private String title;

    /**
     * Create a new Recording
     *
     * @param title    the title of this Recording as a String
     * @param fileType
     */
    public Recording(String title, FileType fileType) {
        this.title = title;
        this.fileType = fileType;
    }

    public String getTitle() {
        return title;
    }

    public FileType getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return String.format("%s.%s", title, fileType);
    }
}

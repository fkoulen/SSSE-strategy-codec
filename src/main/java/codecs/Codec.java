package codecs;


import Recordings.Recording;

/**
 * Represents a coding format for digital audio.  Examples of existing formats are given in the FileType enum.
 */
public interface Codec {
    /**
     * For demonstrating a strategy pattern this method simply returns a message saying that it
     * is decoding a file with a given file type.
     *
     * @return a String in the format "Decoding xyz" where xyz is the file type.
     */
    String decode();

    /**
     * Uses this codec to create a Recording
     *
     * @param title
     * @return recording with title and file type of this decoder.  The message should be in the form:
     * "Decoding " + FileType.ext
     */
    Recording encode(String title);

    /**
     * @return FileType of this codec.
     */
    FileType getFileType();

}

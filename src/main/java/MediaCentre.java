import Recordings.Recording;
import codecs.Codec;

/**
 * A class for demonstrating the strategy pattern.  A MediaCentre can play and record files as
 * long as it has the correct Codec.  It can only have one Codec at a time.
 */
public class MediaCentre {
    public static final String NO_DECODER = "On but no decoder available";
    public static final String DECODER_SET = "On and decoder available: ";
    public static final String INCORRECT_DECODER_FOR_PLAYING = "Incorrect decoder for playing file";
    Codec codec;
    String status;

    /**
     * Creates a MediaCentre with initially no Codec
     */
    public MediaCentre() {
        this.status = NO_DECODER;
    }

    /**
     * Set the current Codec.  The Codec can be set to null. When the
     * Codec is set the status should be: DECODER_SET+codec.getFileType() else
     * status is: NO_DECODER
     *
     * @param codec a Codec
     */

    public void setCodec(Codec codec) {
        this.codec = codec;
        if (codec == null) {
            status = NO_DECODER;
        } else {
            status = DECODER_SET + codec.getFileType();
        }
    }

    /**
     * Play a Recording.  The recording will only play if the required Codec is available.
     * The type of Codec required is deduced from the FileType of the Recording
     *
     * @param recording
     * @return status in the form of either the message returned from the Codec or
     * MediaCentre.INCORRECT_DECODER_FOR_PLAYING
     */
    public String playFile(Recording recording) {
        if (recording.getFileType() != codec.getFileType()) {
            return MediaCentre.INCORRECT_DECODER_FOR_PLAYING;
        }

        return codec.decode();
    }

    /**
     * Record a file with the title passed in.  The Recording will be of the type matching the current Codec
     * if there is one.  If there is no Codec set then null will be returned.
     *
     * @param title
     * @return a new Recording with the title and current Codec format
     */
    public Recording recordFile(String title) {
        if (codec == null) {
            return null;
        }

        return new Recording(title, codec.getFileType());
    }

    /**
     * Get the status message of this MediaCentre
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }
}

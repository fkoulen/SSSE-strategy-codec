package codecs;

import Recordings.Recording;

public class AIFF implements Codec {
    private final FileType fileType = FileType.AIFF;

    @Override
    public String decode() {
        return "Decoding " + fileType.ext;
    }

    @Override
    public Recording encode(String title) {
        return new Recording(title, fileType);
    }

    @Override
    public FileType getFileType() {
        return fileType;
    }
}

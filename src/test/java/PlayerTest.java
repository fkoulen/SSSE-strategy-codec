import Recordings.Recording;
import codecs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    MediaCentre mediaCentre;
    Codec mp3;
    Codec wav;
    Codec aac;
    Codec aiff;
    Recording rec1, rec2, rec3, rec4;

    @BeforeEach
    void setup() {
        mediaCentre = new MediaCentre();
        //TODO instances of Codec
        mp3 = new MP3();
        wav = new WAV();
        aac = new AAC();
        aiff = new AIFF();
        rec1 = new Recording("This is my strategy", FileType.MP3);
        rec2 = new Recording("The patterns of love", FileType.WAV);
        rec3 = new Recording("Like a bridge over troubled water", FileType.AAC);
        rec4 = new Recording("Smooth Decorator", FileType.AIFF);
    }

    @Test
    void testCodecsHaveCorrectFileTypes() {
        assertEquals(FileType.MP3, mp3.getFileType());
        assertEquals(FileType.WAV, wav.getFileType());
        assertEquals(FileType.AAC, aac.getFileType());
        assertEquals(FileType.AIFF, aiff.getFileType());
    }

    @Test
    void testInitialStateOfMediaCentre() {
        assertEquals(MediaCentre.NO_DECODER, mediaCentre.getStatus());
    }

    @Test
    void testStatusOfMediaCentreWithCodec() {
        mediaCentre.setCodec(mp3);
        assertEquals(MediaCentre.DECODER_SET + mp3.getFileType(), mediaCentre.getStatus());
    }

    @Test
    void testCanChangeCodec() {
        mediaCentre.setCodec(mp3);
        assertEquals(MediaCentre.DECODER_SET + mp3.getFileType(), mediaCentre.getStatus());
        mediaCentre.setCodec(null);
        assertEquals(MediaCentre.NO_DECODER, mediaCentre.getStatus());
        mediaCentre.setCodec(aac);
        assertEquals(MediaCentre.DECODER_SET + aac.getFileType(), mediaCentre.getStatus());
    }

    @Test
    void testPlayerCanPlayDifferentMedia() {
        mediaCentre.setCodec(mp3);
        assertEquals("Decoding mp3", mediaCentre.playFile(rec1));
        mediaCentre.setCodec(wav);
        assertEquals("Decoding wav", mediaCentre.playFile(rec2));
        mediaCentre.setCodec(aac);
        assertEquals("Decoding aac", mediaCentre.playFile(rec3));
    }

    @Test
    void testPlayerCannotPlayIncorrectCodec() {
        mediaCentre.setCodec(mp3);
        assertEquals(MediaCentre.INCORRECT_DECODER_FOR_PLAYING, mediaCentre.playFile(rec2));
    }

    @Test
    void playerCanRecordWithDifferentCodecs() {
        mediaCentre.setCodec(mp3);
        Recording recording1 = mediaCentre.recordFile("You've got me in a state");
        assertNotNull(recording1);
        assert (recording1.fileType.equals(FileType.MP3));
        assertEquals("You've got me in a state", recording1.getTitle());
        mediaCentre.setCodec(wav);
        Recording recording2 = mediaCentre.recordFile("Let me be your singleton");
        assert (recording2.fileType.equals(FileType.WAV));
    }

    @Test
    void testPlayerRecordWithNoCodec() {
        assertNull(mediaCentre.recordFile("It ain't gonna work Baby"));
    }

}

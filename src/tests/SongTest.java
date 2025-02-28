package tests;

import model.Song;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    @Test
    void testConstructor() {
        Song song = new Song("Bohemian Rhapsody", "Queen", "A Night at the Opera");
        assertEquals("Bohemian Rhapsody", song.getName());
        assertEquals("Queen", song.getArtist());
        assertFalse(song.getFavorite(), "New song should not be a favorite by default");
    }

    @Test
    void testRate() {
        Song song = new Song("Imagine", "John Lennon", "Imagine");
        song.rate(4);
        assertFalse(song.getFavorite(), "Song should not be favorite unless rated 5");

        song.rate(5);
        assertTrue(song.getFavorite(), "Song should become favorite when rated 5");
    }

    @Test
    void testFavorite() {
        Song song = new Song("Let It Be", "The Beatles", "Let It Be");
        assertFalse(song.getFavorite(), "New song should not be a favorite by default");

        song.favorite(true);
        assertTrue(song.getFavorite(), "Song should be marked as favorite");

        song.favorite(false);
        assertFalse(song.getFavorite(), "Song should no longer be marked as favorite");
    }

    @Test
    void testToString() {
        Song song = new Song("Hey Jude", "The Beatles", "Single");
        song.rate(5);
        String expected = "Title: Hey Jude, Album: Single, Artist: The Beatles, Rating: 5";
        assertEquals(expected, song.toString());
    }
}

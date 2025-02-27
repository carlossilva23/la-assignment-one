package tests;

import model.Song;  
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*;  

class SongTest {

    @Test
    void testGetName() {
        Song song = new Song("Shape of You", "Ed Sheeran", "Divide");
        assertEquals("Shape of You", song.getName());
    }

    @Test
    void testGetArtist() {
        Song song = new Song("Shape of You", "Ed Sheeran", "Divide");
        assertEquals("Ed Sheeran", song.getArtist());
    }

    @Test
    void testRateSong() {
        Song song = new Song("Shape of You", "Ed Sheeran", "Divide"); 
        song.rate(4);      
        assertEquals(4, song.getRating());
    }

    @Test
    void testFavoriteStatus() {
        Song song = new Song("Shape of You", "Ed Sheeran", "Divide");
        assertFalse(song.getFavorite());
        song.rate(5);
        assertTrue(song.getFavorite());
    }

    @Test
    void testToString() {
        Song song = new Song("Shape of You", "Ed Sheeran", "Divide");
        String expected = "Title: Shape of You, Album: Divide, Artist: Ed Sheeran";
        assertEquals(expected, song.toString());
    }
}

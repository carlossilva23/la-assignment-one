package tests;

import model.Album;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {
    private Album album;
    
    @BeforeEach
    void setUp() {
        album = new Album("Test Album", "Test Artist");
    }
    
    @Test
    void testConstructor() {
        assertEquals("Test Album", album.getName());
        assertEquals("Test Artist", album.getArtist());
    }
    
    @Test
    void testSetGenre() {
        album.setGenre("Rock");
        assertTrue(album.toString().contains("Rock"));
    }
    
    @Test
    void testSetYear() {
        album.setYear(2024);
        assertTrue(album.toString().contains("2024"));
    }
    
    @Test
    void testToString() {
        album.setGenre("Pop");
        album.setYear(2020);
        Song song1 = new Song("Song A", "Test Artist", "Test Album");
        Song song2 = new Song("Song B", "Test Artist", "Test Album");
        album.addSong(song1);
        album.addSong(song2);
        
        String expected = "Test Album by Test Artist (2020, Pop)\nSongs:\n  - Song A\n  - Song B";
        assertEquals(expected, album.toString());
    }
}


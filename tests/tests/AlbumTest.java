package tests;

import model.Album;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AlbumTest {

    @Test
    void testGetArtist() {
        Album album = new Album("Test Album", "Test Artist");
        assertEquals("Test Artist", album.getArtist(), "Artist name should be 'Test Artist'");
    }

    @Test
    void testSetGenre() {
        Album album = new Album("Test Album", "Test Artist");
        album.setGenre("Rock");
    }

    @Test
    void testSetYear() {
        Album album = new Album("Test Album", "Test Artist");
        album.setYear(2023);           
    }

    @Test
    void testPrint() {
        Album album = new Album("Test Album", "Test Artist");
        album.setGenre("Rock");
        album.setYear(2023);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        album.print();
        
        String expectedOutput = "Test Album by Test Artist (2023, Rock)\nSongs:\n";
        assertTrue(outContent.toString().startsWith(expectedOutput), "The print output should start with album details.");
    }

    @Test
    void testAlbumConstructor() {
        Album album = new Album("Test Album", "Test Artist");
        assertNotNull(album, "Album should be initialized");
        assertEquals("Test Album", album.getName(), "Album name should be 'Test Album'");
    }
}

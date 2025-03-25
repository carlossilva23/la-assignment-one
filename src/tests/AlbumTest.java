package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Song;

import java.util.Arrays;
import java.util.List;

class AlbumTest {
    private Album album1;
    private Album album2;
    private Album album3;
    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp() {
        album1 = new Album("Test Album", "Artist A");
        album2 = new Album("Another Album", "Artist B");
        album3 = new Album("Test Album", "Artist A");
        
        song1 = new Song("Song One", "Artist A", album1);
        song2 = new Song("Song Two", "Artist A", album1);
    }

    @Test
    void testGetters() {
        assertEquals("Test Album", album1.getName());
        assertEquals("Artist A", album1.getArtist());
        assertNull(album1.getGenre());
        assertEquals(0, album1.getYear());
        assertTrue(album1.getSongs().isEmpty());
    }

    @Test
    void testSetGenre() {
        album1.setGenre("Rock");
        assertEquals("Rock", album1.getGenre());
    }

    @Test
    void testSetYear() {
        album1.setYear(2024);
        assertEquals(2024, album1.getYear());
    }

    @Test
    void testSetSongs() {
        List<Song> songList = Arrays.asList(song1, song2);
        album1.setSongs(songList);
        assertEquals(2, album1.getSongs().size());
        assertThrows(UnsupportedOperationException.class, () -> album1.getSongs().add(new Song("New Song", "Artist A", album1)));
    }

    @Test
    void testEquals() {
        assertEquals(album1, album3);
        assertNotEquals(album1, album2);
        assertNotEquals(album1, null);
        assertNotEquals(album1, "Some String");
    }

    @Test
    void testHashCode() {
        assertEquals(album1.hashCode(), album3.hashCode());
        assertNotEquals(album1.hashCode(), album2.hashCode());
    }

    @Test
    void testToString() {
        album1.setGenre("Rock");
        album1.setYear(2024);
        album1.setSongs(Arrays.asList(song1, song2));

        String expected = "Test Album by Artist A (2024, Rock)\nSongs:\n - Song One\n - Song Two";
        assertEquals(expected, album1.toString());
    }
}
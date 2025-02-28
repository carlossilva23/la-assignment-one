package tests;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PlaylistTest {
    private Playlist playlist;
    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("My Playlist");
        song1 = new Song("Song A", "Artist A", "Album A");
        song2 = new Song("Song B", "Artist B", "Album B");
    }

    @Test
    void testConstructor() {
        assertEquals("My Playlist", playlist.getName());
        assertTrue(playlist.getSongs().isEmpty());
    }

    @Test
    void testAddSong() {
        playlist.addSong(song1);
        assertEquals(1, playlist.getSongs().size());
        assertEquals(song1, playlist.getSongs().get(0));
    }

    @Test
    void testAddNullSong() {
        playlist.addSong(null);
        assertTrue(playlist.getSongs().isEmpty());
    }

    @Test
    void testRemoveSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.removeSong("Song A");
        List<Song> songs = playlist.getSongs();
        assertEquals(1, songs.size());
        assertEquals("Song B", songs.get(0).getName());
    }

    @Test
    void testRemoveNonExistentSong() {
        playlist.addSong(song1);
        playlist.removeSong("Nonexistent Song");
        assertEquals(1, playlist.getSongs().size());
    }

    @Test
    void testToString() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        String expected = "My Playlist:\n  " + song1.toString() + "\n  " + song2.toString();
        assertEquals(expected, playlist.toString());
    }
}

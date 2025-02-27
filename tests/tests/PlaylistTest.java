package tests;

import model.Playlist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    @Test
    void testGetName() {
        Playlist playlist = new Playlist("Test Playlist");
        assertEquals("Test Playlist", playlist.getName(), "Playlist name should be 'Test Playlist'");
    }

    @Test
    void testAddSong() {
        Playlist playlist = new Playlist("Test Playlist");
        Song song = new Song("Test Song", 180);
        playlist.addSong(song);
        assertEquals(1, playlist.getSongs().size(), "Playlist should have one song");
        assertEquals("Test Song", playlist.getSongs().get(0).getName(), "Song name should be 'Test Song'");
    }

    @Test
    void testRemoveSong() {
        Playlist playlist = new Playlist("Test Playlist");
        Song song = new Song("Test Song", 180);
        playlist.addSong(song);
        playlist.removeSong(song);
        assertEquals(0, playlist.getSongs().size(), "Playlist should have no songs");
    }

    @Test
    void testPlaylistConstructor() {
        Playlist playlist = new Playlist("Test Playlist");
        assertNotNull(playlist, "Playlist should be initialized");
        assertEquals("Test Playlist", playlist.getName(), "Playlist name should be 'Test Playlist'");
    }
}


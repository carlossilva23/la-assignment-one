package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Playlist;
import model.Song;

import java.util.List;

class PlaylistTest {
    private Playlist playlist;
    private Song song1;
    private Song song2;
    private Album album;

    @BeforeEach
    void setUp() {
        album = new Album("Test Album", "Artist A");
        song1 = new Song("Song One", "Artist A", album);
        song2 = new Song("Song Two", "Artist A", album);
        playlist = new Playlist("My Playlist");
    }

    @Test
    void testGetName() {
        assertEquals("My Playlist", playlist.getName());
    }

    @Test
    void testAddSong() {
        playlist.addSong(song1);
        assertEquals(1, playlist.getSongs().size());
        assertTrue(playlist.getSongs().contains(song1));
    }

    @Test
    void testAddDuplicateSong() {
        playlist.addSong(song1);
        playlist.addSong(song1);
        assertEquals(1, playlist.getSongs().size());
    }

    @Test
    void testRemoveSong() {
        playlist.addSong(song1);
        playlist.removeSong(song1);
        assertFalse(playlist.getSongs().contains(song1));
        assertEquals(0, playlist.getSongs().size());
    }

    @Test
    void testGetSongs() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        List<Song> songs = playlist.getSongs();
        assertEquals(2, songs.size());
        assertTrue(songs.contains(song1));
        assertTrue(songs.contains(song2));
    }

    @Test
    void testEquals() {
        Playlist anotherPlaylist = new Playlist("My Playlist");
        assertEquals(playlist, anotherPlaylist);
        assertNotEquals(playlist, new Playlist("Different Playlist"));
    }

    @Test
    void testHashCode() {
        Playlist anotherPlaylist = new Playlist("My Playlist");
        assertEquals(playlist.hashCode(), anotherPlaylist.hashCode());
    }

    @Test
    void testToString() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        String expected = "My Playlist:\n - " + song1.toString() + "/n - " + song2.toString() + "/n";
        assertEquals(expected, playlist.toString());
    }
}



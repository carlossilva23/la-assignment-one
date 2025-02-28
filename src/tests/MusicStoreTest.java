package tests;

import model.Album;
import model.Song;
import model.MusicStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class MusicStoreTest {
    private MusicStore musicStore;
    private Album album1;
    private Album album2;
    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp() {
        musicStore = new MusicStore();
        album1 = new Album("Album A", "Artist A");
        album2 = new Album("Album B", "Artist B");
        song1 = new Song("Song X", "Artist A", "Album A");
        song2 = new Song("Song Y", "Artist B", "Album B");
        
        album1.addSong(song1);
        album2.addSong(song2);
        
        musicStore.getLibrary().add(album1);
        musicStore.getLibrary().add(album2);
    }

    @Test
    void testGetAlbum() {
        assertEquals(album1, musicStore.getAlbum("Album A"));
        assertNull(musicStore.getAlbum("Nonexistent Album"));
    }

    @Test
    void testGetSong() {
        assertEquals(song1, musicStore.getSong("Song X"));
        assertNull(musicStore.getSong("Nonexistent Song"));
    }

    @Test
    void testSearchSongsByTitle() {
        ArrayList<Song> results = musicStore.searchSongByTitle("Song X");
        assertEquals(1, results.size());
        assertEquals(song1, results.get(0));
    }

    @Test
    void testSearchSongsByArtist() {
        ArrayList<Song> results = musicStore.searchSongsByArtist("Artist A");
        assertEquals(1, results.size());
        assertEquals(song1, results.get(0));
    }

    @Test
    void testSearchAlbumByTitle() {
        assertEquals(album1, musicStore.searchAlbumByTitle("Album A"));
    }

    @Test
    void testSearchAlbumsByArtist() {
        ArrayList<Album> results = musicStore.searchAlbumsByArtist("Artist A");
        assertEquals(1, results.size());
        assertEquals(album1, results.get(0));
    }
}


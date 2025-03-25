package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.LibraryModel;
import model.MusicStore;
import model.Song;

import java.util.List;

class MusicStoreTest {
    private MusicStore musicStore;
    
    @BeforeEach
    void setUp() {
        musicStore = new MusicStore();
        new LibraryModel();
    }

    // Test if MusicStore initializes correctly and loads albums from albums.txt
    @Test
    void testMusicStoreInitialization() {
        assertNotNull(musicStore);
        assertFalse(musicStore.listAllContent().isEmpty(), "Store should contain albums after initialization");
    }

    // Test if albums are loaded correctly from the albums.txt file
    @Test
    void testLoadAlbumsFromFile() {
        List<Album> albums = musicStore.listAllContent();
        assertTrue(albums.stream().anyMatch(album -> album.getName().equalsIgnoreCase("19")), "Album '19' by Adele should be loaded");
        assertTrue(albums.stream().anyMatch(album -> album.getArtist().equalsIgnoreCase("Adele")), "Artist Adele should be found in the store");
    }

    // Test searching for a song by its title
    @Test
    void testSearchSongByTitle() {
        List<Song> songs = musicStore.searchSongByTitle("Daydreamer");
        assertFalse(songs.isEmpty(), "Song 'Daydreamer' should be found in the store");
        assertEquals("Daydreamer", songs.get(0).getName(), "Song name should match");
    }

    // Test searching for a song by its artist
    @Test
    void testSearchSongsByArtist() {
        List<Song> songs = musicStore.searchSongsByArtist("Adele");
        assertFalse(songs.isEmpty(), "Songs by Adele should be found");
        assertEquals("Adele", songs.get(0).getArtist(), "Artist name should match");
    }

    // Test searching for an album by its title
    @Test
    void testSearchAlbumByTitle() {
        List<Album> albums = musicStore.searchAlbumByTitle("19");
        assertFalse(albums.isEmpty(), "Album '19' should be found");
        assertEquals("19", albums.get(0).getName(), "Album title should match");
    }

    // Test searching for albums by artist
    @Test
    void testSearchAlbumsByArtist() {
        List<Album> albums = musicStore.searchAlbumsByArtist("Adele");
        assertFalse(albums.isEmpty(), "Albums by Adele should be found");
        assertEquals("Adele", albums.get(0).getArtist(), "Artist name should match");
    }
}
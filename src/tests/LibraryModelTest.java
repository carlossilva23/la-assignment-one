package tests;

import model.Song;
import model.LibraryModel;
import model.Album;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryModelTest {
    private LibraryModel library;
    private Song song1;
    private Song song2;
    private Album album;
    
    @BeforeEach
    void setUp() {
        library = new LibraryModel();
        song1 = new Song("Song1", "Artist1", "Album1");
        song2 = new Song("Song2", "Artist2", "Album2");
        album = new Album("Album1", "Artist1");
        album.addSong(song1);
    }
    
    @Test
    void testAddSong() {
        library.addSong(song1);
        assertTrue(library.getAllSongs().contains(song1));
    }
    
    @Test
    void testFavoriteSong() {
        library.favoriteSong(song1);
        assertTrue(song1.getFavorite());
    }
    
    @Test
    void testRateSong() {
        library.rateSong(song1, 5);
        assertTrue(song1.getFavorite());
    }
    
    @Test
    void testAddAlbum() {
        library.addAlbum("Album1");
        assertTrue(library.getAllAlbums().contains(album));
    }
    
    @Test
    void testCreatePlaylist() {
        library.createPlaylist("My Playlist");
        assertEquals(1, library.getAllPlaylists().size());
    }
    
    @Test
    void testAddSongToPlaylist() {
        library.createPlaylist("My Playlist");
        library.addSongToPlaylist("My Playlist", song1);
        assertEquals(1, library.getAllPlaylists().get(0).getSongs().size());
    }
    
    @Test
    void testRemoveSongFromPlaylist() {
        library.createPlaylist("My Playlist");
        library.addSongToPlaylist("My Playlist", song1);
        library.removeSongFromPlaylist("My Playlist", "Song1");
        assertTrue(library.getAllPlaylists().get(0).getSongs().isEmpty());
    }
    
    @Test
    void testSearchLibrarySongByTitle() {
        library.addSong(song1);
        assertEquals(1, library.searchLibrarySongByTitle("Song1").size());
    }
    
    @Test
    void testSearchLibrarySongsByArtist() {
        library.addSong(song1);
        assertEquals(1, library.searchLibrarySongsByArtist("Artist1").size());
    }
    
    @Test
    void testSearchLibraryAlbumByTitle() {
        library.addAlbum("Album1");
        assertNotNull(library.searchLibraryAlbumByTitle("Album1"));
    }
    
    @Test
    void testSearchLibraryAlbumsByArtist() {
        library.addAlbum("Album1");
        assertEquals(1, library.searchLibraryAlbumsByArtist("Artist1").size());
    }
    
    @Test
    void testGetAllArtists() {
        library.addSong(song1);
        assertTrue(library.getAllArtists().contains("Artist1"));
    }
    
    @Test
    void testGetFavoriteSongs() {
        library.favoriteSong(song1);
        assertTrue(library.getFavoriteSongs().contains(song1));
    }
    
    @Test
    void testSearchStoreSongByTitle() {
        assertNotNull(library.searchStoreSongByTitle("Song1"));
    }
    
    @Test
    void testSearchStoreSongsByArtist() {
        assertNotNull(library.searchStoreSongsByArtist("Artist1"));
    }
    
    @Test
    void testSearchStoreAlbumByTitle() {
        assertNotNull(library.searchStoreAlbumByTitle("Album1"));
    }
    
    @Test
    void testSearchStoreAlbumsByArtist() {
        assertNotNull(library.searchStoreAlbumsByArtist("Artist1"));
    }
}


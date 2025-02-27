package tests;

import model.Album;
import model.LibraryModel;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class LibraryModelTest {

    @Test
    void testAddSong() {
        LibraryModel library = new LibraryModel();
        library.addSong("Song 1");
        Song song = library.getSongByTitle("Song 1");
        assertNotNull(song, "Song should be added to the library");
    }

    @Test
    void testAddAlbum() {
        LibraryModel library = new LibraryModel();
        library.addAlbum("Test Album");
        Album album = library.getAlbumByTitle("Test Album");
        assertNotNull(album, "Album should be added to the library");
    }

    @Test
    void testCreatePlaylist() {
        LibraryModel library = new LibraryModel();
        library.createPlaylist("My Playlist");
        library.printPlaylists();  // This will print the playlists
    }

    @Test
    void testAddSongToPlaylist() {
        LibraryModel library = new LibraryModel();
        library.createPlaylist("My Playlist");
        library.addSong("Song 1");
        library.addSongToPlaylist("My Playlist", "Song 1");
        Playlist playlist = library.getUserPlaylist("My Playlist");
        assertTrue(playlist.getSongs().contains(new Song("Song 1")), "Song should be added to the playlist");
    }

    @Test
    void testRemoveSongFromPlaylist() {
        LibraryModel library = new LibraryModel();
        library.createPlaylist("My Playlist");
        library.addSong("Song 1");
        library.addSongToPlaylist("My Playlist", "Song 1");
        library.removeSongFromPlaylist("My Playlist", "Song 1");
        Playlist playlist = library.getUserPlaylist("My Playlist");
        assertFalse(playlist.getSongs().contains(new Song("Song 1")), "Song should be removed from the playlist");
    }

    @Test
    void testSearchSongByTitle() {
        LibraryModel library = new LibraryModel();
        library.addSong("Song 1");
        Song song = library.getSongByTitle("Song 1");
        assertNotNull(song, "Song should be found by title");
    }

    @Test
    void testPrintSongsByArtist() {
        LibraryModel library = new LibraryModel();
        library.addSong("Song 1");
        library.addAlbum("Test Album");
        library.printSongsByArtist("Test Artist");  // Prints all songs by the artist
    }

    @Test
    void testPrintAlbumsByArtist() {
        LibraryModel library = new LibraryModel();
        library.addAlbum("Test Album");
        library.printAlbumsByArtist("Test Artist");  // Prints all albums by the artist
    }

    @Test
    void testPrintPlaylists() {
        LibraryModel library = new LibraryModel();
        library.createPlaylist("My Playlist");
        library.printPlaylists();  // Prints the playlist names
    }

    @Test
    void testPrintAlbumByTitle() {
        LibraryModel library = new LibraryModel();
        library.addAlbum("Test Album");
        library.printAlbumByTitle("Test Album");  // Prints album details by title
    }

    @Test
    void testSetFavorite() {
        LibraryModel library = new LibraryModel();
        library.addSong("Song 1");
        library.setFavorite("Song 1");
        Song song = library.getSongByTitle("Song 1");
        assertTrue(song.getFavorite(), "Song should be marked as favorite");
    }

    @Test
    void testRateSong() {
        LibraryModel library = new LibraryModel();
        library.addSong("Song 1");
        library.rate("Song 1", 5);
        Song song = library.getSongByTitle("Song 1");
        assertEquals(5, song.getRating(), "Song should have the rating of 5");
    }

    @Test
    void testSearchStoreSongByTitle() {
        LibraryModel library = new LibraryModel();
        library.searchStoreSongByTitle("Song 1");  // Should print song details or not found message
    }

    @Test
    void testSearchStoreAlbumByTitle() {
        LibraryModel library = new LibraryModel();
        library.searchStoreAlbumByTitle("Test Album");  // Should print album details or not found message
    }

    @Test
    void testSearchStoreAlbumsByArtist() {
        LibraryModel library = new LibraryModel();
        library.searchStoreAlbumsByArtist("Test Artist");  // Should print albums by artist or not found message
    }
}

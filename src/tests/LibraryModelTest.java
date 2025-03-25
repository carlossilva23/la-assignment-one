package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.LibraryModel;
import model.Playlist;
import model.Rating;
import model.Song;
import model.UserAlbum;

import java.util.*;

public class LibraryModelTest {
    private LibraryModel library;
    private Song song1, song2, song3, song4;
    private Album album1, album2, album3;
    private Playlist playlist;

    @BeforeEach
    public void setUp() {
        library = new LibraryModel();

        // Creating songs
        song1 = new Song("Song1", "Artist1", album1);
        song2 = new Song("Song2", "Artist2", album2);
        song3 = new Song("Song3", "Artist1", album1);
        song4 = new Song("Song4", "Artist3", album3);

        // Creating albums
        album1 = new Album("Album1", "Artist1");
        album2 = new Album("Album2", "Artist2");
        album3 = new Album("Album3", "Artist3");

        // Creating a playlist
        playlist = new Playlist("My Playlist");
        playlist.addSong(song1);
    }

    @Test
    public void testAddSong() {
        library.addSong(song1);
        library.addSong(song2);

        assertTrue(library.searchSongsByTitle("Song1").contains(song1));
        assertTrue(library.searchSongsByTitle("Song2").contains(song2));
    }

    @Test
    public void testRemoveSong() {
        library.addSong(song1);
        library.addSong(song2);
        library.removeSong(song1);

        assertFalse(library.searchSongsByTitle("Song1").contains(song1));
        assertTrue(library.searchSongsByTitle("Song2").contains(song2));
    }

    @Test
    public void testSearchSongsByTitle() {
        library.addSong(song1);
        library.addSong(song2);

        ArrayList<Song> songs = library.searchSongsByTitle("Song1");

        assertEquals(1, songs.size());
        assertTrue(songs.contains(song1));
    }

    @Test
    public void testSearchSongsByArtist() {
        library.addSong(song1);
        library.addSong(song3);

        ArrayList<Song> songs = library.searchSongByArtist("Artist1");

        assertEquals(2, songs.size());
        assertTrue(songs.contains(song1));
        assertTrue(songs.contains(song3));
    }

    @Test
    public void testSearchAlbumByTitle() {
        library.addAlbum(album1);
        library.addAlbum(album2);

        UserAlbum foundAlbum = library.searchAlbumByTitle("Album1");

        assertNotNull(foundAlbum);
        assertTrue(foundAlbum.getSongs().contains(song1));
    }

    @Test
    public void testRemoveAlbum() {
        library.addAlbum(album1);
        library.addAlbum(album2);

        library.removeAlbum(album1);

        assertNull(library.searchAlbumByTitle("Album1"));
        assertNotNull(library.searchAlbumByTitle("Album2"));
    }

    @Test
    public void testListAllContent() {
        library.addAlbum(album1);
        library.addAlbum(album2);

        ArrayList<UserAlbum> allContent = library.listAllContent();

        assertTrue(allContent.size() > 0);
        assertTrue(allContent.contains(library.searchAlbumByTitle("Album1")));
        assertTrue(allContent.contains(library.searchAlbumByTitle("Album2")));
    }

    @Test
    public void testListAllArtists() {
        library.addSong(song1);
        library.addSong(song3);

        ArrayList<String> allArtists = library.listAllArtists();

        assertTrue(allArtists.contains("Artist1"));
        assertFalse(allArtists.contains("Artist2"));
    }

    @Test
    public void testListSortedSongsByTitle() {
        library.addSong(song1);
        library.addSong(song2);

        ArrayList<Song> sortedSongs = library.listSortedSongsByTitle();

        assertEquals(song1, sortedSongs.get(0));
        assertEquals(song2, sortedSongs.get(1));
    }

    @Test
    public void testListSortedSongsByArtist() {
        library.addSong(song1);
        library.addSong(song3);

        ArrayList<Song> sortedSongs = library.listSortedSongsByArtist();

        assertEquals(song1, sortedSongs.get(0));
        assertEquals(song3, sortedSongs.get(1));
    }

    @Test
    public void testMarkSongFavorite() {
        library.addSong(song1);

        library.markSongFavorite(song1);

        assertTrue(library.getFavorites().contains(song1));
        assertTrue(song1.getFavorite());
    }

    @Test
    public void testRateSong() {
        library.addSong(song2);

        library.rateSong(song2, Rating.five);

        assertEquals(Rating.five, song2.getRating());
    }

    @Test
    public void testPlaySong() {
        library.addSong(song1);

        library.playSong(song1);

        assertTrue(library.getRecentlyPlayed().contains(song1));
        assertEquals(1, library.getFrequentPlayed().size());
    }

    @Test
    public void testCreatePlaylist() {
        library.createPlaylist("My Playlist");

        assertNotNull(library.searchPlaylistsByName("My Playlist"));
    }

    @Test
    public void testAddSongToPlaylist() {
        library.addSong(song1);
        library.addSongToPlaylist(playlist, song1);

        assertTrue(playlist.getSongs().contains(song1));
    }

    @Test
    public void testRemoveSongFromPlaylist() {
        library.addSong(song1);
        library.addSongToPlaylist(playlist, song1);
        library.removeSongFromPlaylist(playlist, song1);

        assertFalse(playlist.getSongs().contains(song1));
    }

    @Test
    public void testShuffleLibrary() {
        library.addSong(song1);
        library.addSong(song2);

        List<Song> shuffledLibrary = library.shuffleLibrary();

        assertEquals(2, shuffledLibrary.size());
        assertTrue(shuffledLibrary.contains(song1));
        assertTrue(shuffledLibrary.contains(song2));
    }

    @Test
    public void testShufflePlaylist() {
        library.addSong(song1);
        library.addSong(song2);
        library.addSongToPlaylist(playlist, song2);

        List<Song> shuffledPlaylist = library.shufflePlaylist(playlist);

        assertEquals(2, shuffledPlaylist.size());
        assertTrue(shuffledPlaylist.contains(song1));
        assertTrue(shuffledPlaylist.contains(song2));
    }

    @Test
    public void testCheckAndCreateGenrePlaylist() {
        library.addSong(song1);
        library.addSong(song4);

        library.checkAndCreateGenrePlaylist();

        assertNotNull(library.getGenrePlaylists());
        assertTrue(library.getGenrePlaylists().stream().anyMatch(playlist -> playlist.getName().equals("PopPlaylist:")));
    }

    @Test
    public void testCreateTopRated() {
        library.addSong(song1);
        library.addSong(song3);

        library.createTopRated();

        assertTrue(library.getTopRated().contains(song1));
        assertTrue(library.getTopRated().contains(song3));
    }
}

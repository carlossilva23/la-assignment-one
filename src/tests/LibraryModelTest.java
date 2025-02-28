package tests;

import model.Song;
import model.LibraryModel;
import model.Album;
import model.Playlist;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.*;
import java.util.ArrayList;

public class LibraryModelTest {

    private LibraryModel libraryModel;
    private MusicStore musicStore;
    
    @Before
    public void setUp() {
        libraryModel = new LibraryModel();
        musicStore = new MusicStore();
    }

    @Test
    public void testAlbumLoadingFromStore() {
        Album album = musicStore.getAlbum("21");
        assertNotNull(album);
        assertEquals("Adele", album.getArtist());
        assertTrue(album.getSongs().size() > 0); 
    }


    @Test
    public void testGetSongsByTitle() {
        ArrayList<Song> songs = musicStore.getSongsByTitle("Rolling in the Deep");
        assertFalse(songs.isEmpty());
        assertEquals("Rolling in the Deep", songs.get(0).getName());
    }

 
    @Test
    public void testAddAlbumToLibrary() {
        libraryModel.addAlbum("21");
        assertNotNull(libraryModel.getAllAlbums());
        assertTrue(libraryModel.getAllAlbums().size() > 0);
    }

    
    @Test
    public void testSearchLibrarySongsByTitle() {
        libraryModel.addAlbum("21"); // Add an album first
        ArrayList<Song> songs = libraryModel.getLibrarySongsByTitle("Rolling in the Deep");
        assertFalse(songs.isEmpty());
        assertEquals("Rolling in the Deep", songs.get(0).getName());
    }

   
    @Test
    public void testAddSongToPlaylist() {
        libraryModel.createPlaylist("My Playlist");
        Playlist playlist = libraryModel.getAllPlaylists().get(0);
        assertNotNull(playlist);

        libraryModel.addSongToPlaylist("My Playlist", song);
        assertTrue(playlist.getSongs().contains(song));

        // Test when song is null
        libraryModel.addSongToPlaylist("My Playlist", null);
        assertEquals(1, playlist.getSongs().size()); // Ensure no null song is added
    }

   
    @Test
    public void testAddSongToSingles() {
        libraryModel.addSong(song);
        assertTrue(libraryModel.getAllSongs().contains(song));
    }

    
    @Test
    public void testSearchSongsByArtist() {
        libraryModel.addAlbum("21");
        ArrayList<Song> songs = libraryModel.searchLibrarySongsByArtist("Adele");
        assertFalse(songs.isEmpty());
        assertEquals("Adele", songs.get(0).getArtist());

        
        songs = libraryModel.searchLibrarySongsByArtist("Non-Existing Artist");
        assertTrue(songs.isEmpty());  
    }

   
    @Test
    public void testFavoriteSong() {
        libraryModel.favoriteSong(song);
        assertTrue(song.getFavorite()); 

       
        libraryModel.favoriteSong(null);
        assertFalse(song.getFavorite()); 
    }

   
    @Test
    public void testSearchAlbumsByArtist() {
        libraryModel.addAlbum("21");
        ArrayList<Album> albums = libraryModel.searchLibraryAlbumsByArtist("Adele");
        assertFalse(albums.isEmpty());
        assertEquals("Adele", albums.get(0).getArtist());

        
        albums = libraryModel.searchLibraryAlbumsByArtist("Non-Existing Artist");
        assertTrue(albums.isEmpty());  // Expect an empty result
    }

   
    @Test
    public void testGetFavoriteSongs() {
        libraryModel.favoriteSong(song); // Mark song as favorite
        ArrayList<Song> favorites = libraryModel.getFavoriteSongs();
        assertTrue(favorites.contains(song));

        
        favorites = libraryModel.getFavoriteSongs();
        assertTrue(favorites.isEmpty()); // No favorites should be there yet
    }

    
    @Test
    public void testSearchLibraryAlbumByTitle() {
        libraryModel.addAlbum("21");
        Album retrievedAlbum = libraryModel.searchLibraryAlbumByTitle("21");
        assertNotNull(retrievedAlbum);
        assertEquals("Adele", retrievedAlbum.getArtist());

       
        retrievedAlbum = libraryModel.searchLibraryAlbumByTitle("Non-Existing Album");
        assertNull(retrievedAlbum); // Expect null since the album doesn't exist
    }

    
    @Test
    public void testEmptyLibraryBehavior() {
        ArrayList<Song> songs = libraryModel.getLibrarySongsByTitle("Non-Existing Song");
        assertTrue(songs.isEmpty()); 

        ArrayList<Album> albums = libraryModel.searchLibraryAlbumsByArtist("Non-Existing Artist");
        assertTrue(albums.isEmpty());
    }

    
    @Test
    public void testRemoveSongFromPlaylist() {
        libraryModel.createPlaylist("My Playlist");
        Playlist playlist = libraryModel.getAllPlaylists().get(0);
        libraryModel.addSongToPlaylist("My Playlist", song);
        assertTrue(playlist.getSongs().contains(song));

        libraryModel.removeSongFromPlaylist("My Playlist", "Rolling in the Deep");
        assertFalse(playlist.getSongs().contains(song)); // Song should be removed
    }

   
    @Test
    public void testGetStoreSongsByTitle() {
        ArrayList<Song> songs = musicStore.searchSongByTitle("Nonexistent Song");
        assertTrue(songs.isEmpty());

        songs = musicStore.searchSongByTitle("Rolling in the Deep");
        assertFalse(songs.isEmpty());
        assertEquals("Rolling in the Deep", songs.get(0).getName());
    }

  
    @Test
    public void testGetLibrarySongsByTitle() {
        ArrayList<Song> songs = musicStore.getSongsByTitle("Nonexistent Song");
        assertTrue(songs.isEmpty());

        songs = musicStore.getSongsByTitle("Rolling in the Deep");
        assertFalse(songs.isEmpty());
        assertEquals("Rolling in the Deep", songs.get(0).getName(), "The song returned should match the title");
    }

    @Test
    public void testRateSong() {
        Song song = musicStore.getSong("Rolling in the Deep");
        assertNotNull(song);

    }

    @Test
    public void testGetAllSongs() {
        ArrayList<Song> allSongs = libraryModel.getAllSongs();
        assertNotNull(allSongs);
        assertTrue(allSongs.size() > 0);
    }
}



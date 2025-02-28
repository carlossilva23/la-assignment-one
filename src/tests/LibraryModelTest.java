package tests;

import model.Song;
import model.LibraryModel;

import model.Playlist;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class LibraryModelTest {


    @Test
    public void testGetStoreBySongsByTitle() {
    	LibraryModel libraryModel = new LibraryModel();
    	String example = "Rolling in the Deep";
    	ArrayList <Song> result = libraryModel.getStoreSongsByTitle(example);
    	
    	assertNotNull(result);
    }
    
    @Test 
    public void testGetLibrarySongsByTitle() {
    	LibraryModel libraryModel = new LibraryModel();
    	String example = "Rolling in the deep";    	
    	ArrayList <Song> result = libraryModel.getStoreSongsByTitle(example);
    	for (Song s : result) {
    		libraryModel.addSong(s);
    		libraryModel.createPlaylist("examplePlaylist");
    		libraryModel.addSongToPlaylist("examplePlaylist", s);
    	}
    	libraryModel.addAlbum("21");
    	
    	assertNotNull(libraryModel.getLibrarySongsByTitle(example));
    	assertTrue(libraryModel.getLibrarySongsByTitle(example).size() > 0);
    }
    
    @Test 
    public void testGetAndRateFavoriteSong() {
    	LibraryModel libraryModel = new LibraryModel();
    	String example = "Rolling in the deep";    	
    	ArrayList <Song> result = libraryModel.getStoreSongsByTitle(example);
    	for (Song s : result) {
    		libraryModel.addSong(s);
    		libraryModel.rateSong(s, 4);
    	}
    	Song result0 = result.get(0);
    	libraryModel.favoriteSong(result0);
    	assertTrue(libraryModel.getFavoriteSongs().contains(result0));
    }
    
    @Test 
    public void testRemovePlaylist() {
    	LibraryModel libraryModel = new LibraryModel();
    	String example = "Rolling in the deep";    
    	ArrayList <Song> result = libraryModel.getStoreSongsByTitle(example);
    	for (Song s : result) {
    		libraryModel.addSong(s);
    	}
    	String example1 = "Lullaby";    
    	ArrayList <Song> result1 = libraryModel.getStoreSongsByTitle(example1);
    	for (Song s : result1) {
    		libraryModel.addSong(s);
    	}
    	Song result0 = result.get(0);
    	Song anotherSong = result1.get(0);
    	libraryModel.createPlaylist("examplePlaylist");
		libraryModel.addSongToPlaylist("examplePlaylist", result0);
		libraryModel.addSongToPlaylist("examplePlaylist", anotherSong);
		libraryModel.removeSongFromPlaylist("examplePlaylist", "rolling in the deep");
		Playlist playlist = libraryModel.getUserPlaylist("examplePlaylist");
		assertTrue(playlist.getSongs().get(0) != result0);
    }
    
    @Test
    public void testGetAll() {
    	LibraryModel libraryModel = new LibraryModel();
    	libraryModel.addAlbum("21");
    	libraryModel.addAlbum("19");
    	libraryModel.addAlbum("cuando los angeles lloran");
    	libraryModel.createPlaylist("examplePlaylist");
    	Playlist playlist = libraryModel.getUserPlaylist("examplePlaylist");
    	String example = "my heart is full";    
    	ArrayList <Song> result = libraryModel.getStoreSongsByTitle(example);
    	for (Song s : result) {
    		libraryModel.addSong(s);
    		playlist.addSong(s);
    	}
    	
    	assertNotNull(libraryModel.getAllSongs());
    	assertTrue(libraryModel.getAllArtists().contains("Adele"));
    	assertTrue(libraryModel.getAllPlaylists().contains(playlist));
    	assertNotNull(libraryModel.getAllAlbums());
    }
    
    @Test 
    public void testGetFavoriteSongs() {
    	LibraryModel libraryModel = new LibraryModel();
    	libraryModel.addAlbum("21");
    	libraryModel.addAlbum("19");
    	libraryModel.addAlbum("cuando los angeles lloran");
    	libraryModel.createPlaylist("examplePlaylist");
    	Playlist playlist = libraryModel.getUserPlaylist("examplePlaylist");
    	ArrayList <Song> favorites = libraryModel.searchLibrarySongByTitle("rolling in the deep");
    	libraryModel.favoriteSong(favorites.get(0));
    	ArrayList <Song> favorites1 = libraryModel.searchLibrarySongByTitle("dejame entrar");
    	playlist.addSong(favorites1.get(0));
    	libraryModel.favoriteSong(playlist.getSongs().get(0));
    	
    	assertNotNull(libraryModel.getFavoriteSongs().get(0));
    }
    
    @Test
    public void searchLibrary() {
    	LibraryModel libraryModel = new LibraryModel();
    	libraryModel.addAlbum("21");
    	libraryModel.addAlbum("19");
    	libraryModel.addAlbum("cuando los angeles lloran");
    	libraryModel.createPlaylist("examplePlaylist");
    	Playlist playlist = libraryModel.getUserPlaylist("examplePlaylist");
    	ArrayList <Song> search = libraryModel.searchLibrarySongByTitle("dejame entrar");
    	playlist.addSong(search.get(0));
    	ArrayList <Song> search1 = libraryModel.getStoreSongsByTitle("uh oh");
    	libraryModel.addSong(search1.get(0));
    	
    	
    	
    	assertNotNull(libraryModel.searchLibraryAlbumsByArtist("adele"));
    	assertNotNull(libraryModel.searchLibraryAlbumsByArtist("mana"));
    	assertNotNull(libraryModel.searchLibraryAlbumsByArtist("norah jones"));
    	assertNotNull(libraryModel.searchLibrarySongsByArtist("adele"));
    	assertNotNull(libraryModel.searchLibrarySongsByArtist("mana"));
    	assertNotNull(libraryModel.searchLibrarySongsByArtist("norah jones"));
    	assertNotNull(libraryModel.searchLibraryAlbumByTitle("21"));
    }
    
   
}
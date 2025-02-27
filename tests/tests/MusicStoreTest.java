package tests;

import model.Album;
import model.MusicStore;
import model.Song;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class MusicStoreTest {

    @Test
    void testMusicStoreInitialization() {
        try {
            File tempDir = Files.createTempDirectory("albums").toFile();
            File albumsFile = new File(tempDir, "albums.txt");
            Files.createFile(albumsFile.toPath());
            
            String testAlbumData = "Test Album,Test Artist\n";
            Files.write(albumsFile.toPath(), testAlbumData.getBytes());

            File albumDetailFile = new File(tempDir, "Test Album_Test Artist.txt");
            Files.createFile(albumDetailFile.toPath());

            String albumDetailsData = "songName,artistName,genre,year\nSong 1,Test Artist,Rock,2023\n";
            Files.write(albumDetailFile.toPath(), albumDetailsData.getBytes());

            MusicStore store = new MusicStore();

            assertNotNull(store.getLibrary(), "Library should not be null");
            assertEquals(1, store.getLibrary().size(), "Library should contain one album");

            Album album = store.getAlbum("Test Album");
            assertNotNull(album, "Album should be found by name");
            assertEquals("Test Artist", album.getArtist(), "Artist should be 'Test Artist'");
            assertEquals("Rock", album.getGenre(), "Genre should be 'Rock'");
            assertEquals(2023, album.getYear(), "Year should be 2023");
            assertEquals(1, album.getSongs().size(), "Album should have one song");

            Song song = store.getSong("Song 1");
            assertNotNull(song, "Song should be found by name");
            assertEquals("Song 1", song.getName(), "Song name should be 'Song 1'");
            assertEquals("Test Artist", song.getArtist(), "Song artist should be 'Test Artist'");

            albumDetailFile.delete();
            albumsFile.delete();
            tempDir.delete();

        } catch (IOException e) {
            e.printStackTrace();
            fail("Test setup failed");
        }
    }
    
    @Test
    void testGetAlbum() {
        MusicStore store = new MusicStore();
        Album album = store.getAlbum("Nonexistent Album");
        assertNull(album, "Should return null for a non-existing album");
        
        album = store.getAlbum("Test Album");
        assertNotNull(album, "Should return an album if it exists in the library");
    }

    @Test
    void testGetSong() {
        MusicStore store = new MusicStore();
        Song song = store.getSong("Nonexistent Song");
        assertNull(song, "Should return null for a non-existing song");
        
        song = store.getSong("Song 1");
        assertNotNull(song, "Should return a song if it exists in the library");
    }
}

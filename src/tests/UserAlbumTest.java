package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Song;
import model.UserAlbum;

import java.util.List;

class UserAlbumTest {

    private Album album;
    private Song song1, song2, song3;
    private UserAlbum userAlbum;

    @BeforeEach
    void setUp() {
        // Create a mock album
        album = new Album("Test Album", "Test Artist");
        album.setYear(2022);
        album.setGenre("Rock");
        song1 = new Song("Song 1", "Test Artist", album);
        song2 = new Song("Song 2", "Test Artist", album);
        song3 = new Song("Song 3", "Test Artist", album);
        
        // Add songs to the album
        album.setSongs(List.of(song1, song2, song3));

        // Create a UserAlbum instance
        userAlbum = new UserAlbum(album);
    }

    // Test addSong method
    @Test
    void testAddSong() {
        // Add a song to the user album
        userAlbum.addSong(song1);
        assertTrue(userAlbum.getSongs().contains(song1), "Song 1 should be in the user album");

        // Add another song
        userAlbum.addSong(song2);
        assertTrue(userAlbum.getSongs().contains(song2), "Song 2 should be in the user album");

        // Try to add a song again (it should not be added again)
        userAlbum.addSong(song1);
        assertEquals(2, userAlbum.getSongs().size(), "Duplicate songs should not be added to the user album");
    }

    // Test getSongs method
    @Test
    void testGetSongs() {
        userAlbum.addSong(song1);
        userAlbum.addSong(song2);
        List<Song> songs = userAlbum.getSongs();
        assertEquals(2, songs.size(), "The user album should contain 2 songs");
        assertTrue(songs.contains(song1), "Song 1 should be in the list");
        assertTrue(songs.contains(song2), "Song 2 should be in the list");
        assertFalse(songs.contains(song3), "Song 3 should not be in the list since it's not added");
    }

    // Test toString method
    @Test
    void testToString() {
        userAlbum.addSong(song1);
        userAlbum.addSong(song2);

        String expected = "Test Album by Test Artist (2022, Rock)\nSongs:\n - Song 1\n - Song 2";
        assertEquals(expected, userAlbum.toString(), "toString should return the correct string representation of the user album");
    }

    // Test adding a song to an already added song
    @Test
    void testAddSongDuplicate() {
        userAlbum.addSong(song1);
        userAlbum.addSong(song1);  // Trying to add the same song again
        assertEquals(1, userAlbum.getSongs().size(), "Duplicate song should not be added");
    }

    // Test that the user album initially has no songs added
    @Test
    void testEmptyUserAlbum() {
        List<Song> songs = userAlbum.getSongs();
        assertTrue(songs.isEmpty(), "A newly created UserAlbum should have no songs");
    }
}

package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Rating;
import model.Song;

import java.util.Comparator;

class SongTest {
    private Song song1;
    private Song song2;
    private Song song3;
    private Album album;
    private Album albumTwo;

    @BeforeEach
    void setUp() {
        album = new Album("Test Album", "The Rock");
        albumTwo = new Album("Test Album 2", "Not The Rock");
        album.setGenre("Rock");
        song1 = new Song("Song A", "The Rock", album);
        song2 = new Song("Song B", "The Rock", album);
        song3 = new Song("Song A", "The Rock", albumTwo);
    }

    @Test
    void testGetters() {
        assertEquals("Song A", song1.getName());
        assertEquals("The Rock", song1.getArtist());
        assertEquals(album, song1.getAlbum());
        assertEquals(Rating.noRating, song1.getRating());
        assertEquals("Rock", song1.getGenre());
        assertEquals(0, song1.getPlayCount());
    }

    @Test
    void testSetRating() {
        song1.setRating(Rating.five);
        assertEquals(Rating.five, song1.getRating());
    }

    @Test
    void testSetFavorite() {
        song1.setFavorite(true);
        assertTrue(song1.getFavorite());
        song1.setFavorite(false);
        assertFalse(song1.getFavorite());
    }

    @Test
    void testIncrementPlayCount() {
        song1.incrementPlayCount();
        assertEquals(1, song1.getPlayCount());
    }

    @Test
    void testSortByTitle() {
        Comparator<Song> comparator = Song.sortByTitle;
        assertTrue(comparator.compare(song1, song2) < 0);
        assertEquals(0, comparator.compare(song1, song3));
    }

    @Test
    void testSortByArtist() {
        Comparator<Song> comparator = Song.sortByArtist;
        assertFalse(comparator.compare(song2, song1) < 0);
    }

    @Test
    void testSortByRating() {
        song1.setRating(Rating.three);
        song2.setRating(Rating.five);
        Comparator<Song> comparator = Song.sortByRating;
        assertTrue(comparator.compare(song1, song2) < 0);
    }

    @Test
    void testSortByPlayCount() {
        song1.incrementPlayCount();
        song2.incrementPlayCount();
        song2.incrementPlayCount();
        Comparator<Song> comparator = Song.sortByPlayCount;
        assertTrue(comparator.compare(song1, song2) < 0);
    }

    @Test
    void testEquals() {
        assertEquals(song1, song3);
        assertNotEquals(song1, song2);
        assertNotEquals(song1, null);
        assertNotEquals(song1, "Some String");
    }

    @Test
    void testHashCode() {
        assertEquals(song1.hashCode(), song3.hashCode());
        assertNotEquals(song1.hashCode(), song2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Song A by The Rock, Test Album", song1.toString());
    }
}

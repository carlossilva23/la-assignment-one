package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Rating;

class RatingTest {

    // Test the getValue method for all Rating enum constants
    @Test
    void testGetValue() {
        assertEquals(0, Rating.noRating.getValue(), "Value for noRating should be 0");
        assertEquals(1, Rating.one.getValue(), "Value for one should be 1");
        assertEquals(2, Rating.two.getValue(), "Value for two should be 2");
        assertEquals(3, Rating.three.getValue(), "Value for three should be 3");
        assertEquals(4, Rating.four.getValue(), "Value for four should be 4");
        assertEquals(5, Rating.five.getValue(), "Value for five should be 5");
    }

    // Test the fromInt method for valid values (1 to 5)
    @Test
    void testFromInt() {
        assertEquals(Rating.one, Rating.fromInt(1), "fromInt should return 'one' for 1");
        assertEquals(Rating.two, Rating.fromInt(2), "fromInt should return 'two' for 2");
        assertEquals(Rating.three, Rating.fromInt(3), "fromInt should return 'three' for 3");
        assertEquals(Rating.four, Rating.fromInt(4), "fromInt should return 'four' for 4");
        assertEquals(Rating.five, Rating.fromInt(5), "fromInt should return 'five' for 5");
    }

    // Test the fromInt method for invalid values (values outside the range 1-5)
    @Test
    void testFromIntInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Rating.fromInt(0), "fromInt should throw IllegalArgumentException for 0");
        assertThrows(IllegalArgumentException.class, () -> Rating.fromInt(6), "fromInt should throw IllegalArgumentException for 6");
        assertThrows(IllegalArgumentException.class, () -> Rating.fromInt(-1), "fromInt should throw IllegalArgumentException for -1");
    }
}

package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(15);
    }

    @Test
    public void hotelCreating() {
        assertEquals(15, hotel.getRoomsAmount());
    }

    @Test
    public void isAnyFreeRoom() {
        hotel = new Hotel(1);
        assertTrue(hotel.isAnyFreeRoom());
    }
}
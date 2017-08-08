package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private static final int DEFAULT_ROOMS_COUNT = 10;
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(DEFAULT_ROOMS_COUNT);
    }

    @Test
    public void afterCreating_anyIsFree() {
        assertTrue(hotel.hasAnyFreeRoom());
    }

    @Test
    public void afterCreating_EveryRoomIsFree() {
        for (int i = 0; i<hotel.getRoomsCount(); i++)
            assertTrue(hotel.hasFreeRoom(i));
    }

    @Test
    public void afterEmptyCreating_NoFreeRoom() {
        hotel =  new Hotel(0);
        hotel.hasAnyFreeRoom();
    }

    @Test
    public void afterRenting_roomIsNotFree() {
        int roomNumber = hotel.rentAny(new Person("name", "surname"));
        assertFalse(hotel.hasFreeRoom(roomNumber));
    }

    @Test
    public void afterRenting_PersonIsRenting() {
        Person person = new Person("name", "surname");
        hotel.rentAny(person);
        assertTrue(hotel.isRenting(person.getSurname()));
    }

    @Test
    public void withoutRenting_PersonIsNotRenting() {
        Person person = new Person("name", "surname");
        assertFalse(hotel.isRenting(person.getSurname()));
    }

    @Test
    public void afterRentingAll_noFreeRoom() {
        rentAllRooms();
        assertFalse(hotel.hasAnyFreeRoom());
    }

    private void rentAllRooms() {
        Person person = new Person("Name", "Surname");
        for (int i=0; i<DEFAULT_ROOMS_COUNT; i++)
            hotel.rentAny(person);
    }
}
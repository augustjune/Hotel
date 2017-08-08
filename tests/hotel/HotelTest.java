package hotel;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private static final int DEFAULT_ROOMS_COUNT = 10;
    private Hotel hotel;
    private Person person;


    @BeforeEach
    void setUp() {
        hotel = new Hotel(DEFAULT_ROOMS_COUNT);
        person = new Person("name", "surname");
    }

    @Test
    public void cantCreateEmptyHotel() {
        assertThrows(Hotel.EmptyHotelException.class, () -> {
            hotel = new Hotel(0);
        });
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
    public void afterRenting_roomIsNotFree() {
        int roomNumber = hotel.rentAny(person);
        assertFalse(hotel.hasFreeRoom(roomNumber));
    }

    @Test
    public void afterRenting_PersonIsRenting() {
        hotel.rentAny(person);
        assertTrue(hotel.isRenting(person.getSurname()));
    }

    @Test
    public void afterAllRented_CantBeAnyRented() {
        rentAllRooms();
        assertThrows(Hotel.NoFreeRoomException.class,() -> {
            hotel.rentAny(person);
        });
    }
    
    @Test
    public void afterReleasing_RoomIsFree() {
        int roomNumber = rentAndRelease(person);
        assertTrue(hotel.hasFreeRoom(roomNumber));
    }

    @Test
    public void afterReleasing_PersonIsNotRenting() {
        rentAndRelease(person);
        assertFalse(hotel.isRenting(person.getSurname()));
    }

    @Test
    public void afterRenting2AndReleasing1_PersonIsStillRenting() {
        hotel.rentAny(person);
        rentAndRelease(person);
        assertTrue(hotel.isRenting(person.getSurname()));
    }

    @Test
    public void withoutRenting_PersonIsNotRenting() {
        assertFalse(hotel.isRenting(person.getSurname()));
    }

    @Test
    public void afterRentingAll_noFreeRoom() {
        rentAllRooms();
        assertFalse(hotel.hasAnyFreeRoom());
    }

    @Test
    public void afterRentingAndReleasingAll_PersonIsNotRenting() {
        hotel.rentAny(person);
        hotel.rentAny(person);
        hotel.rentAny(person);
        hotel.releaseAll(person);
        assertFalse(hotel.isRenting(person.getSurname()));
    }

    private int rentAndRelease(Person person) {
        int roomNumber = hotel.rentAny(person);
        hotel.release(roomNumber);
        return roomNumber;
    }

    private void rentAllRooms() {
        for (int i=0; i<DEFAULT_ROOMS_COUNT; i++)
            hotel.rentAny(person);
    }
}
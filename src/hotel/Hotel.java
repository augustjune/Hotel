package hotel;

class Hotel {

    private int roomsCount;
    private Room[] rooms;

    Hotel(int rooms) {
        if (rooms == 0)
            throw new EmptyHotelException();
        this.roomsCount = rooms;
        this.rooms = new Room[rooms];
        initializeRooms();
    }

    private void initializeRooms() {
        for (int i = 0; i < roomsCount; i++)
            rooms[i] = new Room(i+1);
    }

    int getRoomsCount() {
        return roomsCount;
    }

    boolean hasAnyFreeRoom() {
        for (Room room : rooms)
            if (room.isFree())
                return true;
        return false;
    }

    int rentAny(Person person) {
        checkForFreeRoom();
        int roomForRenting = findFirstFreeRoom();
        rooms[roomForRenting].rent(person);
        return roomForRenting;
    }

    private void checkForFreeRoom() {
        if (!hasAnyFreeRoom())
            throw new NoFreeRoomException();
    }

    private int findFirstFreeRoom() {
        for (int i=0; i<rooms.length; i++)
            if (rooms[i].isFree())
                return i;
        return -1;
    }

    void release(int roomNumber) {
        rooms[roomNumber].release();
    }

    boolean isRenting(String surname) {
        for (Room room : rooms)
            if (room.isRentedBy(surname))
                    return true;
        return false;
    }

    boolean hasFreeRoom(int number) {
        return rooms[number].isFree();
    }

    void releaseAll(Person person) {
        for (Room room : rooms)
            if (room.isRentedBy(person))
                    room.release();
    }

    static class NoFreeRoomException extends RuntimeException{

    }

    static class EmptyHotelException extends RuntimeException {
    }
}
package hotel;

class Hotel {

    private int roomsCount;
    private Room[] rooms;

    Hotel(int rooms) {
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
        int roomForRenting = 0;
        while (!rooms[roomForRenting].isFree())
            roomForRenting++;
        rooms[roomForRenting].rent(person);
        return roomForRenting;
    }

    boolean isRenting(String surname) {
        for (Room room : rooms) {
            if (!room.isFree())
                if (room.getRenterSurname().equals(surname))
                    return true;
        }
        return false;
    }

    boolean hasFreeRoom(int number) {
        return rooms[number].isFree();
    }
}
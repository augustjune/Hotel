package hotel;

public interface RoomRenter {

    boolean hasAnyFreeRoom();
    boolean hasFreeRoom(int number);
    int getFreeRoomsCount();
    int rentAny(Person person);
    void release(int roomNumber);
    void releaseAll(Person person);
}

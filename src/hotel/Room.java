package hotel;

class Room {
    private int floor;
    private int number;
    private Person renter;

    Room(int number) {
        this.number = number;
    }

    void rent(Person person) {
        renter = person;
    }

    void release() {
        renter = null;
    }

    String getRenterSurname() {
        if (isRented())
            return renter.getSurname();
        throw new NotRentedException();
    }

    boolean isRentedBy(Person person) {
        return person.equals(renter);
    }

    boolean isRentedBy(String surname) {
        return isRented() && surname.equals(renter.getSurname());
    }

    boolean isFree() {
        return renter == null;
    }

    boolean isRented() {
        return !isFree();
    }

    public int getFloor() {
        return floor;
    }

    public int getNumber() {
        return number;
    }

    private class NotRentedException extends RuntimeException {

    }
}
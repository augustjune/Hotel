package hotel;

class Room {
    private int number;
    private boolean isRented;
    private Person renter;

    Room(int number) {
        this.number = number;
    }

    void rent(Person person) {
        if (!isRented) {
            isRented = true;
            renter = person;
        }
    }

    String getRenterSurname() {
       if (isRented)
           return renter.getSurname();
       throw new NotRentedException();
    }

    boolean isFree() {
        return !isRented;
    }

    public int getNumber() {
        return number;
    }

    private class NotRentedException extends RuntimeException{
    }
}

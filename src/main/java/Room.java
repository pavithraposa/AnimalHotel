public class Room {

    String roomName;
    int roomNumber;
    boolean isBooked;
    Animal guests;

    public Room(String roomName, int roomNumber, boolean isBooked) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.isBooked = isBooked;

    }

    public Room(String roomName, int roomNumber, boolean isBooked, Animal guests) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.isBooked = isBooked;
        this.guests = guests;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setIsBooked(boolean newIsBooked) {
        isBooked = newIsBooked;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setGuests(Animal newGuests) {
        guests = newGuests;
    }

    public Animal getGuests() {
        return guests;
    }

    public String describe() {
        return "A room that an animal can hire";
    }
}
//Super class that is being inherited from to other Room sub-classes
public class Room {

    //Attributes that will be used to define the objects
    private final String roomName;
    private final int roomNumber;
    private boolean isBooked;
    private Animal guests;

    //Constructor, used to initialize objects and is being called when an object is created of this class, if given 3 arguments/parameters
    public Room(String roomName, int roomNumber, boolean isBooked) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.isBooked = isBooked;

    }

    //Constructor, used to initialize objects and is being called when an object is created of this class, if given 4 arguments/parameters
    public Room(String roomName, int roomNumber, boolean isBooked, Animal guests) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.isBooked = isBooked;
        this.guests = guests;
    }

    //Getters and setter that lets user change and set the attributes of each object of this class
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

    //Method with general description of rooms
    public String describe() {
        return "A room that an animal can hire";
    }
}
//Sub-class that inherit from class "Room"
public class GroundRoom extends Room {

    //Constructor, used to initialize objects and is being called when an object is created of this class.
    public GroundRoom(String roomName, int roomNumber, boolean isBooked) {
        super(roomName, roomNumber, isBooked);
    }

    //Method that overrides describe() method from class "Room"
    public String describe() {
        return "The ground room is shaped like a long rectangle, for an easier experience if you like running.";
    }
}
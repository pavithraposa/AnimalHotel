//Sub-class that inherit from class "Room"
public class MarineRoom extends Room {

    //Constructor, used to initialize objects and is being called when an object is created of this class.
    public MarineRoom ( String roomName, int roomNumber, boolean isBooked) {
        super(roomName, roomNumber, isBooked);
    }

    //Method that overrides describe() method from class "Room"
    public String describe(){
        return "The marine room consists of a big pool for animals that prefer the water";
    }
}
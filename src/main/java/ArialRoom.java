//Sub-class that inherit from class "Room"
public class ArialRoom extends Room {

    //Constructor, used to initialize objects and is being called when an object is created of this class.
    public ArialRoom(String roomName, int roomNumber, boolean isBooked) {
        super(roomName, roomNumber, isBooked);
    }

    //Method that overrides describe() method from class "Room"
    public String describe() {
        return "The arial room got a high ceiling, for the ability to fly and lots of trees.";
    }
}
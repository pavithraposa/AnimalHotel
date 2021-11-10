public class ArialRoom extends Room {

    public ArialRoom(String roomName, int roomNumber, boolean isBooked) {
        super(roomName, roomNumber, isBooked);
    }

    public String describe() {
        return "The arial room got a high ceiling, for the ability to fly and lots of trees.";
    }
}
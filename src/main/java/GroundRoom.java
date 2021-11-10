public class GroundRoom extends Room {

    public GroundRoom(String roomName, int roomNumber, boolean isBooked) {
        super(roomName, roomNumber, isBooked);
    }

    public String describe() {
        return "The ground room is shaped like a long rectangle, for an easier experience if you like running.";
    }
}
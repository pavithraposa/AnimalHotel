public class MarineRoom extends Room {

    public MarineRoom ( String roomName, int roomNumber, boolean isBooked) {
        super(roomName, roomNumber, isBooked);
    }

    public String describe(){
        return "The marine room consists of a big pool for animals that prefer the water";
    }
}
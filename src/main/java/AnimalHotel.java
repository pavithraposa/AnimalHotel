import java.io.*;
import java.util.*;

public class AnimalHotel {

    ArrayList<Room> guestRooms;//Array list of Room class
    File roomList;
    Animal currentAnimalObj;
    String animal;

    public void start() throws IOException {

        roomList = new File("RoomList.txt");
        guestRooms = new ArrayList<>();

        boolean programRunning = true;

        //Creating 6 rooms of 3 different kinds, and adding them to an Array
        createRooms();

        //Replaces created rooms with the same room but with a guest if saved into "RoomList.txt"
        replaceRooms();

        //While loop that as long as (true) keeps the program running
        while (programRunning) {

            // Prints the Main menu, and lets the user select an option that represents what the user wants to do.
            int userChoice = mainMenu();

            switch (userChoice) {

                case 1 -> checkIn();

                case 2 -> checkOut();

                case 3 -> editBookings();

                case 4 -> listBookings();

                case 5 -> individualBookingDetails();

                case 6 -> filterBookings();

                case 7 -> programRunning = exitProgram();

                default -> {
                    System.out.println("Faulty input");
                    System.out.println("------------");
                }
            }
        }
    }

    //Creating 6 rooms of 3 different kinds, and adding them to an Array
    public void createRooms() {

        Room marineRoom1 = new MarineRoom("MarineRoom1", 1, false);
        Room marineRoom2 = new MarineRoom("MarineRoom2", 2, false);
        guestRooms.add(marineRoom1);
        guestRooms.add(marineRoom2);

        Room groundRoom1 = new GroundRoom("GroundRoom1", 3, false);
        Room groundRoom2 = new GroundRoom("GroundRoom2", 4, false);
        guestRooms.add(groundRoom1);
        guestRooms.add(groundRoom2);

        Room arialRoom1 = new ArialRoom("ArialRoom1", 5, false);
        Room arialRoom2 = new ArialRoom("ArialRoom2", 6, false);
        guestRooms.add(arialRoom1);
        guestRooms.add(arialRoom2);
    }

    //Prints the main menu
    public int mainMenu() {

        System.out.println(ConsoleColors.CYAN_ITALIC);
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to our hotel booking manager");
        System.out.println("\tPlease select a choice");
        System.out.println("\t----------------------");

        System.out.println("\t[1] Check in guest");
        System.out.println("\t[2] Check out guest");
        System.out.println("\t[3] Edit bookings");
        System.out.println("\t[4] List bookings");
        System.out.println("\t[5] Individual booking details");
        System.out.println("\t[6] Search among bookings");
        System.out.println("\t[7] Exit program");
        System.out.print("\t[?]> ");
        return getUserInt();
    }

    //User can check in guests creating an object, and assign them to a room, by putting the guest object in an existing room object
    public void checkIn() {

        int animalChoice;

        do {

            System.out.println(ConsoleColors.GREEN_ITALIC);

            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("What kind of animal are you?");
            System.out.println("----------------------------");
            System.out.println("\t[1] Orca");
            System.out.println("\t[2] Cheetah");
            System.out.println("\t[3] Peregrine falcon");
            System.out.print("\t[?]> ");
            animalChoice = getUserInt();

            switch (animalChoice) {

                case 1 -> animal = "orca";

                case 2 -> animal = "cheetah";

                case 3 -> animal = "peregrineFalcon";

                default -> {
                    System.out.println("Faulty input");
                    System.out.println("------------");
                }
            }
        } while (animalChoice < 1 || animalChoice > 3);

        System.out.println("What is your name?");
        System.out.println("------------------");
        System.out.print("\t> ");
        String name = getUserString();

        System.out.println("What is your favorite activity?");
        System.out.println("-------------------------------");
        System.out.print("\t> ");
        String favoriteActivity = getUserString();

        System.out.println("What is your favorite food?");
        System.out.println("---------------------------");
        System.out.print("\t> ");
        String favoriteFood = getUserString();

        switch (animal) {

            case "orca" -> currentAnimalObj = new Orca(name, favoriteActivity, favoriteFood);

            case "cheetah" -> currentAnimalObj = new Cheetah(name, favoriteActivity, favoriteFood);

            case "peregrineFalcon" -> currentAnimalObj = new PeregrineFalcon(name, favoriteActivity, favoriteFood);
        }

        while (true) {

            int numOfBookedRooms = 0;

            System.out.println("\tAvailable Rooms              Description");
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            //Prints all rooms in the hotel Which are available
            for (int i = 0; i < guestRooms.size(); i++) {

                if (!guestRooms.get(i).getIsBooked()) {

                    System.out.printf("\t[%-1d]%-26s%s\n", (i + 1), guestRooms.get(i).getRoomName(), guestRooms.get(i).describe());
                    numOfBookedRooms++;
                }
            }
            if (numOfBookedRooms == 0) {

                System.out.println("There are no available rooms at this point in time");
                System.out.println("----------------------------------------------------------------------------------------------------------------");

                break;
            } else {

                System.out.print("\t[?]> ");
                int userChoice = getUserInt();

                try {

                    if (guestRooms.get(userChoice - 1).getIsBooked()) {

                        System.out.println("This room is already booked, please select rooms from list of available rooms");
                        System.out.println("----------------------------------------------------------------------------------------------------------------");

                    } else {

                        guestRooms.get(userChoice - 1).setGuests(currentAnimalObj);
                        guestRooms.get(userChoice - 1).setIsBooked(true);
                        System.out.println("Room number [" + guestRooms.get(userChoice - 1).getRoomNumber() + "] is booked in the name of " + guestRooms.get(userChoice - 1).getGuests().getName());
                        System.out.println("-------------------------------------------------------------------------------");

                        break;
                    }
                } catch (Exception e) {

                    System.out.println("Faulty Input");
                    System.out.println("-------------");
                }
            }
        }
    }

    //User can check out guest, removes the guest object from the room object
    public void checkOut() {

        System.out.println(ConsoleColors.RED_ITALIC);

        listBookings();

        System.out.println("Enter the room number to Checking out");
        System.out.println("-------------------------------------");
        System.out.print("\t[?]> ");
        int userChoice = getUserInt();

        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked()) {

                if (userChoice == guestRoom.getRoomNumber()) {

                    System.out.println(guestRoom.getGuests().getName() + " has been checked out");
                    System.out.println("------------------------------");

                    guestRoom.setGuests(null);
                    guestRoom.setIsBooked(false);
                }
            }
        }
    }

    //Prints all the current bookings
    public void listBookings() {

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Room number                Name");
        System.out.println("--------------------------------------");

        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked()) {

                System.out.printf("\t[%d]%24s\n", guestRoom.getRoomNumber(), guestRoom.getGuests().getName());
            }
        }
    }

    //
    public void editBookings() {

        System.out.println(ConsoleColors.YELLOW_ITALIC);

        listBookings();

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter the room number of the guest you want to edit");
        System.out.println("---------------------------------------------------");
        System.out.print("\t[?]> ");
        int userChoice1 = getUserInt();

        for (Room guestRoom : guestRooms) {

            if (userChoice1 == guestRoom.getRoomNumber()) {

                System.out.println("What would you like to change?");
                System.out.println("------------------------------");
                System.out.println("\t[1] Change name");
                System.out.println("\t[2] Change Favorite Activity");
                System.out.println("\t[3] Change Favorite Food");
                System.out.print("\t[?]> ");
                int userInput = getUserInt();

                //This if-method changes the guests name if the user input is == 1
                if (userInput == 1) {

                    System.out.println("What would you like to change name [" + guestRoom.getGuests().getName() + "] to?");
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.print("\t> ");
                    String userChoice = getUserString();

                    guestRoom.getGuests().setName(userChoice);
                    System.out.println("Name has been updated to [" + guestRoom.getGuests().getName() + "]");
                    System.out.println("-------------------------------------------------------------------------------");

                }
                //This else if-method changes the guests favorite activity if the user input is == 2
                else if (userInput == 2) {

                    System.out.println("What would you like to change favorite activity [" + guestRoom.getGuests().getFavoriteActivity() + " to?");
                    System.out.println("-------------------------------------------------------------------------------");

                    System.out.print("\t> ");
                    String userChoice = getUserString();

                    guestRoom.getGuests().setFavoriteActivity(userChoice);
                    System.out.println("Favorite activity has been updated to [" + guestRoom.getGuests().getFavoriteActivity() + "]");
                    System.out.println("-------------------------------------------------------------------------------");

                }
                //This else if-method changes the guests favorite food if the user input == 3
                else if (userInput == 3) {

                    System.out.println("What would you like to change favorite food [" + guestRoom.getGuests().getFavoriteFood() + "] to?");
                    System.out.println("-------------------------------------------------------------------------------");

                    System.out.print("\t> ");
                    String userChoice = getUserString();

                    guestRoom.getGuests().setFavoriteFood(userChoice);
                    System.out.println("Favorite food has been updated to [" + guestRoom.getGuests().getFavoriteFood() + "]");
                    System.out.println("-------------------------------------------------------------------------------");

                }
            }
        }
    }

    //
    public void individualBookingDetails() {

        System.out.println(ConsoleColors.PURPLE_ITALIC);

        listBookings();

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter the room number for booking details");
        System.out.println("-----------------------------------------");
        System.out.print("\t[?]> ");
        int userChoice = getUserInt();

        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked() && userChoice == guestRoom.getRoomNumber()) {

                System.out.println("Name                   Favorite Activity                   Favorite Food");
                System.out.println("------------------------------------------------------------------------");
                System.out.printf("\t%-23s%-36s%s\n\n", guestRoom.getGuests().getName(), guestRoom.getGuests().getFavoriteActivity(), guestRoom.getGuests().getFavoriteFood());

                System.out.println("Room Number            Description");
                System.out.println("-------------------------------------------------------------------------------------------------------");
                System.out.printf("\t[%d]%92s\n", guestRoom.getRoomNumber(), guestRoom.describe());
            } else {

            }
        }
    }

    //
    public void filterBookings() {

        System.out.println(ConsoleColors.LIGHT_PINK);

        System.out.println("----------------------------------------------------------------------------------------------------------------");


        int bookedGuests = 0;

        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked()) {

                bookedGuests++;
            }
        }

        if (bookedGuests != 0) {

            System.out.println("Enter what you would like to filter the names by");
            System.out.println("------------------------------------------------");
            System.out.print("\t> ");
            String userChoice = getUserString().toLowerCase();
            System.out.println("These are all guests that has [" + userChoice + "] in their name");
            System.out.println("----------------------------------------------------------------");

            for (Room guestRoom : guestRooms) {

                if (guestRoom.getIsBooked() && guestRoom.getGuests().getName().contains(userChoice)) {

                    System.out.printf("%-19s%d\n", guestRoom.getGuests().getName(), guestRoom.getRoomNumber());
                }
            }
        } else {
            System.out.println("There are no guests booked at this point in time");
        }
    }

    //
    public boolean exitProgram() throws IOException {

        FileWriter fw = new FileWriter(roomList);

        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked()) {

                String outPutText = guestRoom.getRoomName() + "|" + guestRoom.getRoomNumber() + "|" + guestRoom.getIsBooked() + "|" + guestRoom.getGuests().getName() + "|" + guestRoom.getGuests().getFavoriteFood() + "|" + guestRoom.getGuests().getFavoriteActivity() + "\n";
                saveToFile("RoomList.txt", outPutText, true);
            }
        }

        System.out.println("Shutting Down...");
        return false;
    }

    //
    public static void saveToFile(String fileName, String text, boolean append) throws IOException {

        File file = new File(fileName);

        FileWriter fw = new FileWriter(file, append);

        fw.write(text);

        fw.close();
    }

    //
    public ArrayList<Room> loadToList(String fileName) throws FileNotFoundException {

        File file = new File(fileName);

        Scanner myScanner = new Scanner(file);

        ArrayList<Room> guests = new ArrayList<>();

        if (file.length() > 1) {

            while (myScanner.hasNextLine()) {

                String line = myScanner.nextLine();

                String[] items = line.split("\\|");

                String roomName = items[0];
                int roomNumber = Integer.parseInt(items[1]);
                boolean isBooked = Boolean.parseBoolean(items[2]);
                String animalName = items[3];
                String animalActivity = items[4];
                String animalFood = items[5];

                Animal newObject = new Animal(animalName, animalActivity, animalFood);

                if (roomName.equals("MarineRoom1")) {
                    Room MarineRoom1 = new Room(roomName, roomNumber, isBooked, newObject);
                    guests.add(MarineRoom1);

                } else if (roomName.equals("MarineRoom2")) {
                    Room MarineRoom2 = new Room(roomName, roomNumber, isBooked, newObject);
                    guests.add(MarineRoom2);

                } else if (roomName.equals("GroundRoom1")) {
                    Room GroundRoom1 = new Room(roomName, roomNumber, isBooked, newObject);
                    guests.add(GroundRoom1);

                } else if (roomName.equals("GroundRoom2")) {
                    Room GroundRoom2 = new Room(roomName, roomNumber, isBooked, newObject);
                    guests.add(GroundRoom2);

                } else if (roomName.equals("ArialRoom2")) {
                    Room ArialRoom1 = new Room(roomName, roomNumber, isBooked, newObject);
                    guests.add(ArialRoom1);

                } else if (roomName.equals("ArialRoom1")) {
                    Room ArialRoom2 = new Room(roomName, roomNumber, isBooked, newObject);
                    guests.add(ArialRoom2);
                }
            }
        }

        return guests;
    }

    //
    public void replaceRooms() throws FileNotFoundException {

        for (int i = 0; i < loadToList("RoomList.txt").size(); i++) {

            for (Room guestRoom : guestRooms) {

                if (loadToList("RoomList.txt").get(i).getRoomNumber() == guestRoom.getRoomNumber()) {

                    Room currentRoom = loadToList("RoomList.txt").get(i);

                    guestRoom.setGuests(currentRoom.getGuests());
                    guestRoom.setIsBooked(currentRoom.getIsBooked());
                }
            }
        }
    }

    //
    public static String getUserString() {

        Scanner myScanner = new Scanner(System.in);

        return myScanner.nextLine();
    }

    //
    public static int getUserInt() {

        Scanner myScanner = new Scanner(System.in);

        int userChoice;

        while (true) {

            try {

                userChoice = Integer.parseInt(myScanner.nextLine());
                break;
            } catch (Exception e) {

                System.out.println("Faulty input");
                System.out.println("-------------");
                System.out.print("\t[?]> ");
            }
        }
        return userChoice;
    }

}
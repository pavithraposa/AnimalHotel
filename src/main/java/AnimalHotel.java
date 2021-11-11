import java.io.*;
import java.util.*;

public class AnimalHotel {

    private ArrayList<Room> guestRooms;
    private File roomList;
    private Animal currentAnimalObj;
    private String animal;

    public void start() throws IOException {

        //Object for RoomList.txt
        roomList = new File("RoomList.txt");
        //If it does not exist it creates a new file, if it exists it does nothing
        roomList.createNewFile();

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

            //Checks what animal to sign in and saves a string to variable "animal"
            switch (animalChoice) {

                case 1 -> animal = "orca";

                case 2 -> animal = "cheetah";

                case 3 -> animal = "peregrineFalcon";

                default -> {
                    System.out.println("Faulty input");
                    System.out.println("------------");
                }
            }
            //As long as this condition is true the do while loop will repeat itself.
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

        //Switch case that creates an object of the type chosen and assigned to "animal"and putting it to currentAnimalObj, giving the object its parameters by saving strings and using them while creating the object
        switch (animal) {

            case "orca" -> currentAnimalObj = new Orca(name, favoriteActivity, favoriteFood);

            case "cheetah" -> currentAnimalObj = new Cheetah(name, favoriteActivity, favoriteFood);

            case "peregrineFalcon" -> currentAnimalObj = new PeregrineFalcon(name, favoriteActivity, favoriteFood);
        }

        //This while loop will keep running until its broken, and that happens if all rooms are booked and if you assign the currentAnimalObj to a room.
        while (true) {

            int numberOfAvailableRooms = 0;

            System.out.println("\tAvailable Rooms              Description");
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            //Prints all rooms in the hotel which are available, and adds 1 to numberOfAvailableRooms. If all rooms are booked numberOfAvailableRooms = 0, and it will break the while loop
            for (int i = 0; i < guestRooms.size(); i++) {

                if (!guestRooms.get(i).getIsBooked()) {

                    System.out.printf("\t[%-1d]%-26s%s\n", (i + 1), guestRooms.get(i).getRoomName(), guestRooms.get(i).describe());
                    numberOfAvailableRooms++;
                }
            }
            //if there are no available rooms it does not allow the user to book the room.breaks the loop,Otherwise it will allow the user to book the Room.
            if (numberOfAvailableRooms == 0) {

                System.out.println("There are no available rooms at this point in time");
                System.out.println("----------------------------------------------------------------------------------------------------------------");

                break;
            } else {

                System.out.print("\t[?]> ");
                int userChoice = getUserInt();

                //Checks if the room that the user selected is booked
                try {

                    if (guestRooms.get(userChoice - 1).getIsBooked()) {

                        System.out.println("This room is already booked, please select rooms from list of available rooms");
                        System.out.println("----------------------------------------------------------------------------------------------------------------");

                        //If the selected room is not booked it will add the currentAnimalObj to that selected room and set IsBooked to true(which makes the room booked) and then break the while loop.
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

        boolean run = true;


        listBookings();

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter the room number to Checking out");
        System.out.println("-------------------------------------");
        System.out.print("\t[?]> ");
        int userChoice = getUserInt();

        //Loops through the guest rooms and checks if it is booked, and if the userChoice = the room number. If true, removes the guest from that room by setting it to null and setting IsBooked to false(room is available)
        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked() && userChoice == guestRoom.getRoomNumber()) {

                System.out.println(guestRoom.getGuests().getName() + " has been checked out");
                System.out.println("------------------------------");

                guestRoom.setGuests(null);
                guestRoom.setIsBooked(false);

            }
        }
    }

    //Prints all the current bookings which are booked
    public void listBookings() {

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Room number                Name");
        System.out.println("--------------------------------------");

        //Loops through all rooms and checks if IsBooked is true, and prints all the rooms that it applies to.
        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked()) {

                System.out.printf("\t[%d]%24s\n", guestRoom.getRoomNumber(), guestRoom.getGuests().getName());
            }
        }
    }

    //Uses the listBookings method to print all bookings, then allows the user to change any of the information about guests.
    public void editBookings() {

        System.out.println(ConsoleColors.YELLOW_ITALIC);

        listBookings();

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter the room number of the guest you want to edit");
        System.out.println("---------------------------------------------------");
        System.out.print("\t[?]> ");
        int userChoice1 = getUserInt();

        //Loops through all rooms and compares userChoice1 to all the room numbers, if any of them match it will proceed to ask what you want to change
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

    //Uses the listBookings method to print all bookings, then allows the user to choose a room and see more details about this booking
    public void individualBookingDetails() {

        System.out.println(ConsoleColors.PURPLE_ITALIC);

        int numberOfAvailableRooms = 0;

        //Loops through all rooms and adds 1 to numberOfAvailableRooms if IsBooked = false(available)
        for (Room guestRoom : guestRooms) {

            if (!guestRoom.getIsBooked()) {

                numberOfAvailableRooms++;
            }
        }
        //If statement prints there are no guests if numberOfAvailableRooms = 6, if false it will proceed into else statement
        if (numberOfAvailableRooms == 6) {

            System.out.println("There are no guests at this point in time");
        } else {

            listBookings();

            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("Enter the room number for booking details");
            System.out.println("-----------------------------------------");
            System.out.print("\t[?]> ");
            int userChoice = getUserInt();

            //For loop compares userChoice to room numbers in all the booked room, if true, it will display more details about that specific booking
            for (Room guestRoom : guestRooms) {

                if (guestRoom.getIsBooked() && userChoice == guestRoom.getRoomNumber()) {

                    System.out.println("Name                   Favorite Activity                   Favorite Food");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.printf("\t%-23s%-36s%s\n\n", guestRoom.getGuests().getName(), guestRoom.getGuests().getFavoriteActivity(), guestRoom.getGuests().getFavoriteFood());

                    System.out.println("Room Number            Description");
                    System.out.println("-------------------------------------------------------------------------------------------------------");
                    System.out.printf("\t[%d]%92s\n", guestRoom.getRoomNumber(), guestRoom.describe());
                }
            }
        }
    }

    //Allows the user to enter a character or a string to filter through the list of bookings by name, and show which bookings contain the "filter".
    public void filterBookings() {

        System.out.println(ConsoleColors.LIGHT_PINK);

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        int bookedGuests = 0;

        //For loop that checks if there are any booked rooms.
        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked()) {

                bookedGuests++;
            }
        }

        // If there are any guests(bookedGuests != 0) then it filters names with the user input and give the list otherwise,it  displays there are are no guests
        if (bookedGuests != 0) {

            System.out.println("Enter what you would like to filter the names by");
            System.out.println("------------------------------------------------");
            System.out.print("\t> ");
            String userChoice = getUserString().toLowerCase();
            System.out.println("These are all guests that has [" + userChoice + "] in their name");
            System.out.println("----------------------------------------------------------------");

            //If any of the guests names contains what user waned to filter by, it will display them.
            for (Room guestRoom : guestRooms) {

                if (guestRoom.getIsBooked() && guestRoom.getGuests().getName().contains(userChoice)) {

                    System.out.printf("%-19s%d\n", guestRoom.getGuests().getName(), guestRoom.getRoomNumber());
                }
            }
        } else {

            System.out.println("There are no guests booked at this point in time");
        }
    }

    //If selected this method saves all the rooms with guests in it by calling saveToFile(), and writes the down to a txt document, and then exits the program.
    public boolean exitProgram() throws IOException {

        //Creates a FileWriter, that write to file roomList
        new FileWriter(roomList);

        //Checks if any rooms are booked, if true it will save all of those rooms and guests into a txt document by splitting all parameters with "|". And then returning false to programRunning, which exits the program.
        for (Room guestRoom : guestRooms) {

            if (guestRoom.getIsBooked()) {

                String outPutText = guestRoom.getRoomName() + "|" + guestRoom.getRoomNumber() + "|" + guestRoom.getIsBooked() + "|" + guestRoom.getGuests().getName() + "|" + guestRoom.getGuests().getFavoriteFood() + "|" + guestRoom.getGuests().getFavoriteActivity() + "\n";
                saveToFile("RoomList.txt", outPutText, true);
            }
        }

        System.out.println("Shutting Down...");
        return false;
    }

    //Creates a file with same name as the one created before, then writes to the same file and then closes the file
    public static void saveToFile(String fileName, String text, boolean append) throws IOException {

        //Creating an object of a file
        File file = new File(fileName);

        //FileWriter that writes to file(file), in this case RoomList.txt
        FileWriter fw = new FileWriter(file, append);

        //Writes down "text" which is the saved rooms.
        fw.write(text);

        //Closes the FileWriter
        fw.close();
    }

    //Loads all the information from the txt document, and saves it in different variables, and uses them to create the objects that were saved to the txt document.
    public ArrayList<Room> loadToList(String fileName) throws FileNotFoundException {

        //Creates a new file and sets it as RoomList.txt
        File file = new File(fileName);

        //Creates a scanner that scans our selected file (RoomList.txt)
        Scanner myScanner = new Scanner(file);

        //An array list of the class called Room, Where all of our saved rooms will be to from RoomList.txt
        ArrayList<Room> guests = new ArrayList<>();

        //Checks if the file length is bigger than 1, if true it will start to upload
        if (file.length() > 1) {

            //As long as scanner has a next line, it will keep run the while loop
            while (myScanner.hasNextLine()) {

                //Creates a String variable that contains one of the lines in text document, and then splits the string by each "|" adn saves all the elements into an array
                String line = myScanner.nextLine();

                String[] items = line.split("\\|");

                String roomName = items[0];
                int roomNumber = Integer.parseInt(items[1]);
                boolean isBooked = Boolean.parseBoolean(items[2]);
                String animalName = items[3];
                String animalActivity = items[4];
                String animalFood = items[5];

                //Creates a new animal object with all the parameters that goes into an animal
                Animal newObject = new Animal(animalName, animalActivity, animalFood);

                //If statement that depending on the name, it creates a new room for that room type and sets its guest that was created above "newObject"
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

    //Replaces created rooms with the same room but with a guest if saved into "RoomList.txt"
    public void replaceRooms() throws FileNotFoundException {

        //Loops through array returned from loadToList();
        for (int i = 0; i < loadToList("RoomList.txt").size(); i++) {

            for (Room guestRoom : guestRooms) {

                //Compares the room numbers from that array and the on created at the start of the program, if they match it will set the same guest in currentRoom to the room in guestRoom with same room number, and also setting IsBooked true.
                if (loadToList("RoomList.txt").get(i).getRoomNumber() == guestRoom.getRoomNumber()) {

                    Room currentRoom = loadToList("RoomList.txt").get(i);

                    guestRoom.setGuests(currentRoom.getGuests());
                    guestRoom.setIsBooked(currentRoom.getIsBooked());
                }
            }
        }
    }

    //Allows the user to write a string into the program.
    public static String getUserString() {

        Scanner myScanner = new Scanner(System.in);

        return myScanner.nextLine();
    }

    //Allows the user to write an int into the program, and if it is not an int says faulty input and asks for one again.
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
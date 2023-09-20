import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static List<String> arrivalTrains = new ArrayList<>();
    public static List<String> departureTrains = new ArrayList<>();
    public static List<String> arrivalTimes = new ArrayList<>();
    public static List<String> departureTimes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Stockholm Central. Please enter a command.");
        int choice;

        do {
            displayMenu();
            choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTrain();
                    break;
                case 2:
                    updateTrain();
                    break;
                case 3:
                    addTrainDelay();
                    break;
                case 4:
                    removeTrain();
                    break;
                case 5:
                    showDepartures();
                    break;
                case 6:
                    showArrivals();
                    break;
                case 7:
                    showAllTrains();
                    break;
                case 8:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 8.");
            }

        } while (choice != 8);

        System.out.println("Goodbye!! Thank you for using this program");
        input.close();
    }

    public static void displayMenu() {
        System.out.println("1 - Add Train/Arrival/Departure.");
        System.out.println("2 - Update Train Information.");
        System.out.println("3 - Add Train Delay.");
        System.out.println("4 - Remove Train.");
        System.out.println("5 - Show Departures.");
        System.out.println("6 - Show Arrivals.");
        System.out.println("7 - Show All Trains.");
        System.out.println("8 - Exit.");
        System.out.print("Enter your choice: ");
    }

    public static void addTrain() {
        System.out.print("Enter the train type (Arrival or Departure): ");
        String trainType = input.nextLine();

        System.out.print("Enter the name of the train: ");
        String trainName = input.nextLine();

        System.out.print("Enter the time: ");
        String time = input.nextLine();

        if (trainType.equalsIgnoreCase("Arrival")) {
            arrivalTrains.add(trainName);
            arrivalTimes.add(time);
            System.out.println("Arrival train added successfully!");
        } else if (trainType.equalsIgnoreCase("Departure")) {
            departureTrains.add(trainName);
            departureTimes.add(time);
            System.out.println("Departure train added successfully!");
        } else {
            System.out.println("Invalid train type. Please enter 'Arrival' or 'Departure'.");
        }
    }

    public static void updateTrain() {
        System.out.print("Enter the name of the train to update: ");
        String trainName = input.nextLine();

        if (arrivalTrains.contains(trainName)) {
            System.out.print("Enter the new arrival time: ");
            String newTime = input.nextLine();
            int index = arrivalTrains.indexOf(trainName);
            arrivalTimes.set(index, newTime);
            System.out.println("Arrival time updated successfully!");
        } else if (departureTrains.contains(trainName)) {
            System.out.print("Enter the new departure time: ");
            String newTime = input.nextLine();
            int index = departureTrains.indexOf(trainName);
            departureTimes.set(index, newTime);
            System.out.println("Departure time updated successfully!");
        } else {
            System.out.println("Train not found.");
        }
    }

    public static void addTrainDelay() {
        System.out.print("Enter the name of the train to add delay: ");
        String trainName = input.nextLine();

        if (arrivalTrains.contains(trainName)) {
            System.out.print("Enter the delay time for arrival: ");
            int delay = input.nextInt();
            input.nextLine();
            int index = arrivalTrains.indexOf(trainName);
            String currentTime = arrivalTimes.get(index);
            String[] parts = currentTime.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            minutes += delay;
            hours += minutes / 60;
            minutes %= 60;
            arrivalTimes.set(index, String.format("%02d:%02d", hours, minutes));
            System.out.println("Arrival delay added successfully!");
        } else if (departureTrains.contains(trainName)) {
            System.out.print("Enter the delay time for departure: ");
            int delay = input.nextInt();
            input.nextLine(); // Consume newline
            int index = departureTrains.indexOf(trainName);
            String currentTime = departureTimes.get(index);
            String[] parts = currentTime.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            minutes += delay;
            hours += minutes / 60;
            minutes %= 60;
            departureTimes.set(index, String.format("%02d:%02d", hours, minutes));
            System.out.println("Departure delay added successfully!");
        } else {
            System.out.println("Train not found.");
        }
    }

    public static void removeTrain() {
        System.out.print("Enter the name of the train to remove: ");
        String trainName = input.nextLine();

        if (arrivalTrains.remove(trainName)) {
            System.out.println("Arrival train removed successfully!");
        } else if (departureTrains.remove(trainName)) {
            System.out.println("Departure train removed successfully!");
        } else {
            System.out.println("Train not found.");
        }
    }

    public static void showDepartures() {
        System.out.println("\nDeparture Trains:");
        for (int i = 0; i < departureTrains.size(); i++) {
            System.out.println(departureTrains.get(i) + " - Departure Time: " + departureTimes.get(i));
        }
    }

    public static void showArrivals() {
        System.out.println("\nArrival Trains:");
        for (int i = 0; i < arrivalTrains.size(); i++) {
            System.out.println(arrivalTrains.get(i) + " - Arrival Time: " + arrivalTimes.get(i));
        }
    }

    public static void showAllTrains() {
        showArrivals();
        showDepartures();
    }
}


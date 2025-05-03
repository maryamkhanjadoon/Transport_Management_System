import java.util.*;

public class ViewAvailableVehiclesApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Filter 1: Input
        int userChoice = inputFilter(scanner);

        if (userChoice == 1) {
            // Filter 2: Load all vehicles
            List<String> allVehicles = vehicleLoader();

            // Filter 3: Ask if user wants filtering
            System.out.println("\nDo you want to filter based on:");
            System.out.println("1. Morning Buses");
            System.out.println("2. Evening Buses");
            System.out.print("Enter your choice (1 or 2): ");
            int filterOption = scanner.nextInt();

            // Filter 4: Apply time filter
            List<String> filteredVehicles = timeFilter(allVehicles, filterOption);

            // Filter 5: Display
            displayFilter(filteredVehicles);
        } else if (userChoice == 2) {
            System.out.println("\nFeature to view routes is coming soon.");
        } else {
            System.out.println("Invalid input. Please restart the program.");
        }

        scanner.close();
    }

    // Filter 1: Input filter
    public static int inputFilter(Scanner scanner) {
        System.out.println("Welcome to the Manage Bookings Page!");
        System.out.println("Press 1 to view available vehicles");
        System.out.println("Press 2 to view routes");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    // Filter 2: Load all vehicles
    public static List<String> vehicleLoader() {
        List<String> vehicles = new ArrayList<>();
        vehicles.add("Bus A - Morning - Main Campus ➝ Dhamtor via Nawasher — 7:30 AM");
        vehicles.add("Bus B - Morning - Main Campus ➝ Dhamtor via Muree Road — 8:00 AM");
        vehicles.add("Bus C - Morning - Main Campus ➝ Dhamtor via Main Road — 8:30 AM");
        vehicles.add("Bus D - Evening - Dhamtor ➝ Main Campus via Pma Road — 4:30 PM");
        vehicles.add("Bus E - Evening - Dhamtor ➝ Mansehra — 5:00 PM");
        vehicles.add("Bus F - Evening - Dhamtor ➝ Main Campus via Main Road — 5:30 PM");
        return vehicles;
    }

    // Filter 3: Time-based filter
    public static List<String> timeFilter(List<String> vehicles, int timeChoice) {
        List<String> result = new ArrayList<>();
        for (String vehicle : vehicles) {
            if (timeChoice == 1 && vehicle.contains("Morning")) {
                result.add(vehicle);
            } else if (timeChoice == 2 && vehicle.contains("Evening")) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // Filter 4: Display
    public static void displayFilter(List<String> vehicles) {
        System.out.println("\nFiltered Buses:");
        for (String v : vehicles) {
            System.out.println(v);
        }
    }
}

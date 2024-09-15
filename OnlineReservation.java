import java.util.*;

class OnlineReservation {
    static class Passenger {
        String name;
        String trainNumber;
        String trainName;
        String classType;
        String dateOfJourney;
        String fromPlace;
        String destination;
        int pnrNumber;
        double price;
        public Passenger(String name, String trainNumber, String trainName, String classType, String dateOfJourney, String fromPlace, String destination) {
            this.name = name;
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.fromPlace = fromPlace;
            this.destination = destination;
            this.pnrNumber = generatePNR(); 
            this.price = calculatePrice(); 
        }
        private int generatePNR() {
            return (int)(Math.random() * 1000000); 
        }

        private double calculatePrice() {
            double basePrice = 500; 
            switch (classType.toLowerCase()) {
                case "sleeper":
                    return basePrice;
                case "ac 3-tier":
                    return basePrice * 1.5;
                case "ac 2-tier":
                    return basePrice * 2;
                case "first class":
                    return basePrice * 3;
                default:
                    return basePrice; 
            }
        }
    }
    static List<Passenger> passengersList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Reservation System!");
        System.out.print("Enter your login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        if (loginId.equals("admin") && password.equals("password")) {
            System.out.println("Login successful!");
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Book a ticket");
                System.out.println("2. Cancel a ticket");
                System.out.println("3. View booking history");
                System.out.println("4. Search ticket by PNR");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        bookTicket(scanner);
                        break;

                    case 2:
                        cancelTicket(scanner);
                        break;

                    case 3:
                        viewBookingHistory();
                        break;

                    case 4:
                        searchTicketByPNR(scanner);
                        break;

                    case 5:
                        System.out.println("Exiting the system.");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }

        scanner.close();
    }
    private static void bookTicket(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter class type (Sleeper, AC 3-Tier, AC 2-Tier, First Class): ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from place: ");
        String fromPlace = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        Passenger passenger = new Passenger(name, trainNumber, trainName, classType, dateOfJourney, fromPlace, destination);
        passengersList.add(passenger);

        System.out.println("\nBooking confirmed!");
        System.out.println("Passenger Name: " + passenger.name);
        System.out.println("PNR Number: " + passenger.pnrNumber);
        System.out.println("Class: " + passenger.classType);
        System.out.println("Price: $" + passenger.price);
    }
    private static void cancelTicket(Scanner scanner) {
        System.out.print("Enter your PNR number: ");
        int pnrNumber = scanner.nextInt();
        scanner.nextLine(); 
        Passenger passengerToCancel = null;
        for (Passenger passenger : passengersList) {
            if (passenger.pnrNumber == pnrNumber) {
                passengerToCancel = passenger;
                break;
            }
        }

        if (passengerToCancel != null) {
            passengersList.remove(passengerToCancel);
            System.out.println("\nCancellation successful!");
        } else {
            System.out.println("No ticket found with the given PNR number.");
        }
    }
    private static void viewBookingHistory() {
        if (passengersList.isEmpty()) {
            System.out.println("No tickets booked yet.");
        } else {
            System.out.println("\nBooking History:");
            for (Passenger passenger : passengersList) {
                System.out.println("PNR: " + passenger.pnrNumber + ", Name: " + passenger.name + ", Train: " + passenger.trainName + ", Class: " + passenger.classType);
            }
        }
    }
    private static void searchTicketByPNR(Scanner scanner) {
        System.out.print("Enter PNR number: ");
        int pnrNumber = scanner.nextInt();
        scanner.nextLine(); 
        for (Passenger passenger : passengersList) {
            if (passenger.pnrNumber == pnrNumber) {
                System.out.println("\nTicket Details:");
                System.out.println("Passenger Name: " + passenger.name);
                System.out.println("Train: " + passenger.trainName);
                System.out.println("Class: " + passenger.classType);
                System.out.println("Journey Date: " + passenger.dateOfJourney);
                System.out.println("From: " + passenger.fromPlace + " To: " + passenger.destination);
                return;
            }
        }

        System.out.println("No ticket found with the given PNR number.");
    }
}

import java.util.*;

// Car class to represent each car
class Car {
    private String licensePlate;
    private String model;
    private boolean rented;

    public Car(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.rented = false;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public boolean isRented() {
        return rented;
    }

    public void rent() {
        rented = true;
    }

    public void returnCar() {
        rented = false;
    }

    @Override
    public String toString() {
        return model + " [" + licensePlate + "] - " + (rented ? "Rented" : "Available");
    }
}

// Customer class to represent each customer
class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// RentalAgency class to manage cars, customers, and rentals
class RentalAgency {
    private List<Car> cars;
    private List<Customer> customers;
    private Map<String, String> rentals; // customerId -> licensePlate

    public RentalAgency() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new HashMap<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public boolean rentCar(String customerId, String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equals(licensePlate) && !car.isRented()) {
                car.rent();
                rentals.put(customerId, licensePlate);
                return true;
            }
        }
        return false;
    }

    public boolean returnCar(String customerId) {
        String plate = rentals.get(customerId);
        if (plate == null) return false;

        for (Car car : cars) {
            if (car.getLicensePlate().equals(plate)) {
                car.returnCar();
                rentals.remove(customerId);
                return true;
            }
        }
        return false;
    }

    public void listAvailableCars() {
        System.out.println("Available Cars:");
        for (Car car : cars) {
            if (!car.isRented()) {
                System.out.println(" - " + car);
            }
        }
    }
}

// Main class to run the program
public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalAgency agency = new RentalAgency();

        // Add sample data
        agency.addCar(new Car("KDA123A", "Toyota Premio"));
        agency.addCar(new Car("KDA456B", "Nissan Note"));
        agency.addCustomer(new Customer("C001", "Alice"));
        agency.addCustomer(new Customer("C002", "Bob"));

        while (true) {
            System.out.println("\n--- Car Rental System ---");
            System.out.println("1. List Available Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. Return a Car");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    agency.listAvailableCars();
                    break;

                case 2:
                    System.out.print("Enter customer ID: ");
                    String rentCustomerId = scanner.nextLine();
                    System.out.print("Enter car license plate: ");
                    String rentPlate = scanner.nextLine();
                    if (agency.rentCar(rentCustomerId, rentPlate)) {
                        System.out.println("Car rented successfully.");
                    } else {
                        System.out.println("Rental failed. Car may be unavailable or info is incorrect.");
                    }
                    break;

                case 3:
                    System.out.print("Enter customer ID: ");
                    String returnCustomerId = scanner.nextLine();
                    if (agency.returnCar(returnCustomerId)) {
                        System.out.println("Car returned successfully.");
                    } else {
                        System.out.println("Return failed. Check customer ID or if car was rented.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

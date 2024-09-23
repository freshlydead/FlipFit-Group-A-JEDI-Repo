package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.business.BookingService;
import com.flipkart.business.CustomerService;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.UserService;
import com.flipkart.constant.ColourConstants;
import com.flipkart.dao.CityDAO;
import com.flipkart.dao.CityDAOImpl;
import com.flipkart.validator.ValidateCard;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents the menu interface for customers to manage their profiles, bookings,
 * and other customer-related activities in the Flipfit system.
 */
public class CustomerFlipfitMenu {

    private Scanner scanner = new Scanner(System.in);
    private UserService userServiceInterface;
    private CustomerService customerService = new CustomerService();
    private GymOwnerService gymOwnerService = new GymOwnerService();
    private BookingService bookingService = new BookingService();
    private CityDAO cityDAO = new CityDAOImpl();

    /**
     * Constructor initializes the scanner and user service interface.
     *
     * @param scanner Scanner instance for user input
     */
    public CustomerFlipfitMenu(Scanner scanner) {
        this.scanner = scanner;
        this.userServiceInterface = new UserService();
    }

    /**
     * Registers a new customer by taking input details from the user.
     *
     * @param scanner Scanner instance for user input
     */
    public void registerCustomer(Scanner scanner) {
        System.out.println("Enter your Username");
        String username = scanner.nextLine();
        System.out.println("Enter your Password");
        String password = scanner.nextLine();
        System.out.println("Enter your Name");
        String name = scanner.nextLine();
        System.out.println("Enter your Phone");
        String phone = scanner.nextLine();
        System.out.println("Enter your Email");
        String mail = scanner.nextLine();
        System.out.println("Enter your Age");
        int age = Integer.parseInt(scanner.nextLine());

        // Create a new customer using the provided details
        customerService.createCustomer(username, name, mail, phone, age, password);
        System.out.println(ColourConstants.PASTEL_GREEN + "Customer registered successfully!" + ColourConstants.RESET);
    }

    /**
     * Displays the customer menu and handles customer choices.
     *
     * @param customer the logged-in customer user
     */
    public void showMenu(Customer customer) {
        int userChoice = -1;

        // Loop until the customer chooses to exit
        while (userChoice != 8) {
            // Display customer menu options
            System.out.println("Customer Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Book Slot");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Change Password");
            System.out.println("7. Make Payment");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            // Handle customer's choice
            switch (userChoice) {
                case 1:
                    customerService.showProfile(customer);
                    break;
                case 2:
                    editProfile(customer);
                    break;
                case 3:
                    addBookings(customer);
                    break;
                case 4:
                    viewBookings(customer.getUserid());
                    break;
                case 5:
                    cancelBookings(customer.getUserid());
                    break;
                case 6:
                    changePassword(customer);
                    break;
                case 7:
                    makePayment();
                    break;
                case 8:
                    System.out.println(ColourConstants.PASTEL_GREEN + "Logging Out!" + ColourConstants.RESET);
                    break;
                default:
                    System.out.println(ColourConstants.PASTEL_RED + "Invalid choice. Please try again." + ColourConstants.RESET);
            }
        }
    }

    public void addBookings(Customer customer) {
        List<City> cities = cityDAO.getAllCities();
        AtomicInteger itr = new AtomicInteger(1);
        cities.forEach(city -> {
            System.out.println(itr.getAndIncrement() + ". " + city.getCityName());
        });
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        int c = 1;
        List<GymCenter> gymCenters = customerService.getGymCenters(city.toLowerCase());
        for (GymCenter gymCenter : gymCenters) {
            System.out.println(c + ". " + gymCenter.getGymName());
            c++;
        }

        System.out.print("Enter Gym Name: ");
        String gn = scanner.nextLine();
        GymCenter gymCenterSel = gymCenters.stream()
                .filter(gc -> gc.getGymName().equalsIgnoreCase(gn))
                .findFirst()
                .orElse(null);
        if (gymCenterSel != null) {
            List<Slot> slots = gymCenterSel.getSlots();
            if (slots.isEmpty()) {
                System.out.println(ColourConstants.PASTEL_RED + "No slots available for this gym center." + ColourConstants.RESET);
            } else {
                System.out.println("Available slots:");
                for (int i = 0; i < slots.size(); i++) {
                    Slot slot = slots.get(i);
                    System.out.printf("%d. Start time: %s, End time: %s, Capacity: %d%n",
                            i + 1, slot.getStarttime(), slot.getEndtime(), slot.getCapacity());
                }

                System.out.print("Choose a slot (enter the number): ");
                int choice = scanner.nextInt();
                Slot slot = slots.get(choice - 1);
                if (bookingService.bookSlot(customer.getUserid(), gymCenterSel, slot)) {
                    System.out.println(ColourConstants.PASTEL_GREEN + "Booking successful!" + ColourConstants.RESET);
                } else {
                    System.out.println(ColourConstants.PASTEL_RED + "Booking failed!" + ColourConstants.RESET);
                }
            }
        } else {
            System.out.println(ColourConstants.PASTEL_RED + "Invalid Gym Name." + ColourConstants.RESET);
        }
    }

    public void viewBookings(String userId) {
        List<Booking> bookings = customerService.viewBookings(userId);

        if (bookings.isEmpty()) {
            System.out.println(ColourConstants.PASTEL_RED + "No bookings found for user: " + userId + ColourConstants.RESET);
        } else {
            System.out.println("Bookings for user: " + userId);
            for (Booking booking : bookings) {
                System.out.println("Booking Gym: " + booking.getGymName());
                System.out.println("Slot Time: " + booking.getSlot().getStarttime() + " - " + booking.getSlot().getEndtime());
                System.out.println();
            }
        }
    }

    public void cancelBookings(String userId) {
        List<Booking> bookings = customerService.viewBookings(userId);
        if (bookings.isEmpty()) {
            System.out.println(ColourConstants.PASTEL_RED + "No bookings found!!!" + ColourConstants.RESET);
        } else {
            System.out.println("Bookings:\n");
            int itr = 1;
            for (Booking booking : bookings) {
                System.out.println(itr + ". " + booking.getGymName() + ": " + booking.getBookingDate() + " Duration: 1hr\n");
                itr++;
            }
            System.out.print("Choose a booking you wish to cancel (enter the number): ");
            int choice = scanner.nextInt();
            Booking booking = bookings.get(choice - 1);
            if (bookingService.cancelSlotBooking(userId, booking.getSlotID())) {
                System.out.println(ColourConstants.PASTEL_GREEN + "Slot Cancelled" + ColourConstants.RESET);
            } else {
                System.out.println(ColourConstants.PASTEL_RED + "Cancellation unsuccessful!!" + ColourConstants.RESET);
            }
        }
    }

    public void editProfile(Customer customer) {
        if (customer != null) {
            boolean updating = true;
            while (updating) {
                System.out.println("Choose the field to update:");
                System.out.println("1. Name");
                System.out.println("2. Email");
                System.out.println("3. Phone");
                System.out.println("4. Age");
                System.out.println("5. Exit");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        customer.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new email: ");
                        customer.setEmail(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter new phone: ");
                        customer.setPhone(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter new age: ");
                        customer.setAge(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 5:
                        updating = false;
                        break;
                    default:
                        System.out.println(ColourConstants.PASTEL_RED + "Invalid choice. Please try again." + ColourConstants.RESET);
                }
            }
            if (customerService.editProfile(customer)) {
                System.out.println(ColourConstants.PASTEL_GREEN + "Customer profile updated." + ColourConstants.RESET);
            }
        } else {
            System.out.println(ColourConstants.PASTEL_RED + "Customer not found." + ColourConstants.RESET);
        }
    }

    /**
     * Allows the customer to change their password.
     *
     * @param user the logged-in customer user
     */
    public void changePassword(User user) {
        System.out.print("Enter your Old Password: ");
        String password = scanner.nextLine();
        boolean flag = userServiceInterface.validatePassword(user, password);
        if (flag) {
            System.out.print("Enter your New Password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Confirm your Password: ");
            String confirmPassword = scanner.nextLine();
            userServiceInterface.confirmPassword(user, newPassword, confirmPassword);
            System.out.println(ColourConstants.PASTEL_GREEN + "Password changed successfully!" + ColourConstants.RESET);
        } else {
            System.out.println(ColourConstants.PASTEL_RED + "Wrong Old Password." + ColourConstants.RESET);
        }
    }

    /**
     * Handles the payment process for bookings.
     */
    public void makePayment() {
        System.out.println("Payment Processing...");
        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine();
        if (!ValidateCard.validateCardNumber(cardNumber)) {
            System.out.println(ColourConstants.PASTEL_RED + "Invalid card number." + ColourConstants.RESET);
            return;
        }

        System.out.print("Enter Expiry Date (MM/YY): ");
        String expiryDate = scanner.nextLine();
        if (!ValidateCard.validateExpiryDate(expiryDate)) {
            System.out.println(ColourConstants.PASTEL_RED + "Invalid expiry date." + ColourConstants.RESET);
            return;
        }

        System.out.print("Enter Cardholder Name: ");
        String cardholderName = scanner.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();
        if (!ValidateCard.validateCVV(cvv)) {
            System.out.println(ColourConstants.PASTEL_RED + "Invalid CVV." + ColourConstants.RESET);
            return;
        }

        // Assuming payment is successful; you can integrate with a payment service here.
        System.out.println(ColourConstants.PASTEL_GREEN + "Payment successful!" + ColourConstants.RESET);
    }
}

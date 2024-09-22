package com.flipkart.client;

import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.business.UserService;
import com.flipkart.dao.CustomerDAOImpl;
import com.flipkart.dao.GymOwnerDAOImpl;
import com.flipkart.exception.InvalidLogin;
import com.flipkart.constant.ColourConstants; // Make sure to import your color constants

import java.util.Scanner;

/**
 * This class is the entry point for the Flipfit application.
 * It handles user interactions for login, registration, password changes, and manages navigation
 * to different user menus based on the user role.
 */
public class FlipfitApplication {
    // Static instance of AdminFlipfitMenu to be used throughout the application
    static AdminFlipfitMenu adminFlipfitMenu = new AdminFlipfitMenu();
    static GymOwnerDAOImpl gymOwnerDAOImpl = new GymOwnerDAOImpl();
    static CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();

    /**
     * Main method to start the Flipfit application.
     * It displays the main menu and handles user choices.
     *
     * @param args command-line arguments
     * @throws InvalidLogin if login credentials are invalid
     */
    public static void main(String[] args) throws InvalidLogin {
        Scanner scanner = new Scanner(System.in);
        FlipfitApplication app = new FlipfitApplication();
        CustomerFlipfitMenu customerFlipfitMenu = new CustomerFlipfitMenu(scanner);
        ForgotPasswordMenu forgotPasswordMenu = new ForgotPasswordMenu(scanner);
        GymOwnerFlipfitMenu gymOwnerFlipfitMenu = new GymOwnerFlipfitMenu(scanner);

        int choice = -1;

        // Main menu loop
        while (choice != 4) {
            System.out.println(ColourConstants.PASTEL_BLUE + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
            System.out.println(ColourConstants.PASTEL_YELLOW + "----------------------- Welcome to FlipFit: Your Fitness Partner --------------------" + ColourConstants.RESET);
            System.out.println(ColourConstants.PASTEL_BLUE + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
            System.out.println(ColourConstants.PASTEL_YELLOW + "1. Login" + ColourConstants.RESET);
            System.out.println(ColourConstants.PASTEL_YELLOW + "2. Registration of the GYM Customer" + ColourConstants.RESET);
            System.out.println(ColourConstants.PASTEL_YELLOW + "3. Registration of the GYM Owner" + ColourConstants.RESET);
            System.out.println(ColourConstants.PASTEL_YELLOW + "4. Exit" + ColourConstants.RESET);
            System.out.print(ColourConstants.PASTEL_GREEN + "Enter your choice: " + ColourConstants.RESET);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Handle user login
                    handleLogin();
                    break;
                case 2:
                    // Register a new customer
                    customerFlipfitMenu.registerCustomer(scanner);
                    System.out.println(ColourConstants.PASTEL_GREEN + "Customer Registered" + ColourConstants.RESET);
                    break;
                case 3:
                    // Register a new gym owner
                    try {
                        gymOwnerFlipfitMenu.registerGymOwner(scanner);
                        System.out.println(ColourConstants.PASTEL_GREEN + "Gym Owner Registered" + ColourConstants.RESET);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    // Exit the application
                    System.out.println(ColourConstants.PASTEL_RED + "Exiting the application." + ColourConstants.RESET);
                    break;
                default:
                    System.out.println(ColourConstants.PASTEL_RED + "Invalid choice. Please try again." + ColourConstants.RESET);
            }
        }
    }

    /**
     * Handles user login, determines user role, and navigates to the appropriate menu.
     *
     * @throws InvalidLogin if the login attempt fails
     */
    public static void handleLogin() throws InvalidLogin {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for username and password
        System.out.print(ColourConstants.PASTEL_BLUE + "Enter your username: " + ColourConstants.RESET);
        String username = scanner.nextLine();
        UserService userService = new UserService();
        System.out.print(ColourConstants.PASTEL_BLUE + "Enter password: " + ColourConstants.RESET);
        String password = scanner.nextLine();

        // Validate user credentials and get user details
        User user = userService.login(username, password);
        if (user != null) {
            System.out.println(ColourConstants.PASTEL_GREEN + "Logged in successfully." + ColourConstants.RESET);
            String roleId = user.getRoleId();

            // Navigate to the appropriate menu based on user role
            switch (roleId) {
                case "A":
                    System.out.println(ColourConstants.PASTEL_YELLOW + "Welcome Admin !!!" + ColourConstants.RESET);
                    adminFlipfitMenu.showMenu(user);
                    break;
                case "B":
                    System.out.println(ColourConstants.PASTEL_YELLOW + "Welcome Gym Owner !!!" + ColourConstants.RESET);
                    GymOwner gymOwner = gymOwnerDAOImpl.getGymOwner(user);
                    GymOwnerFlipfitMenu gymOwnerFlipfitMenu = new GymOwnerFlipfitMenu(scanner);
                    gymOwnerFlipfitMenu.showMenu(gymOwner);
                    break;
                case "C":
                    System.out.println(ColourConstants.PASTEL_YELLOW + "Welcome Customer !!!" + ColourConstants.RESET);
                    Customer customer = customerDAOImpl.getCustomer(user);
                    CustomerFlipfitMenu customerFlipfitMenu = new CustomerFlipfitMenu(scanner);
                    customerFlipfitMenu.showMenu(customer);
                    break;
                default:
                    System.out.println(ColourConstants.PASTEL_RED + "Invalid role. Please try again." + ColourConstants.RESET);
            }
        } else {
            System.out.println(ColourConstants.PASTEL_RED + "Invalid username or password." + ColourConstants.RESET);
        }
    }
}

package com.flipkart.client;

import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.UserService;
import com.flipkart.constant.ColourConstants;

import java.util.Scanner;

/**
 * This class manages the menu and actions available to a Gym Owner.
 * It provides functionality for registering a gym owner, viewing and adding gym centers,
 * editing slots, and changing passwords.
 */
public class GymOwnerFlipfitMenu {
    private Scanner scanner; // Scanner instance for user input
    private GymOwnerService gymOwnerServiceInterface; // Service for managing gym owner operations
    private UserService userServiceInterface; // Service for managing user operations

    /**
     * Constructor to initialize the GymOwnerFlipfitMenu with Scanner, GymOwnerService, and UserService.
     *
     * @param scanner Scanner instance for user input
     */
    public GymOwnerFlipfitMenu(Scanner scanner) {
        this.scanner = scanner;
        this.gymOwnerServiceInterface = new GymOwnerService();
        this.userServiceInterface = new UserService();
    }

    /**
     * Registers a new gym owner by taking input from the user and calling the service to create a gym owner.
     *
     * @param scanner Scanner instance for user input
     */
    public void registerGymOwner(Scanner scanner) {
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your Username" + ColourConstants.RESET);
        String username = scanner.nextLine();
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your Password" + ColourConstants.RESET);
        String password = scanner.nextLine();
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your Name" + ColourConstants.RESET);
        String name = scanner.nextLine();
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your Phone" + ColourConstants.RESET);
        String phone = scanner.nextLine();
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your Email" + ColourConstants.RESET);
        String mail = scanner.nextLine();
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your Age" + ColourConstants.RESET);
        int age = Integer.parseInt(scanner.nextLine());

        gymOwnerServiceInterface.createGymOwner(username, name, mail, phone, age, password);
    }

    /**
     * Displays the Gym Owner menu and handles user choices.
     * Provides options to view all gym centers, add a new gym center, edit gym slots, and logout.
     *
     * @param gymOwner The currently logged-in gym owner user
     */
    public void showMenu(GymOwner gymOwner) {
        int gymOwnerChoice = -1;

        while (gymOwnerChoice != 5) { // Updated to 4 for logout
            System.out.println(ColourConstants.PASTEL_GREEN + "Gym Owner Menu:" + ColourConstants.RESET);
            System.out.println("1. View all Gym Centers");
            System.out.println("2. Add New Gym Center");
            System.out.println("3. Edit Gym Slots");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            gymOwnerChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (gymOwnerChoice) {
                case 1:
                    gymOwnerServiceInterface.showGymCenters(gymOwner);
                    break;
                case 2:
                    addGym(gymOwner);
                    break;
                case 3:
                    gymOwnerServiceInterface.editSlots(gymOwner);
                    break;
                case 4:
                    changePassword(gymOwner);
                    break;
                case 5:
                    System.out.println(ColourConstants.PASTEL_RED + "Logging out." + ColourConstants.RESET);
                    break;
                default:
                    System.out.println(ColourConstants.PASTEL_RED + "Invalid choice. Please try again." + ColourConstants.RESET);
            }
        }
    }

    /**
     * Changes the password for the currently logged-in user.
     * Prompts for old password, new password, and confirmation, then updates if valid.
     *
     * @param user The currently logged-in user
     */
    public void changePassword(User user) {
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your Old Password" + ColourConstants.RESET);
        String password = scanner.nextLine();
        boolean flag = userServiceInterface.validatePassword(user, password);
        if (flag) {
            System.out.println(ColourConstants.PASTEL_YELLOW + "Enter your New Password" + ColourConstants.RESET);
            String newPassword = scanner.nextLine();
            System.out.println(ColourConstants.PASTEL_YELLOW + "Confirm your Password" + ColourConstants.RESET);
            String confirmPassword = scanner.nextLine();
            userServiceInterface.confirmPassword(user, newPassword, confirmPassword);
        } else {
            System.out.println(ColourConstants.PASTEL_RED + "Wrong Old Password." + ColourConstants.RESET);
        }
    }

    public void addGym(GymOwner gymOwner) {
        System.out.println(ColourConstants.PASTEL_YELLOW + "Registering Gym Center" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter Gym Centre Name: " + ColourConstants.RESET);
        String gymName = scanner.nextLine();
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter Gym Centre Address: " + ColourConstants.RESET);
        String address = scanner.nextLine();
        System.out.println(ColourConstants.PASTEL_YELLOW + "Enter Gym Centre City: " + ColourConstants.RESET);
        String city = scanner.nextLine();
        if (gymOwnerServiceInterface.addGymCenter(gymOwner, gymName, address, city)) {
            System.out.println(ColourConstants.PASTEL_GREEN + "Gym Center registered successfully." + ColourConstants.RESET);
        } else {
            System.out.println(ColourConstants.PASTEL_RED + "Error registering Gym Center." + ColourConstants.RESET);
        }
    }
}

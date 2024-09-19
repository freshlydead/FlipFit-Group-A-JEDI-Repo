package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.constant.ColourConstants;
import flipfit.flipkart.validation.InputValidator;

import java.util.Scanner;

public class FlipFitApp {
    private static Scanner scanner = new Scanner(System.in);
    private static InputValidator inputValidator = new InputValidator();

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------Welcome to FlipFit: Your Fitness Partner--------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "1. Login" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "2. Registration as FlipFit Customer" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "3. Registration as FlipFit Gym Owner" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "4. Update Password" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "5. Exit" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > 5) {
            System.out.println(ColourConstants.PASTEL_PINK + "Invalid choice. Please try again." + ColourConstants.RESET);
            return;
        }

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registerCustomer();
                break;
            case 3:
                registerGymOwner();
                break;
            case 4:
                updatePassword();
                break;
            case 5:
                System.out.println(ColourConstants.PASTEL_ORANGE + "Exiting..." + ColourConstants.RESET);
                System.exit(0);
                break;
        }
    }

    private static void login() {
        System.out.println(ColourConstants.CYAN + "\nLogin" + ColourConstants.RESET);
        System.out.print(ColourConstants.PASTEL_GREEN + "Username: " + ColourConstants.RESET);
        String username = scanner.nextLine();
        System.out.print(ColourConstants.PASTEL_GREEN + "Password: " + ColourConstants.RESET);
        String password = scanner.nextLine();
        System.out.print(ColourConstants.PASTEL_GREEN + "Role (Admin/Customer/GymOwner): " + ColourConstants.RESET);
        String role = scanner.nextLine();

        if (!inputValidator.isValidRole(role)) {
            System.out.println(ColourConstants.PASTEL_PINK + "Invalid role. Please try again." + ColourConstants.RESET);
            return;
        }

        switch (role.toLowerCase()) {
            case "customer":
                FlipFitCustomerService flipFitCustomerService = new FlipFitCustomerService();
                FlipFitCustomer flipFitCustomer = flipFitCustomerService.login(username, password);
                if (flipFitCustomer != null) {
                    System.out.println(ColourConstants.PASTEL_BLUE + "Logged in successfully." + ColourConstants.RESET);
                    showCustomerMenu(flipFitCustomer);
                } else {
                    System.out.println(ColourConstants.PASTEL_PINK + "Login failed. Please check your credentials." + ColourConstants.RESET);
                }
                break;
            case "admin":
                FlipFitAdminService flipFitAdminService = new FlipFitAdminService();
                if (flipFitAdminService.login(username, password)) {
                    System.out.println(ColourConstants.PASTEL_BLUE + "Logged in successfully." + ColourConstants.RESET);
                    showAdminMenu();
                } else {
                    System.out.println(ColourConstants.PASTEL_PINK + "Login failed. Please check your credentials." + ColourConstants.RESET);
                }
                break;
            case "gymowner":
                FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerService();
                if (flipFitGymOwnerService.login(username, password)) {
                    System.out.println(ColourConstants.PASTEL_BLUE + "Logged in successfully." + ColourConstants.RESET);
                    showGymOwnerMenu();
                } else {
                    System.out.println(ColourConstants.PASTEL_PINK + "Login failed. Please check your credentials." + ColourConstants.RESET);
                }
                break;
        }
    }

    private static void showAdminMenu() {
        System.out.println(ColourConstants.CYAN + "\nAdmin Menu" + ColourConstants.RESET);
        FlipFitAdminClient flipFitAdminClient = new FlipFitAdminClient();
        while (!flipFitAdminClient.showMenu());
    }

    private static void showGymOwnerMenu() {
        System.out.println(ColourConstants.CYAN + "\nGym Owner Menu" + ColourConstants.RESET);
        GymOwnerClient gymOwnerClient = new GymOwnerClient();
        while (!gymOwnerClient.showMenu());
    }

    private static void showCustomerMenu(FlipFitCustomer flipFitCustomer) {
        System.out.println(ColourConstants.CYAN + "\nCustomer Menu" + ColourConstants.RESET);
        CustomerClient customerClient = new CustomerClient(flipFitCustomer);
        while (!customerClient.showMenu());
    }

    private static void registerCustomer() {
        System.out.println(ColourConstants.CYAN + "\nRegistration as FlipFit Customer" + ColourConstants.RESET);
        // Add registration logic here
    }

    private static void registerGymOwner() {
        System.out.println(ColourConstants.CYAN + "\nRegistration as FlipFit Gym Owner" + ColourConstants.RESET);
        // Add registration logic here
    }

    private static void updatePassword() {
        System.out.println(ColourConstants.CYAN + "\nUpdate Password" + ColourConstants.RESET);
        // Add password update logic here
    }
}

package com.flipkart.client;

import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.business.UserService;
import com.flipkart.dao.CustomerDAOImpl;
import com.flipkart.dao.GymOwnerDAOImpl;
import com.flipkart.exception.InvalidLogin;
import com.flipkart.constant.ColourConstants;

import java.util.Scanner;

public class FlipfitApplication {
    static AdminFlipfitMenu adminFlipfitMenu = new AdminFlipfitMenu();
    static GymOwnerDAOImpl gymOwnerDAOImpl = new GymOwnerDAOImpl();
    static CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();

    public static void main(String[] args) throws InvalidLogin {
        Scanner scanner = new Scanner(System.in);
        CustomerFlipfitMenu customerFlipfitMenu = new CustomerFlipfitMenu(scanner);
        ForgotPasswordMenu forgotPasswordMenu = new ForgotPasswordMenu(scanner);
        GymOwnerFlipfitMenu gymOwnerFlipfitMenu = new GymOwnerFlipfitMenu(scanner);

        int choice = -1;

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
                    handleLogin();
                    break;
                case 2:
                    customerFlipfitMenu.registerCustomer(scanner);
                    System.out.println(ColourConstants.PASTEL_GREEN + "Customer Registered" + ColourConstants.RESET);
                    break;
                case 3:
                    try {
                        gymOwnerFlipfitMenu.registerGymOwner(scanner);
                        System.out.println(ColourConstants.PASTEL_GREEN + "Gym Owner Registered" + ColourConstants.RESET);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println(ColourConstants.PASTEL_RED + "Exiting the application." + ColourConstants.RESET);
                    break;
                default:
                    System.out.println(ColourConstants.PASTEL_RED + "Invalid choice. Please try again." + ColourConstants.RESET);
            }
        }
    }

    public static void handleLogin() throws InvalidLogin {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ColourConstants.PASTEL_BLUE + "Enter your username: " + ColourConstants.RESET);
        String username = scanner.nextLine();
        UserService userService = new UserService();
        System.out.print(ColourConstants.PASTEL_BLUE + "Enter password: " + ColourConstants.RESET);
        String password = scanner.nextLine();

        User user = userService.login(username, password);
        if (user != null) {
            System.out.println(ColourConstants.PASTEL_GREEN + "Logged in successfully." + ColourConstants.RESET);
            String roleId = user.getRoleId();

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

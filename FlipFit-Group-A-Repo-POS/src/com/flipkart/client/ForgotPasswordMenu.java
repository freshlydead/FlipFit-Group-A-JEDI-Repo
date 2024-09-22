package com.flipkart.client;

import com.flipkart.business.ForgotPasswordService;
import com.flipkart.constant.ColourConstants;

import java.util.Scanner;

/**
 * This class handles the forgot password functionality for users.
 * It provides the interface for users to reset their password if they forget it.
 */
public class ForgotPasswordMenu {
    private Scanner scanner;
    private ForgotPasswordService forgotPasswordService; // Service to handle forgot password logic

    /**
     * Constructor to initialize the ForgotPasswordMenu with a Scanner and ForgotPasswordService.
     *
     * @param scanner Scanner instance for user input
     */
    public ForgotPasswordMenu(Scanner scanner) {
        this.scanner = scanner;
        this.forgotPasswordService = new ForgotPasswordService();
    }

    /**
     * Handles the forgot password process.
     * Prompts the user to enter their username and new password,
     * then updates the password if the username is valid.
     */
    public void forgotPassword() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Check if the user exists
        if (forgotPasswordService.isUser(username)) {
            System.out.print("Enter new password: ");
            String newPass = scanner.nextLine();

            // Reset the user's password
            forgotPasswordService.resetPass(username, newPass);
            System.out.println(ColourConstants.PASTEL_GREEN + "Password changed successfully." + ColourConstants.RESET);
        } else {
            System.out.println(ColourConstants.PASTEL_RED + "Username not found." + ColourConstants.RESET);
        }
    }
}

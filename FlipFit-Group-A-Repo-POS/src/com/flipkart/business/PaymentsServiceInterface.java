package com.flipkart.business;

import com.flipkart.bean.Payments;
import com.flipkart.validator.ValidateCard;

import java.util.Random;
import java.util.Scanner;

/**
 * Implementation of payment-related operations.
 */
public class PaymentsServiceInterface implements PaymentsService {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean validateCardDetails(Payments payments) {
        boolean isValid = true;
        if (!ValidateCard.validateCardNumber(payments.getCardNumber())) {
            System.out.println("Invalid card number.");
            isValid = false;
        }
        if (!ValidateCard.validateExpiryDate(payments.getExpiryDate())) {
            System.out.println("Invalid expiry date.");
            isValid = false;
        }
        if (!ValidateCard.validateCVV(payments.getCvv())) {
            System.out.println("Invalid CVV.");
            isValid = false;
        }
        return isValid;
    }

    @Override
    public int requestOTP(Payments payments) {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    @Override
    public boolean validateOTP(int serverOTP) {
        System.out.print("Enter OTP: ");
        int userOTP = Integer.parseInt(scanner.nextLine());
        return userOTP == serverOTP;
    }

    @Override
    public boolean collectCardDetails() {
        // Placeholder for collecting card details from the user.
        return false;
    }

    @Override
    public void requestOTP() {
        // Placeholder for requesting OTP logic.
    }

    @Override
    public void processPayments() {
        // Placeholder for processing payment logic.
    }
}

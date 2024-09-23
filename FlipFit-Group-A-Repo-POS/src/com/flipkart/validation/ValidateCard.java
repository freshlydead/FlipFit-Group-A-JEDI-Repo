package com.flipkart.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validator class for credit card details such as card number, expiry date, and CVV.
 */
public class ValidateCard {

    private static final String CARD_NUMBER_REGEX = "^[0-9]{16}$";

    public static boolean validateCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return false;
        }
        if (!cardNumber.matches(CARD_NUMBER_REGEX)) {
            return false;
        }
        return isValidLuhn(cardNumber);
    }

    private static boolean isValidLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    private static final DateTimeFormatter EXPIRY_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/yy");

    public static boolean validateExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.isEmpty()) {
            return false;
        }
        LocalDate expiryLocalDate;
        try {
            expiryLocalDate = LocalDate.parse("01/" + expiryDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (DateTimeParseException e) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDateWithYear = expiryLocalDate.withYear(currentDate.getYear());
        if (expiryLocalDate.getYear() < currentDate.getYear() % 100) {
            expiryDateWithYear = expiryDateWithYear.plusYears(1);
        }
        return !expiryDateWithYear.isBefore(currentDate);
    }

    private static final String CVV_REGEX = "^[0-9]{3,4}$";

    public static boolean validateCVV(String cvv) {
        if (cvv == null || cvv.isEmpty()) {
            return false;
        }
        return cvv.matches(CVV_REGEX);
    }
}

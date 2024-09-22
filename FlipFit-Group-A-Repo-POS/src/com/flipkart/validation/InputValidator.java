package com.flipkart.validation;

import java.util.regex.Pattern;

public class InputValidator {



        // Regular expression for valid email
        private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        // Regular expression for valid password
        private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,20}$";

        public boolean validateEmail(String email) {
            return Pattern.matches(EMAIL_REGEX, email);
        }

        public boolean validatePassword(String password) {
            return Pattern.matches(PASSWORD_REGEX, password);
        }

        public boolean isValidRole(String role) {
            return role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("customer") || role.equalsIgnoreCase("gymowner");
        }
}






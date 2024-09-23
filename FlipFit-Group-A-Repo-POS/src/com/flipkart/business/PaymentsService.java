package com.flipkart.business;

import com.flipkart.bean.Payments;

/**
 * Interface for payment-related operations.
 * Provides methods for collecting and validating card details, requesting OTPs, and processing payments.
 */
public interface PaymentsService {

    boolean validateCardDetails(Payments payments);
    int requestOTP(Payments payments);
    boolean validateOTP(int serverOTP);
    boolean collectCardDetails();
    void requestOTP();
    void processPayments();
}

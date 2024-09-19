package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitPayment;

// Service class to handle payment-related operations in the FlipFit application
public class FlipFitPaymentService {

    // Method to create a payment and return its ID
    public int createPayment(String transactionId) {
        // Create a new FlipFitPayment object with the given transaction ID
        FlipFitPayment flipFitPayment = new FlipFitPayment(transactionId);

        // Return the payment ID generated for the new payment
        return flipFitPayment.getPaymentId();
    }
}

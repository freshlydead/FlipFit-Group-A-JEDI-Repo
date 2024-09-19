package flipfit.flipkart.bean;

// Class representing a payment made for a booking
public class FlipFitPayment {
    private String transactionId; // ID of the transaction
    private int paymentId; // Unique ID for the payment
    static int paymentCounter = 0; // Counter for generating payment IDs

    // Constructor initializing payment details
    public FlipFitPayment(String transactionId) {
        this.transactionId = transactionId;
        this.paymentId = paymentCounter++;
    }

    // Getter for payment ID
    public int getPaymentId() {
        return paymentId;
    }
}

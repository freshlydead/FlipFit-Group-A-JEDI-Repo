package flipfit.flipkart.bean;

// Class representing a customer user
public class FlipFitCustomer extends FlipFitUser {
    private int customerId; // Unique ID for the customer
    static int customerIdCounter = 0; // Counter for generating customer IDs

    // Constructor initializing customer-specific properties
    public FlipFitCustomer(String name, String email, String username, String password) {
        super(name, email, username, password, Role.CUSTOMER);
        this.customerId = customerIdCounter++;
    }

    // Getter for customer ID
    public int getCustomerId() {
        return customerId;
    }
}

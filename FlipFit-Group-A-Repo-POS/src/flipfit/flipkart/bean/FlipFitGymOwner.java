package flipfit.flipkart.bean;

// Class representing a gym owner user
public class FlipFitGymOwner extends FlipFitUser {
    private int gymOwnerId; // Unique ID for the gym owner
    static int gymOwnerIdCounter = 0; // Counter for generating gym owner IDs

    // Constructor initializing gym owner-specific properties
    public FlipFitGymOwner(String name, String email, String username, String password) {
        super(name, email, username, password, Role.GYM_OWNER);
        this.gymOwnerId = gymOwnerIdCounter++;
    }

    // Getter for gym owner ID
    public int getGymOwnerId() {
        return gymOwnerId;
    }
}

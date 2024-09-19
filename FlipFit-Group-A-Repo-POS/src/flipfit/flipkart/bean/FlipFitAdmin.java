package flipfit.flipkart.bean;

// Class representing an admin user
public class FlipFitAdmin extends FlipFitUser {
    // Constructor initializing admin-specific properties
    public FlipFitAdmin(String name, String email, String username, String password) {
        super(name, email, username, password, Role.ADMIN);
    }
}

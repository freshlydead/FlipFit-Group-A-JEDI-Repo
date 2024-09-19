package flipfit.flipkart.bean;

// Base class for all users in the system
public class FlipFitUser {
    private String username; // User's username
    private String password; // User's password
    private String email;    // User's email address
    private String name;     // User's name
    private String role;     // User's role

    // Constructor to initialize user details
    public FlipFitUser(String name, String email, String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getter and setter methods for user properties
    public String getName() { return name; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
}

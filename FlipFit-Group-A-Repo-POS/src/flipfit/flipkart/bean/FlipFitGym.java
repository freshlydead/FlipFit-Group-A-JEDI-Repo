package flipfit.flipkart.bean;

public class FlipFitGym {
    private int gymId; // Unique identifier for the gym
    private int gymOwnerId; // Identifier for the owner of the gym
    private String gym_name; // Name of the gym
    private String gym_city; // City where the gym is located
    private String status; // Status of the gym (e.g., pending, approved)

    // Constructor to initialize a new gym
    public FlipFitGym(int gymOwnerId, String gym_name, String gym_city) {
        this.gymOwnerId = gymOwnerId; // Set gym owner ID
        this.gym_name = gym_name; // Set gym name
        this.gym_city = gym_city; // Set gym city
        this.status = "pending"; // Default status is pending
    }

    // Getter for gym ID
    public int getGymId() {
        return gymId;
    }

    // Setter for gym ID
    public void setGym_id(int gym_id) {
        this.gymId = gym_id;
    }

    // Getter for gym owner ID
    public int getGymOwnerId() {
        return gymOwnerId;
    }

    // Setter for gym owner ID
    public void setGymOwnerId(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
    }

    // Getter for gym name
    public String getGym_name() {
        return gym_name;
    }

    // Setter for gym name
    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    // Getter for gym city
    public String getGym_city() {
        return gym_city;
    }

    // Setter for gym city
    public void setGym_city(String gym_city) {
        this.gym_city = gym_city;
    }

    // Getter for gym status
    public String getStatus() {
        return status;
    }

    // Setter for gym status
    public void setStatus(String status) {
        this.status = status;
    }
}

package flipfit.flipkart.bean;

import java.time.LocalTime;

// Class representing a time slot in a gym
public class FlipFitSlot {
    private int slotId; // Unique ID for the slot
    private String status; // Status of the slot (available, booked, etc.)
    private int gymId; // ID of the gym offering the slot
    private LocalTime startTime; // Start time of the slot
    private LocalTime endTime; // End time of the slot
    private int seatsAvailable; // Number of seats available in the slot
    private double price; // Price of the slot

    // Constructor initializing slot details
    public FlipFitSlot(int gymId, LocalTime startTime, LocalTime endTime, int seatsAvailable, double price) {
        this.gymId = gymId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
    }

    // Getters and setters for slot properties
    public double getPrice() { return price; }
    public int getSlotId() { return slotId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getGymId() { return gymId; }
    public void setGymId(int gymId) { this.gymId = gymId; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
}

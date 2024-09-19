package flipfit.flipkart.bean;

import java.util.Date;

// Class representing a booking made by a customer
public class FlipFitBooking {
    private int bookingId; // Unique ID for the booking
    private int customerId; // ID of the customer who made the booking
    private int slotId; // ID of the slot booked
    private Date bookingDate; // Date of the booking
    private String bookingStatus; // Status of the booking
    static int bookingCounter = 0; // Counter for generating booking IDs
    private int waitListRank; // Rank for waitlist (if applicable)
    private int paymentId; // ID of the associated payment

    // Constructor to initialize booking details
    public FlipFitBooking(int customerId, int slotId, int paymentId) {
        this.bookingId = bookingCounter++;
        this.customerId = customerId;
        this.slotId = slotId;
        this.bookingStatus = "Booked"; // Default status
        this.paymentId = paymentId;
        this.waitListRank = -1; // Default waitlist status
    }

    // Getters and setters for booking properties
    public int getPaymentId() { return paymentId; }
    public int getBookingId() { return bookingId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    public int getWaitListRank() { return waitListRank; }
    public void setWaitListRank(int waitListRank) { this.waitListRank = waitListRank; }
}

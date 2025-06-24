package transportmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Booking {
    private int bookingId;
    private String customerName;
    private String contactInfo;
    private int vehicleId; 
    private int seatsBooked;
    private String status;
    private String paymentStatus;

    // No-arg constructor
    public Booking() {
    }

    // Parameterized constructor
    public Booking(String customerName, String contactInfo, int vehicleId, int seatsBooked, String status,String paymentStatus) {
        this.customerName = customerName;
        this.contactInfo = contactInfo;
        this.vehicleId = vehicleId;
        this.seatsBooked = seatsBooked;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPaymentStatus() {
    return paymentStatus;
}

public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
}


    public boolean saveToDatabase(Connection conn) {
    String sql = "INSERT INTO bookings (customer_name, contact_info, vehicle_id, seats_booked, status, payment_status) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, customerName);
        stmt.setString(2, contactInfo);
        stmt.setInt(3, vehicleId);
        stmt.setInt(4, seatsBooked);
        stmt.setString(5, status);
        stmt.setString(6, paymentStatus); // ✅ Add this line

        int rowsInserted = stmt.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        System.out.println("Error saving booking: " + e.getMessage());
        return false;
    }
}

}
